package me.lolok.protoboard.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import me.lolok.protoboard.IBoard;
import me.lolok.protoboard.IBoardLine;
import me.lolok.protoboard.tasks.BoardLineTask;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

@AllArgsConstructor
@RequiredArgsConstructor
public class BoardLine implements IBoardLine {
    private final IBoard board;

    @Getter
    private final int row;

    @Getter @Setter
    private String content = "";

    @Getter @Setter
    private BoardLineTask task;

    @Override
    public void show() {
        IBoard.VERSION_WRAPPER.sendPackets(getViewer(), IBoard.VERSION_WRAPPER.getCreateTeamPacket(getTeamName(), getPrefix(), getSuffix()));
        IBoard.VERSION_WRAPPER.sendPackets(getViewer(), IBoard.VERSION_WRAPPER.createUpdateUserPacket(3, getTeamName(), getViewer().getName()));
        IBoard.VERSION_WRAPPER.sendPackets(getViewer(), IBoard.VERSION_WRAPPER.createScorePacket(getViewer().getName(), getTeamName(), 15 - row));
    }

    @Override
    public void destroy() {
        IBoard.VERSION_WRAPPER.sendPackets(getViewer(), IBoard.VERSION_WRAPPER.getDestroyTeamPacket(getTeamName()));
    }

    @Override
    public void update() {
        IBoard.VERSION_WRAPPER.sendPackets(getViewer(), IBoard.VERSION_WRAPPER.getUpdateTeamPacket(getTeamName(), getPrefix(), getSuffix()));
    }

    @Override
    public String getTeamName() {
        return ChatColor.values()[row].name();
    }

    @Override
    public String getPrefix() {
        return content.substring(0, IBoard.VERSION_WRAPPER.getCharactersLimits());
    }

    @Override
    public String getSuffix() {
        return content.substring(IBoard.VERSION_WRAPPER.getCharactersLimits());
    }

    @Override
    public Player getViewer() {
        return board.getViewer();
    }
}
