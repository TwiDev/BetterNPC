package ch.twidev.betternpc.nms.common;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface IPacketsFactory {

    void removeEntityFromPlayerList(Entity entity);

    void removeEntityFromPlayerList(Entity entity, Player player);

}
