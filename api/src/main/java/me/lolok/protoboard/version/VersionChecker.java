package me.lolok.protoboard.version;

import org.bukkit.Bukkit;

public class VersionChecker {
    /**
     * Server version, Eg. 1_16_R1
     */
    public static final String VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

    /**
     * Numerical server version, Eg. 16
     */
    public static final int NUMERICAL_VERSION = Integer.parseInt(VERSION.split("_")[1]);

    public static VersionWrapper getVersionWrapper() {
        switch (NUMERICAL_VERSION) {
            case 8:
                return new VersionWrapper_1_8_R3();
            case 13:
                return new VersionWrapper_1_13_R2();
            case 14:
                return new VersionWrapper_1_14_R1();
            case 15:
                return new VersionWrapper_1_15_R1();
            case 16:
                return new VersionWrapper_1_16_R3();
        }
        return null;
    }
}
