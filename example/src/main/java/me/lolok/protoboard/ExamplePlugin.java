package me.lolok.protoboard;

import me.lolok.protoboard.impl.Board;
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
        IBoard board = new Board("§6§lTest Scoreboard", e.getPlayer()).setLine(0, "First line").setLine(1, "Second line");
        board.create();
    }
}
