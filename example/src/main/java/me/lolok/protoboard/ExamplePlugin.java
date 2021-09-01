package me.lolok.protoboard;

import lombok.Getter;
import me.lolok.protoboard.factory.DefaultBoardFactory;
import me.lolok.protoboard.impl.DefaultBoardLine;
import me.lolok.protoboard.tasks.lines.DefaultBoardLineTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ExamplePlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Board board = new DefaultBoardFactory("§6§lTest Scoreboard", e.getPlayer()).addLine("First line").addLine("Second line").create();
        board.create();
    }

    public static class ExampleBoard implements BoardProvider {
        @Getter
        private final JavaPlugin plugin;

        public ExampleBoard(JavaPlugin plugin) {
            this.plugin = plugin;
        }

        @Override
        public String getTitle(Player player) {
            return String.format("Hello %s", player.getName());
        }

        @Override
        public List<BoardLine> getLines(Player player) {
            List<BoardLine> lines = new ArrayList<>();
            // First line of the board
            lines.add(new DefaultBoardLine(player, 0, "Name: " + player.getName()));
            // Second line of the board (empty line)
            lines.add(new DefaultBoardLine(player, 1));
            // Third line with update task
            lines.add(new DefaultBoardLine(player, 2, "Seconds: 0", new DefaultBoardLineTask(plugin, 20L, line -> {
                int seconds = Integer.parseInt(line.getContent().split(" ")[1]);
                line.setContent("Seconds: " + seconds + 1);
            })));
            return lines;
        }
    }
}
