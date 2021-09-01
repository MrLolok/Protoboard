package me.lolok.protoboard.adapter;

import org.bukkit.entity.Player;

public class DefaultBoardAdapter implements BoardAdapter {
    private final static BoardAdapter instance = new DefaultBoardAdapter();

    public static BoardAdapter getInstance() {
        return instance;
    }

    @Override
    public void create(String title, Player viewer) {
        VERSION_WRAPPER.sendPackets(viewer, VERSION_WRAPPER.createObjectivePacket(0, viewer.getName(), title));
        VERSION_WRAPPER.sendPackets(viewer, VERSION_WRAPPER.createDisplayObjectivePacket(viewer.getName()));
    }

    @Override
    public void destroy(Player viewer) {
        VERSION_WRAPPER.sendPackets(viewer, VERSION_WRAPPER.createObjectivePacket(1, viewer.getName(), null));
    }

    @Override
    public void showLine(Player viewer, int row, String team, String prefix, String suffix) {
        VERSION_WRAPPER.sendPackets(viewer, VERSION_WRAPPER.getCreateTeamPacket(team, prefix, suffix));
        VERSION_WRAPPER.sendPackets(viewer, VERSION_WRAPPER.createScorePacket(viewer.getName(), prefix + suffix, 15 - row));
    }

    @Override
    public void destroyLine(Player viewer, String team) {
        VERSION_WRAPPER.sendPackets(viewer, VERSION_WRAPPER.getDestroyTeamPacket(team));
    }

    @Override
    public void updateLine(Player viewer, String team, String prefix, String suffix) {
        VERSION_WRAPPER.sendPackets(viewer, VERSION_WRAPPER.getUpdateTeamPacket(team, prefix, suffix));
    }
}
