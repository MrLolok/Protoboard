package me.lolok.protoboard.version;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.Nullable;

public class VersionChecker {
    /**
     * Server version, Eg. 1_16_R1
     */
    public static final String VERSION = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];

    /**
     * Numerical server version, Eg. 16
     */
    public static final int NUMERICAL_VERSION = Integer.parseInt(VERSION.split("_")[1]);

    @Nullable
    public static VersionWrapper getVersionWrapper() {
        switch (VERSION) {
            case "1_7_R4":
                return new VersionWrapper_1_7_R4();
            case "1_8_R1":
                return new VersionWrapper_1_8_R1();
            case "1_8_R2":
                return new VersionWrapper_1_8_R2();
            case "1_8_R3":
                return new VersionWrapper_1_8_R3();
            case "1_9_R1":
                return new VersionWrapper_1_9_R1();
            case "1_9_R2":
                return new VersionWrapper_1_9_R2();
            case "1_10_R1":
                return new VersionWrapper_1_10_R1();
            case "1_11_R1":
                return new VersionWrapper_1_11_R1();
            case "1_12_R1":
                return new VersionWrapper_1_12_R1();
            case "1_13_R1":
                return new VersionWrapper_1_13_R2();
            case "1_13_R2":
                return new VersionWrapper_1_13_R1();
            case "1_14_R1":
                return new VersionWrapper_1_14_R1();
            case "1_15_R1":
                return new VersionWrapper_1_15_R1();
            case "1_16_R3":
                return new VersionWrapper_1_16_R3();
            case "1_17_R1":
                return new VersionWrapper_1_17_R1();
            default:
                Bukkit.getLogger().severe("The server version isn't compatible with Protoboard!");
                return null;
        }
    }
}
