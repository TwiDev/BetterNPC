package ch.twidev.betternpc.nms.v1_8_R3;

import ch.twidev.betternpc.nms.common.IPacketsFactory;
import net.minecraft.server.v1_8_R3.Entity;
import net.minecraft.server.v1_8_R3.EntityLiving;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class PacketsFactory implements IPacketsFactory {

    @Override
    public void removeEntityFromPlayerList(org.bukkit.entity.Entity entity, Player player) {
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, (EntityPlayer) ((CraftEntity) entity).getHandle());
    }

    @Override
    public void removeEntityFromPlayerList(org.bukkit.entity.Entity entity) {
        EntityPlayer handle = (EntityPlayer) getHandle(entity);
    }

    private static EntityLiving getHandle(LivingEntity entity) {
        return (EntityLiving) PacketsFactory.getHandle((org.bukkit.entity.Entity) entity);
    }

    public static Entity getHandle(org.bukkit.entity.Entity entity) {
        if (!(entity instanceof CraftEntity))
            return null;
        return ((CraftEntity) entity).getHandle();
    }

}
