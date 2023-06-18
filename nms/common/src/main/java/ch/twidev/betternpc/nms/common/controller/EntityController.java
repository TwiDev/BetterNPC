package ch.twidev.betternpc.nms.common.controller;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.NPC;
import org.bukkit.entity.Player;

public interface EntityController {

    void create(Location at, NPC npc);

    void destroy();

    void showToPlayer(Player player);

    void removeToPlayer(Player player);

    Entity getBukkitEntity();

}
