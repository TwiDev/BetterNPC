package ch.twidev.betternpc.nms.common.controller;

import ch.twidev.betternpc.api.BetterNPC;
import ch.twidev.betternpc.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public abstract class AbstractEntityController implements EntityController{

    private Entity bukkitEntity;

    public AbstractEntityController() {
    }

    @Override
    public void create(Location at, NPC npc) {
        this.bukkitEntity = createEntity(at, npc);
    }

    @Override
    public void destroy() {
        //TODO: Remove living NPC from controller

        this.bukkitEntity.remove();
        this.bukkitEntity = null;
    }

    @Override
    public boolean spawn(Location at) {
        return !at.getChunk().isLoaded() ? false : BetterNPC.getNMSManager().getPacketsFactory().addEntityToWorld(getBukkitEntity(), CreatureSpawnEvent.SpawnReason.CUSTOM);
    }

    @Override
    public boolean spawn(Location at, Player player) {
        if(!at.getChunk().isLoaded()) return false;

        BetterNPC.getNMSManager().getPacketsFactory().spawnEntity(getBukkitEntity(),player);
        return true;
    }

    protected abstract Entity createEntity(Location at, NPC npc);


    @Override
    public Entity getBukkitEntity() {
        return bukkitEntity;
    }
}
