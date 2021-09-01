package me.lolok.protoboard;

import me.lolok.protoboard.adapter.BoardAdapter;
import me.lolok.protoboard.adapter.DefaultBoardAdapter;
import me.lolok.protoboard.factory.DefaultBoardFactory;
import org.bukkit.entity.Player;

import java.util.List;

public interface BoardProvider {
    String getTitle(Player player);

    List<BoardLine> getLines();

    default BoardAdapter getAdapter() {
        return DefaultBoardAdapter.getInstance();
    }

    default Board getBoard(Player viewer) {
        return new DefaultBoardFactory(getTitle(viewer), viewer).addLines(getLines()).create();
    }

    default void show(Player viewer) {
        getBoard(viewer).create();
    }
}
