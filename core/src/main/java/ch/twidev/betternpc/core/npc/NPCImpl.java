package ch.twidev.betternpc.core.npc;

import ch.twidev.betternpc.api.npc.NPC;
import ch.twidev.betternpc.api.npc.NPCLiving;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.UUID;

public class NPCImpl implements NPC {
    @Override
    public Entity getBukkitEntity() {
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Location getStoredLocation() {
        return null;
    }

    @Override
    public UUID getUniqueId() {
        return null;
    }

    @Override
    public boolean isSpawned() {
        return false;
    }

    @Override
    public NPCLiving spawn() {
        return null;
    }

    @Override
    public void destroy() {

    }

    @Override
    public boolean isVisibleByDefault() {
        return false;
    }

    @Override
    public void setVisibleByDefault(boolean visible) {

    }

    @Override
    public void destroyToPlayer(Player player) {

    }

    @Override
    public NPCLiving spawnToPlayer(Player player, Location location) {
        return null;
    }

    @Override
    public NPCLiving spawnToPlayer(Player player) {
        return null;
    }

    @Override
    public NPCLiving spawn(Location location) {
        return null;
    }

    @Override
    public void setLocation(Location location) {

    }

    @Override
    public void setBukkitEntityType(EntityType entityType) {

    }
}
