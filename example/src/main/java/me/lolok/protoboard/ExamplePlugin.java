package me.lolok.protoboard;

import me.lolok.protoboard.factory.DefaultBoardFactory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

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
}
