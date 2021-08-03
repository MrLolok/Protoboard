package me.lolok.protoboard;

import me.lolok.protoboard.tasks.BoardLineTask;
import me.lolok.protoboard.version.VersionChecker;
import me.lolok.protoboard.version.VersionWrapper;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.Set;

public interface IBoard {
    VersionWrapper VERSION_WRAPPER = VersionChecker.getVersionWrapper();

    void create();

    void destroy();

    String getTitle();

    Set<IBoardLine> getLines();

    Player getViewer();

    boolean isReady();

    Optional<IBoardLine> getLine(int row);

    void setLine(int row, String content);

    void setLine(IBoardLine line, String content);

    void setLineTask(int row, BoardLineTask task);

    void setLineTask(IBoardLine line, BoardLineTask task);
}
