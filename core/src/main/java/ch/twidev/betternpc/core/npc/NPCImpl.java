package ch.twidev.betternpc.core.npc;

import ch.twidev.betternpc.api.npc.NPC;
import ch.twidev.betternpc.api.npc.NPCLiving;
import ch.twidev.betternpc.core.BetterNPCPlugin;
import ch.twidev.betternpc.core.npc.skin.SkinnableEntity;
import ch.twidev.betternpc.nms.common.controller.EntityController;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.UUID;

public class NPCImpl implements NPC {

    private final UUID uuid;
    private final String name;
    private EntityController entityController;

    public NPCImpl(UUID uuid, String name, EntityController entityController) {
        this.uuid = uuid;
        this.name = name;
        this.setEntityController(entityController);
    }

    @Override
    public Entity getBukkitEntity() {
        return entityController == null ? null : entityController.getBukkitEntity();
    }

    public EntityController getEntityController() {
        return entityController;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Location getStoredLocation() {
        return isSpawned() ? getBukkitEntity().getLocation() : null;
    }

    @Override
    public UUID getUniqueId() {
        return uuid;
    }

    @Override
    public boolean isSpawned() {
        return getBukkitEntity() != null;
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
    @Deprecated
    public NPCLiving spawnToPlayer(Player player) {
        return null;
    }

    @Override
    public NPCLiving spawn(Location location) {
        if(getBukkitEntity() != null) {
            //TODO: log entity already spawned
        }
        location = location.clone();
        entityController.create(location.clone(), this);
        getBukkitEntity().setMetadata("BetterNPC", new FixedMetadataValue(BetterNPCPlugin.get(), true));
        getBukkitEntity().setMetadata("BetterNPC-UUID", new FixedMetadataValue(BetterNPCPlugin.get(), getUniqueId()));
        if(getBukkitEntity() instanceof SkinnableEntity) {

        }

        boolean couldSpawn = entityController.spawn(location);

        if(!couldSpawn) {
            entityController.destroy();
        }

        return new LivingNPCImpl();
    }

    @Override
    public void setLocation(Location location) {

    }

    @Override
    public void setBukkitEntityType(EntityType entityType) {

    }

    public void setEntityController(EntityController newController) {
        boolean wasSpawned = entityController == null ? false : isSpawned();

        Location previousLocation = null;
        if(wasSpawned)  {
            previousLocation = getBukkitEntity().getLocation();
            destroy();
        }

        entityController = newController;
        if(wasSpawned) {
            spawn(previousLocation);
        }
    }

}
