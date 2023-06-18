package ch.twidev.betternpc.api.npc;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public interface INPCManager {

    NPC create(EntityType entityType, String name);
    NPCLiving create(EntityType entityType, String name, Location location);
    NPC create(EntityType entityType, UUID uuid, String name);

}
