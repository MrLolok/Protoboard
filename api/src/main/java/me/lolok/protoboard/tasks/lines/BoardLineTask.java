package me.lolok.protoboard.tasks.lines;

import me.lolok.protoboard.BoardLine;

public interface BoardLineTask extends Runnable {
    BoardLine getLine();

    BoardLineTrigger getTrigger();

    void start();

    void cancel();

    boolean isRunning();
}
