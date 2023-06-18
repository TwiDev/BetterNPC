package ch.twidev.betternpc.api.npc;

import ch.twidev.betternpc.api.Agent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public interface NPC extends Cloneable, Agent {

    void destroy();

    Entity getBukkitEntity();

    int getId();

    String getName();

    Location getStoredLocation();

    UUID getUniqueId();

    boolean isSpawned();

    void setBukkitEntityType(EntityType entityType);

    void setLocation(Location location);

    void spawn();

    void spawn(Location location);



}
