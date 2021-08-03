package me.lolok.protoboard;

import org.bukkit.entity.Player;

public interface IBoardLine {
    int getRow();

    String getContent();

    void setContent(String content);

    void show();

    void destroy();

    void update();

    String getTeamName();

    String getPrefix();

    String getSuffix();

    Player getViewer();
}
