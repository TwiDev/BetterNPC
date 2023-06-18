package ch.twidev.betternpc.nms.v1_8_R3.controller;

import ch.twidev.betternpc.api.BetterNPC;
import ch.twidev.betternpc.api.npc.NPC;
import ch.twidev.betternpc.common.utils.EntityUtils;
import ch.twidev.betternpc.nms.common.controller.AbstractEntityController;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerInteractManager;
import net.minecraft.server.v1_8_R3.WorldServer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class HumanNPC extends AbstractEntityController {

    public HumanNPC() {
        super();
    }

    @Override
    protected Entity createEntity(Location at, NPC npc) {
        final MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        final WorldServer nmsWorld = ((CraftWorld) at.getWorld()).getHandle();
        UUID uuid = npc.getUniqueId();
        String fullName = npc.getName();
        String subName = fullName.length() > 16 ? fullName.substring(0, 16) : fullName;

        final GameProfile gameProfile = new GameProfile(uuid, subName);
        final EntityPlayer handle = new EntityPlayer(server, nmsWorld, gameProfile, new PlayerInteractManager(nmsWorld));

        Bukkit.getScheduler().runTaskLaterAsynchronously(BetterNPC.getPlugin(), () -> {
            if(EntityUtils.isBukkitEntityInvalid(getBukkitEntity())) return;

            BetterNPC.getNMSManager().getPacketsFactory().removeEntityFromPlayerList(getBukkitEntity());
        }, 20);

        return null;
    }

    @Override
    public Player getBukkitEntity() {
        return (Player) super.getBukkitEntity();
    }
}
