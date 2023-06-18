package ch.twidev.betternpc.core.npc.skin;

import ch.twidev.betternpc.core.npc.NPCHolder;
import org.bukkit.entity.Player;

public interface SkinnableEntity extends NPCHolder {

    Player getBukkitEntity();
}
