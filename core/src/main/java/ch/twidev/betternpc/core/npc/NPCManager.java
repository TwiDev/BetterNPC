package ch.twidev.betternpc.core.npc;

import ch.twidev.betternpc.api.npc.INPCManager;
import ch.twidev.betternpc.api.npc.NPC;
import ch.twidev.betternpc.api.npc.NPCLiving;
import ch.twidev.betternpc.nms.common.controller.EntityControllers;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public class NPCManager implements INPCManager {
    @Override
    public NPC create(EntityType entityType, String name) {
        return create(entityType,UUID.randomUUID(),name);
    }

    @Override
    public NPCLiving create(EntityType entityType, String name, Location location) {
        NPC npc = this.create(entityType, name);
        return npc.spawn(location);
    }

    @Override
    public NPC create(EntityType entityType, UUID uuid, String name) {
        return new NPCImpl(uuid, name, EntityControllers.createController(entityType));
    }
}
