package me.lolok.protoboard;

import me.lolok.protoboard.adapter.BoardAdapter;
import me.lolok.protoboard.tasks.lines.BoardLineTask;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.Set;

public interface Board {
    BoardAdapter getAdapter();

    void create();

    void destroy();

    String getTitle();

    Set<BoardLine> getLines();

    Player getViewer();

    boolean isReady();

    Optional<BoardLine> getLine(int row);

    void addLine(BoardLine line);

    void setLine(int row, String content);

    void setLine(BoardLine line, String content);

    void setLineTask(int row, BoardLineTask task);

    void setLineTask(BoardLine line, BoardLineTask task);
}
