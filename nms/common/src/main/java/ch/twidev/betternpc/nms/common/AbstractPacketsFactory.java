package ch.twidev.betternpc.nms.common;

import ch.twidev.betternpc.api.npc.NPC;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public abstract class AbstractPacketsFactory {

    public AbstractPacketsFactory() {
    }

    public abstract void removeEntityFromPlayerList(Entity entity);

    public abstract void removeEntityFromPlayerList(Entity entity, Player player);

    public abstract GameProfileRepository getGameProfileRepository();

    public abstract void applySkin(NPC entity, String value, String signature, boolean respawn);

    public abstract void spawnEntity(Entity entity, Player player);

    public abstract boolean addEntityToWorld(Entity entity, CreatureSpawnEvent.SpawnReason spawnReason);

}
