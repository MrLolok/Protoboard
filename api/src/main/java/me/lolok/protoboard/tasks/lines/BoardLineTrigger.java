package me.lolok.protoboard.tasks.lines;

import me.lolok.protoboard.BoardLine;

import java.util.function.Consumer;

@FunctionalInterface
public interface BoardLineTrigger extends Consumer<BoardLine> {

    /**
     * Performs the implemented action when the line updates.
     * @param line the {@link BoardLine}
     */
    void accept(BoardLine line);
}
