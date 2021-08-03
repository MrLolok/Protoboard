package me.lolok.protoboard;

import me.lolok.protoboard.tasks.BoardLineTask;
import org.bukkit.entity.Player;

public interface IBoardLine {
    int getRow();

    String getContent();

    void setContent(String content);

    BoardLineTask getTask();

    void setTask(BoardLineTask task);

    void show();

    void destroy();

    void update();

    String getTeamName();

    String getPrefix();

    String getSuffix();

    Player getViewer();
}
