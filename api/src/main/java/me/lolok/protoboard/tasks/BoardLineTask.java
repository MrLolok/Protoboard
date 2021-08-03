package me.lolok.protoboard.tasks;

import me.lolok.protoboard.IBoardLine;
import org.bukkit.plugin.Plugin;

public class BoardLineTask extends RepeatingTask {
    private final IBoardLine line;
    private final BoardLineTrigger listener;

    public BoardLineTask(Plugin plugin, long period, IBoardLine line, BoardLineTrigger listener) {
        super(plugin, period);
        this.line = line;
        this.listener = listener;
    }

    @Override
    public void run() {
        listener.accept(line);
    }
}
