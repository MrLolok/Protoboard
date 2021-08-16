package me.lolok.protoboard.version;

import me.lolok.protoboard.version.utils.FieldHelper;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public interface VersionWrapper {
    int getCharactersLimits();

    Object createObjectivePacket(int mode, String name, String displayName);

    Object createDisplayObjectivePacket(String name);

    Object createScorePacket(String name, String line, int score);

    Object createTeamPacket(int mode, String name, @Nullable String prefix, @Nullable String suffix);

    default Object getCreateTeamPacket(String name, @Nullable String prefix, @Nullable String suffix) {
        return createTeamPacket(0, name, prefix, suffix);
    }

    default Object getDestroyTeamPacket(String name) {
        return createTeamPacket(1, name, null, null);
    }

    default Object getUpdateTeamPacket(String name, @Nullable String prefix, @Nullable String suffix) {
        return createTeamPacket(2, name, prefix, suffix);
    }

    Object createUpdateUserPacket(int mode, String name, String user);

    void sendPackets(Player player, Object... packets);

    default void setFieldValue(Object object, String fieldName, Object value) {
        try {
            FieldHelper.setDeclaredField(object.getClass(), object, fieldName, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
