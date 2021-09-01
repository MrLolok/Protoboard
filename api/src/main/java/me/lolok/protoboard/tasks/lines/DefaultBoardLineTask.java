package me.lolok.protoboard.tasks.lines;

import lombok.Getter;
import me.lolok.protoboard.BoardLine;
import me.lolok.protoboard.tasks.RepeatingTask;
import org.bukkit.plugin.Plugin;

@Getter
public class DefaultBoardLineTask extends RepeatingTask implements BoardLineTask {
    private final BoardLine line;
    private final BoardLineTrigger trigger;

    public DefaultBoardLineTask(Plugin plugin, long period, BoardLine line, BoardLineTrigger trigger) {
        super(plugin, period);
        this.line = line;
        this.trigger = trigger;
    }

    @Override
    public void run() {
        trigger.accept(line);
    }
}
