package me.lolok.protoboard.factory;

import me.lolok.protoboard.Board;
import me.lolok.protoboard.BoardLine;
import me.lolok.protoboard.adapter.BoardAdapter;

import java.util.Collection;

public interface IBoardFactory<T extends Board> {
    IBoardFactory<T> addLine(String content);

    IBoardFactory<T> setLine(int row, String content);

    IBoardFactory<T> addLine(BoardLine line);

    IBoardFactory<T> addLines(Collection<BoardLine> lines);

    IBoardFactory<T> setAdapter(BoardAdapter adapter);

    T create();
}
