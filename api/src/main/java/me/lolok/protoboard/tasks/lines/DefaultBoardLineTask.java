package me.lolok.protoboard.tasks.lines;

import lombok.Getter;
import lombok.Setter;
import me.lolok.protoboard.BoardLine;
import me.lolok.protoboard.tasks.RepeatingTask;
import org.bukkit.plugin.Plugin;

@Getter
public class DefaultBoardLineTask extends RepeatingTask implements BoardLineTask {
    private final BoardLineTrigger trigger;

    @Setter
    private BoardLine line;

    public DefaultBoardLineTask(Plugin plugin, long period, BoardLineTrigger trigger) {
        super(plugin, period);
        this.trigger = trigger;
    }

    @Override
    public void run() {
        trigger.accept(line);
    }
}
