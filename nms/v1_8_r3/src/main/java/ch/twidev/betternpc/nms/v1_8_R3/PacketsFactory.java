package ch.twidev.betternpc.nms.v1_8_R3;

import ch.twidev.betternpc.api.BetterNPC;
import ch.twidev.betternpc.api.npc.NPC;
import ch.twidev.betternpc.api.nms.AbstractPacketsFactory;
import ch.twidev.betternpc.common.utils.EntityUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.properties.Property;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class PacketsFactory extends AbstractPacketsFactory {

    @Override
    public void removeEntityFromPlayerList(org.bukkit.entity.Entity entity, Player... players) {
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, (EntityPlayer) ((CraftEntity) entity).getHandle());
        for (Player player : players) {
            if(!player.isOnline()) continue;
            sendPacket(player, packet);
        }
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
    public void spawnEntity(org.bukkit.entity.Entity entity, Location location, Player... players) {
        Entity handle = getHandle(entity);
        Packet<?>[] packets = new Packet[0];
        if(handle instanceof EntityPlayer) {
            EntityPlayer humanEntity = (EntityPlayer) handle;
            humanEntity.setLocation(
                    location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()
            );

            packets = new Packet[]{
                    new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, humanEntity),
                    new PacketPlayOutNamedEntitySpawn(humanEntity),
                    new PacketPlayOutEntityHeadRotation(humanEntity, (byte) (humanEntity.yaw * 256 / 360)),
                    new PacketPlayOutEntityMetadata(humanEntity.getId(), humanEntity.getDataWatcher(), true)
            };

            Bukkit.getScheduler().runTaskLaterAsynchronously(BetterNPC.get().getPlugin(), () -> {
                removeEntityFromPlayerList(entity, players);
            }, 20);
        }else if(handle instanceof EntityLiving){
            EntityLiving entityLiving = (EntityLiving) handle;

            packets = new Packet[]{
                    new PacketPlayOutSpawnEntityLiving(entityLiving),
                    new PacketPlayOutEntityMetadata(entityLiving.getId(), entityLiving.getDataWatcher(), true)
            };
        }

        for (Player player : players) {
            PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;

            for (Packet<?> packet : packets) {
                connection.sendPacket(packet);
            }
        }
    }

    @Override
    public boolean addEntityToWorld(org.bukkit.entity.Entity entity, Location location, CreatureSpawnEvent.SpawnReason spawnReason) {
        spawnEntity(entity, location, Bukkit.getOnlinePlayers().toArray(new Player[0]));
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
