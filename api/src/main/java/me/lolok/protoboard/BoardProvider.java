package me.lolok.protoboard;

import me.lolok.protoboard.adapter.BoardAdapter;
import me.lolok.protoboard.adapter.DefaultBoardAdapter;
import me.lolok.protoboard.factory.DefaultBoardFactory;
import me.lolok.protoboard.tasks.BoardUpdateTask;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public interface BoardProvider {
    String getTitle(Player player);

    List<BoardLine> getLines(Player player);

    JavaPlugin getPlugin();

    default long getUpdatePeriod() {
        return 0L;
    }

    default BoardAdapter getAdapter() {
        return DefaultBoardAdapter.getInstance();
    }

    default Board getBoard(Player viewer) {
        return new DefaultBoardFactory(getTitle(viewer), viewer).addLines(getLines(viewer)).create();
    }

    default void show(Player viewer) {
        Board board = getBoard(viewer);
        if (getUpdatePeriod() > 0) {
            BoardUpdateTask task = new BoardUpdateTask(getPlugin(), getUpdatePeriod(), board, this);
            board.setUpdateTask(task);
        }
        board.create();
    }
}
