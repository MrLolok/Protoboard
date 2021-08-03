package me.lolok.protoboard.version;

import lombok.Getter;
import lombok.SneakyThrows;
import net.minecraft.EnumChatFormat;
import net.minecraft.network.chat.ChatComponentText;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardObjective;
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardScore;
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardTeam;
import net.minecraft.server.ScoreboardServer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.scores.criteria.IScoreboardCriteria;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

public class VersionWrapper_1_17_R1 implements VersionWrapper {
    @Getter
    private final int charactersLimits = 128;

    @SneakyThrows
    @Override
    public PacketPlayOutScoreboardObjective createObjectivePacket(int mode, String displayName) {
        PacketPlayOutScoreboardObjective packet = PacketPlayOutScoreboardObjective.class.newInstance();
        setFieldValue(packet, "d", ChatColor.stripColor(displayName));
        setFieldValue(packet, "g", mode);

        if (mode == 0 || mode == 2) {
            setFieldValue(packet, "e", new ChatComponentText(displayName));
            setFieldValue(packet, "f", IScoreboardCriteria.EnumScoreboardHealthDisplay.a);
        }

        return packet;
    }

    @Override
    public PacketPlayOutScoreboardDisplayObjective createDisplayObjectivePacket(String name) {
        PacketPlayOutScoreboardDisplayObjective packet = new PacketPlayOutScoreboardDisplayObjective(1, null);
        setFieldValue(packet, "b", name);
        return packet;
    }

    @Override
    public PacketPlayOutScoreboardScore createScorePacket(String name, String line, int score) {
        return new PacketPlayOutScoreboardScore(ScoreboardServer.Action.a, line, line, score);
    }

    @SneakyThrows
    @Override
    public PacketPlayOutScoreboardTeam createTeamPacket(int mode, String name, @Nullable String prefix, @Nullable String suffix) {
        PacketPlayOutScoreboardTeam packet = PacketPlayOutScoreboardTeam.class.newInstance();
        setFieldValue(packet, "h", mode);
        setFieldValue(packet, "i", name);
        PacketPlayOutScoreboardTeam.b options = PacketPlayOutScoreboardTeam.b.class.newInstance();
        setFieldValue(options, "a", new ChatComponentText(""));
        setFieldValue(options, "b", new ChatComponentText(prefix != null ? prefix : ""));
        setFieldValue(options, "c", new ChatComponentText(suffix != null ? suffix : ""));
        setFieldValue(options, "d", "always");
        setFieldValue(options, "e", "never");
        setFieldValue(options, "f", EnumChatFormat.v);
        setFieldValue(options, "g", 0);
        setFieldValue(packet, "k", options);
        return packet;
    }

    @Override
    public PacketPlayOutScoreboardTeam createUpdateUserPacket(int mode, String name, String user) {
        PacketPlayOutScoreboardTeam packet = createTeamPacket(mode, name, null, null);
        setFieldValue(packet, "j", Collections.singletonList(user));
        return packet;
    }

    @Override
    public void sendPackets(Player player, Object... packets) {
        PlayerConnection connection = ((CraftPlayer) player).getHandle().b;
        for (Object packet : packets)
            connection.sendPacket((Packet<?>) packet);
    }
}
