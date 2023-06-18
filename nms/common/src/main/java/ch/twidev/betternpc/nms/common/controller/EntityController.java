package ch.twidev.betternpc.nms.common.controller;

import ch.twidev.betternpc.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface EntityController {

    void create(Location at, NPC npc);

    void destroy();

    Entity getBukkitEntity();

}
