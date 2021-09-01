package me.lolok.protoboard.factory;

import me.lolok.protoboard.impl.DefaultBoard;
import org.bukkit.entity.Player;

public class DefaultBoardFactory extends BoardFactory<DefaultBoard> {
    public DefaultBoardFactory(String title, Player viewer) {
        super(DefaultBoard.class, title, viewer);
    }
}
