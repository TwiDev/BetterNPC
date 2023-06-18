package ch.twidev.betternpc.api;

import ch.twidev.betternpc.api.npc.INPCManager;
import ch.twidev.betternpc.api.nms.INMSManager;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BetterNPC {

    private static BetterNPC INSTANCE;
    private final JavaPlugin plugin;

    public BetterNPC(JavaPlugin plugin) {
        INSTANCE = this;

        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public abstract INMSManager getNMSManager();
    public abstract INPCManager getNPCManager();

    public static BetterNPC get() {
        return INSTANCE;
    }

}
