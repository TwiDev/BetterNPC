package ch.twidev.betternpc.api.npc;

import ch.twidev.betternpc.api.Agent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface NPC extends Cloneable, Agent {

    Entity getBukkitEntity();

    int getId();

    String getName();

    Location getStoredLocation();

    UUID getUniqueId();

    boolean isSpawned();

    void setBukkitEntityType(EntityType entityType);

    void setLocation(Location location);

    NPCLiving spawn();

    NPCLiving spawn(Location location);

    NPCLiving spawnToPlayer(Player player);

    NPCLiving spawnToPlayer(Player player, Location location);

    void destroyToPlayer(Player player);

    void destroy();


}
