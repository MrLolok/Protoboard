package me.lolok.protoboard.version;

import lombok.Getter;
import net.minecraft.server.v1_14_R1.*;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

public class VersionWrapper_1_14_R1 implements VersionWrapper {
    @Getter
    private final int charactersLimits = 128;

    @Override
    public PacketPlayOutScoreboardObjective createObjectivePacket(int mode, String displayName) {
        PacketPlayOutScoreboardObjective packet = new PacketPlayOutScoreboardObjective();
        setFieldValue(packet, "a", ChatColor.stripColor(displayName));
        setFieldValue(packet, "d", mode);

        if (mode == 0 || mode == 2) {
            setFieldValue(packet, "b", new ChatComponentText(displayName));
            setFieldValue(packet, "c", IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
        }

        return packet;
    }

    @Override
    public PacketPlayOutScoreboardDisplayObjective createDisplayObjectivePacket(String name) {
        PacketPlayOutScoreboardDisplayObjective packet = new PacketPlayOutScoreboardDisplayObjective();
        setFieldValue(packet, "a", 1);
        setFieldValue(packet, "b", name);
        return packet;
    }

    @Override
    public PacketPlayOutScoreboardScore createScorePacket(String name, String line, int score) {
        return new PacketPlayOutScoreboardScore(ScoreboardServer.Action.CHANGE, line, line, score);
    }

    @Override
    public PacketPlayOutScoreboardTeam createTeamPacket(int mode, String name, @Nullable String prefix, @Nullable String suffix) {
        PacketPlayOutScoreboardTeam packet = new PacketPlayOutScoreboardTeam();
        setFieldValue(packet, "i", mode);
        setFieldValue(packet, "a", name);
        if (prefix != null) setFieldValue(packet, "c", new ChatComponentText(prefix));
        if (suffix != null) setFieldValue(packet, "d", new ChatComponentText(suffix));
        setFieldValue(packet, "g", EnumChatFormat.RESET);
        setFieldValue(packet, "e", "always");
        setFieldValue(packet, "f", "never");
        setFieldValue(packet, "j", 0);
        return packet;
    }

    @Override
    public PacketPlayOutScoreboardTeam createUpdateUserPacket(int mode, String name, String user) {
        PacketPlayOutScoreboardTeam packet = createTeamPacket(mode, name, null, null);
        setFieldValue(packet, "h", Collections.singletonList(user));
        return packet;
    }

    @Override
    public void sendPackets(Player player, Object... packets) {
        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
        for (Object packet : packets)
            connection.sendPacket((Packet<?>) packet);
    }
}
