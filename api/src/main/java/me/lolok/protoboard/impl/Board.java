package me.lolok.protoboard.impl;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.lolok.protoboard.IBoard;
import me.lolok.protoboard.IBoardLine;
import me.lolok.protoboard.tasks.BoardLineTask;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Getter
public class Board implements IBoard {
    private final String title;
    private final Set<IBoardLine> lines = new HashSet<>();
    private final Player viewer;
    private boolean ready = false;

    @Override
    public void create() {
        Preconditions.checkNotNull(VERSION_WRAPPER);
        VERSION_WRAPPER.sendPackets(viewer, VERSION_WRAPPER.createObjectivePacket(0, viewer.getName(), title));
        VERSION_WRAPPER.sendPackets(viewer, VERSION_WRAPPER.createDisplayObjectivePacket(viewer.getName()));
        lines.forEach(IBoardLine::show);
        ready = true;
    }

    @Override
    public void destroy() {
        Preconditions.checkNotNull(VERSION_WRAPPER);
        VERSION_WRAPPER.sendPackets(viewer, VERSION_WRAPPER.createObjectivePacket(1, viewer.getName(), null));
        lines.forEach(IBoardLine::destroy);
        ready = false;
    }

    @Override
    public Optional<IBoardLine> getLine(int row) {
        return lines.stream().filter(line -> line.getRow() == row).findAny();
    }

    @Override
    public IBoard setLine(int row, String content) {
        Optional<IBoardLine> existing = getLine(row);
        if (existing.isPresent()) {
            setLine(existing.get(), content);
            return this;
        }

        IBoardLine line = new BoardLine(this, row, content, null);
        lines.add(line);
        line.show();
        return this;
    }

    @Override
    public IBoard setLine(IBoardLine line, String content) {
        line.setContent(content);
        line.update();
        return this;
    }

    @Override
    public IBoard setLineTask(int row, BoardLineTask task) {
        getLine(row).ifPresent(line -> setLineTask(line, task));
        return this;
    }

    @Override
    public IBoard setLineTask(IBoardLine line, BoardLineTask task) {
        if (line.getTask() != null && line.getTask().isRunning()) line.getTask().cancel();

        line.setTask(task);
        task.start();
        return this;
    }
}
