package me.lolok.protoboard.tasks;

import me.lolok.protoboard.Board;
import me.lolok.protoboard.BoardProvider;
import org.bukkit.plugin.Plugin;

public class BoardUpdateTask extends RepeatingTask {
    private final Board board;
    private final BoardProvider provider;

    public BoardUpdateTask(Plugin plugin, long period, Board board, BoardProvider provider) {
        super(plugin, period, true);
        this.board = board;
        this.provider = provider;
    }

    @Override
    public void run() {
        board.removeLines();
        provider.getLines(board.getViewer()).forEach(board::addLine);
    }
}
