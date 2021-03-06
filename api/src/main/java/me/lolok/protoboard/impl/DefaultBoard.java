package me.lolok.protoboard.impl;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.lolok.protoboard.Board;
import me.lolok.protoboard.BoardLine;
import me.lolok.protoboard.adapter.BoardAdapter;
import me.lolok.protoboard.adapter.DefaultBoardAdapter;
import me.lolok.protoboard.tasks.RepeatingTask;
import me.lolok.protoboard.tasks.lines.BoardLineTask;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Getter
public class DefaultBoard implements Board {
    @Setter
    private BoardAdapter adapter = DefaultBoardAdapter.getInstance();

    private final String title;
    private final Set<BoardLine> lines = new HashSet<>();
    private final Player viewer;

    @Setter
    private RepeatingTask updateTask;
    private boolean ready = false;

    @Override
    public void create() {
        adapter.create(title, viewer);
        lines.forEach(BoardLine::show);
        if (updateTask != null) updateTask.start();
        ready = true;
    }

    @Override
    public void destroy() {
        adapter.destroy(viewer);
        lines.forEach(BoardLine::destroy);
        if (updateTask != null) updateTask.cancel();
        ready = false;
    }

    @Override
    public Optional<BoardLine> getLine(int row) {
        return lines.stream().filter(line -> line.getRow() == row).findAny();
    }

    @Override
    public void addLine(BoardLine line) {
        int row = line.getRow();
        Optional<BoardLine> existing = getLine(row);
        if (existing.isPresent()) {
            setLine(existing.get(), line.getContent());
            return;
        }

        lines.add(line);
        line.show();
    }

    @Override
    public void setLine(int row, String content) {
        BoardLine line = new DefaultBoardLine(viewer, row, content, null);
        addLine(line);
    }

    @Override
    public void setLine(BoardLine line, String content) {
        line.setContent(content);
        line.update();
    }

    @Override
    public void setLineTask(int row, BoardLineTask task) {
        getLine(row).ifPresent(line -> setLineTask(line, task));
    }

    @Override
    public void setLineTask(BoardLine line, BoardLineTask task) {
        if (line.getTask() != null && line.getTask().isRunning()) line.getTask().cancel();

        line.setTask(task);
        task.start();
    }

    @Override
    public void removeLine(int row) {
        getLine(row).ifPresent(line -> {
            line.destroy();
            lines.remove(line);
        });
    }

    @Override
    public void removeLines() {
        lines.forEach(BoardLine::destroy);
        lines.clear();
    }
}
