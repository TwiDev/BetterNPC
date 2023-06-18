package ch.twidev.betternpc.common.utils;

import org.bukkit.entity.Entity;

public class EntityUtils {

    public static boolean isBukkitEntityInvalid(Entity entity){
        return entity == null || !entity.isValid();
    }

}
