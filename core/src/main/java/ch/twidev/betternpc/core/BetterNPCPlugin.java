package ch.twidev.betternpc.core;

import ch.twidev.betternpc.api.BetterNPC;
import ch.twidev.betternpc.api.npc.INPCManager;
import ch.twidev.betternpc.core.command.TestCommand;
import ch.twidev.betternpc.core.exception.PluginEnableException;
import ch.twidev.betternpc.core.nms.NMSManagerFactory;
import ch.twidev.betternpc.core.nms.NMSVersion;
import ch.twidev.betternpc.core.npc.NPCManager;
import ch.twidev.betternpc.api.nms.INMSManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BetterNPCPlugin extends JavaPlugin {

    public static final Logger LOGGER = Logger.getLogger("BetterNPC");

    private static BetterNPCPlugin INSTANCE;

    private NPCManager npcManager;
    private INMSManager nmsManager;

    private BetterNPC api;

    @Override
    public void onEnable() {
        INSTANCE = this;

        log("#================[BETTER NPC IS ENABLED]===============#");
        log("# Better NPC is now loading. Please read               #");
        log("# carefully all outputs coming from it.                #");
        log("# A plugin by TwiDev (https://github.com/twidev)       #");
        log("#======================================================#");

        saveDefaultConfig();

        this.npcManager = new NPCManager();

        // Init NMS Version manager
        try {
            this.nmsManager = NMSVersion.getCurrentVersion().getManagerFactory().create();
        } catch (NMSManagerFactory.UnknownVersionException e) {
            throw new PluginEnableException("BetterNPC only supports Spigot from 1.8 to 1.20.");
        } catch (NMSManagerFactory.OutdatedVersionException e) {
            throw new PluginEnableException("BetterNPC doesn't support this version please use " + e.getMinimumSupportedVersion());
        }

        log("[BetterNPC] is now running in version " + NMSVersion.getCurrentVersion());

        this.api = new BetterNPC(this) {
            @Override
            public INMSManager getNMSManager() {
                return nmsManager;
            }

            @Override
            public INPCManager getNPCManager() {
                return npcManager;
            }
        };

        getCommand("test").setExecutor(new TestCommand());
    }

    public BetterNPC getAPI() {
        return api;
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }

    public INMSManager getNmsManager() {
        return nmsManager;
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void stop() {
        this.setEnabled(false);
    }

    public static BetterNPCPlugin get() {
        return INSTANCE;
    }

    public static void log(String message) {
        LOGGER.log(Level.INFO, message);
    }
}
