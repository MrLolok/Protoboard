package me.lolok.protoboard.tasks;

import me.lolok.protoboard.IBoardLine;

import java.util.function.Consumer;

@FunctionalInterface
public interface BoardLineTrigger extends Consumer<IBoardLine> {

    /**
     * Performs the implemented action when the line updates.
     * @param line the {@link IBoardLine}
     */
    void accept(IBoardLine line);
}
