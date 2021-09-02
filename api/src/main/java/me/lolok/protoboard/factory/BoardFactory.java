package me.lolok.protoboard.factory;

import me.lolok.protoboard.Board;
import me.lolok.protoboard.BoardLine;
import me.lolok.protoboard.adapter.BoardAdapter;
import me.lolok.protoboard.adapter.DefaultBoardAdapter;
import me.lolok.protoboard.impl.DefaultBoardLine;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BoardFactory<T extends Board> implements IBoardFactory<T> {
    private final Class<T> type;
    private final String title;
    private final Player viewer;

    private int index = 0;
    private BoardAdapter adapter = DefaultBoardAdapter.getInstance();
    private final Set<BoardLine> lines = new HashSet<>();

    public BoardFactory(Class<T> type, String title, Player viewer) {
        this.type = type;
        this.title = title;
        this.viewer = viewer;
    }

    @Override
    public IBoardFactory<T> addLine(String content) {
        for (BoardLine line : lines)
            if (line.getRow() == index) {
                index++;
                addLine(content);
            }

        return setLine(index++, content);
    }

    @Override
    public IBoardFactory<T> setLine(int row, String content) {
        BoardLine line = new DefaultBoardLine(viewer, row, content, null);
        return addLine(line);
    }

    @Override
    public IBoardFactory<T> addLine(BoardLine line) {
        lines.add(line);
        return this;
    }

    @Override
    public IBoardFactory<T> addLines(Collection<BoardLine> lines) {
        for (BoardLine line : lines)
            addLine(line);
        return this;
    }

    @Override
    public IBoardFactory<T> setAdapter(BoardAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    @Override
    public T create() {
        T board;
        try {
            board = type.getConstructor(String.class, Player.class).newInstance(title, viewer);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Something went wrong during build of the view", e);
        }

        board.setAdapter(adapter);
        for (BoardLine line : lines)
            board.addLine(line);

        return board;
    }
}
