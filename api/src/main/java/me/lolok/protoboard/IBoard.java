package me.lolok.protoboard;

import me.lolok.protoboard.version.VersionChecker;
import me.lolok.protoboard.version.VersionWrapper;
import org.bukkit.entity.Player;

import java.util.Set;

public interface IBoard {
    VersionWrapper VERSION_WRAPPER = VersionChecker.getVersionWrapper();

    void create();

    void destroy();

    String getTitle();

    Set<IBoardLine> getLines();

    Player getViewer();

    boolean isReady();

    IBoardLine getLine(int row);

    void setLine(int row, String content);

    void setLine(IBoardLine line, String content);
}
