package me.lolok.protoboard.adapter;

import me.lolok.protoboard.version.VersionChecker;
import me.lolok.protoboard.version.VersionWrapper;
import org.bukkit.entity.Player;

public interface BoardAdapter {
    VersionWrapper VERSION_WRAPPER = VersionChecker.getVersionWrapper();

    void create(String title, Player player);

    void destroy(Player player);

    void showLine(Player viewer, int row, String team, String prefix, String suffix);

    void destroyLine(Player viewer, String team);

    void updateLine(Player viewer, String team, String prefix, String suffix);
}
