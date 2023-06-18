package ch.twidev.betternpc.nms.v1_8_R3;

import ch.twidev.betternpc.api.npc.NPC;
import ch.twidev.betternpc.nms.common.AbstractPacketsFactory;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class PacketsFactory extends AbstractPacketsFactory {

    @Override
    public void removeEntityFromPlayerList(org.bukkit.entity.Entity entity, Player player) {
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, (EntityPlayer) ((CraftEntity) entity).getHandle());
        sendPacket(player, packet);
    }

    @Override
    public GameProfileRepository getGameProfileRepository() {
        return ((CraftServer) Bukkit.getServer()).getServer().getGameProfileRepository();
    }

    @Override
    public void applySkin(NPC entity, String value, String signature, boolean respawn) {
        EntityPlayer handle = (EntityPlayer) getHandle(entity.getBukkitEntity());

        GameProfile gameProfile = handle.getProfile();
        gameProfile.getProperties().put("textures", new Property("textures", value, signature));

        if(respawn) {
            entity.destroy();
            entity.spawn(entity.getStoredLocation());
        }
    }

    @Override
    public void spawnEntity(org.bukkit.entity.Entity entity, Player player) {
        Entity handle = getHandle(entity);
        PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

        if(handle instanceof EntityPlayer) {
            EntityPlayer humanEntity = (EntityPlayer) handle;

            connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, humanEntity));
            connection.sendPacket(new PacketPlayOutNamedEntitySpawn(humanEntity));
            connection.sendPacket(new PacketPlayOutEntityHeadRotation(humanEntity, (byte) (humanEntity.yaw * 256 / 360)));
            connection.sendPacket(new PacketPlayOutEntityMetadata(humanEntity.getId(), humanEntity.getDataWatcher(), true));
        }else if(handle instanceof EntityLiving){
            EntityLiving entityLiving = (EntityLiving) handle;

            connection.sendPacket(new PacketPlayOutSpawnEntityLiving(entityLiving));
            connection.sendPacket(new PacketPlayOutEntityMetadata(entityLiving.getId(), entityLiving.getDataWatcher(), true));
        }
    }

    @Override
    public boolean addEntityToWorld(org.bukkit.entity.Entity entity, CreatureSpawnEvent.SpawnReason spawnReason) {
        getHandle(entity).world.addEntity(getHandle(entity), spawnReason);
        return true;
    }

    @Override
    public void removeEntityFromPlayerList(org.bukkit.entity.Entity entity) {
        EntityPlayer handle = (EntityPlayer) getHandle(entity);
        handle.world.players.remove(handle);
    }

    private static EntityLiving getHandle(LivingEntity entity) {
        return (EntityLiving) PacketsFactory.getHandle((org.bukkit.entity.Entity) entity);
    }

    public static Entity getHandle(org.bukkit.entity.Entity entity) {
        if (!(entity instanceof CraftEntity))
            return null;
        return ((CraftEntity) entity).getHandle();
    }

    public static PlayerConnection getPlayerConnection(Player player) {
        return ((CraftPlayer) player).getHandle().playerConnection;
    }

    public static void sendPacket(Player player, Packet<?> packet) {
        getPlayerConnection(player).sendPacket(packet);
    }

}
