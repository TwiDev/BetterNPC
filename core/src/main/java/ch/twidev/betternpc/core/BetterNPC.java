package ch.twidev.betternpc.core;

import ch.twidev.betternpc.nms.common.INMSManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BetterNPC extends JavaPlugin {

    public static final Logger LOGGER = Logger.getLogger("SpectralDamage");

    private static BetterNPC INSTANCE;

    private INMSManager nmsManager;

    @Override
    public void onEnable() {
        INSTANCE = this;

        log("#================[BETTER NPC IS ENABLED]===============#");
        log("# Better NPC is now loading. Please read               #");
        log("# carefully all outputs coming from it.                #");
        log("# A plugin by TwiDev (https://github.com/twidev)       #");
        log("#======================================================#");

        saveDefaultConfig();

        this.nmsManager = null;
    }

    public INMSManager getNMSManager() {
        return nmsManager;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void stop() {
        this.setEnabled(false);
    }

    public static BetterNPC get() {
        return INSTANCE;
    }

    public static void log(String message) {
        LOGGER.log(Level.INFO, message);
    }
}
