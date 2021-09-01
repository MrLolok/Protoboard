package me.lolok.protoboard.factory;

import me.lolok.protoboard.Board;
import me.lolok.protoboard.BoardLine;

import java.util.Collection;

public interface IBoardFactory<T extends Board> {
    IBoardFactory<T> addLine(String content);

    IBoardFactory<T> setLine(int row, String content);

    IBoardFactory<T> addLine(BoardLine line);

    IBoardFactory<T> addLines(Collection<BoardLine> lines);

    T create();
}
