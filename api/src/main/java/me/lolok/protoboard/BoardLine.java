package me.lolok.protoboard;

import me.lolok.protoboard.adapter.BoardAdapter;
import me.lolok.protoboard.tasks.lines.BoardLineTask;
import org.bukkit.entity.Player;

public interface BoardLine {
    BoardAdapter getAdapter();

    Player getViewer();

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
}
