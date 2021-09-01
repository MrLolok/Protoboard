package me.lolok.protoboard.impl;

import lombok.Getter;
import lombok.Setter;
import me.lolok.protoboard.BoardLine;
import me.lolok.protoboard.adapter.BoardAdapter;
import me.lolok.protoboard.adapter.DefaultBoardAdapter;
import me.lolok.protoboard.tasks.lines.BoardLineTask;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class DefaultBoardLine implements BoardLine {
    @Getter
    private final BoardAdapter adapter = DefaultBoardAdapter.getInstance();

    @Getter
    private final Player viewer;

    @Getter
    private final int row;

    @Getter @Setter
    private String content;

    @Getter @Setter
    private BoardLineTask task;

    public DefaultBoardLine(Player viewer, int row, String content, @Nullable BoardLineTask task) {
        this.viewer = viewer;
        this.row = row;
        this.content = content;
        this.task = task;
    }

    public DefaultBoardLine(Player viewer, int row, String content) {
        this(viewer, row, content, null);
    }

    public DefaultBoardLine(Player viewer, int row) {
        this(viewer, row, "", null);
    }

    @Override
    public void show() {
        adapter.showLine(getViewer(), row, getTeamName(), getPrefix(), getSuffix());
        if (task != null) {
            task.setLine(this);
            task.start();
        }
    }

    @Override
    public void destroy() {
        adapter.destroyLine(getViewer(), getTeamName());
        if (task != null)
            task.cancel();
    }

    @Override
    public void update() {
        adapter.updateLine(getViewer(), getTeamName(), getPrefix(), getSuffix());
    }

    @Override
    public String getTeamName() {
        return ChatColor.values()[row].name();
    }

    @Override
    public String getPrefix() {
        return content.substring(0, Math.min(content.length(), BoardAdapter.VERSION_WRAPPER.getCharactersLimits()));
    }

    @Override
    public String getSuffix() {
        return content.substring(Math.min(content.length(), BoardAdapter.VERSION_WRAPPER.getCharactersLimits()));
    }
}
