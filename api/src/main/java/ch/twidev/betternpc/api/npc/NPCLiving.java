package ch.twidev.betternpc.api.npc;

import ch.twidev.betternpc.api.utils.Pose;
import org.bukkit.Location;

public interface NPCLiving {

    void teleport(Location location);

    void faceLocation(Location location);

    void swingHand();

    void swingOffHand();

    void damage();

    void setPose(Pose pose);

}
