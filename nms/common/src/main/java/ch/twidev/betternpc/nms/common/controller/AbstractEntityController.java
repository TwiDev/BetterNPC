package ch.twidev.betternpc.nms.common.controller;

import ch.twidev.betternpc.api.npc.NPC;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

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

    protected abstract Entity createEntity(Location at, NPC npc);


    @Override
    public Entity getBukkitEntity() {
        return bukkitEntity;
    }
}
