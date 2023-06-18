package ch.twidev.betternpc.nms.v1_8_R3;

import ch.twidev.betternpc.nms.common.INMSManager;
import ch.twidev.betternpc.nms.common.IPacketsFactory;
import ch.twidev.betternpc.nms.common.controller.EntityController;

public class NMSManager implements INMSManager {

    final PacketsFactory packetsFactory;

    public NMSManager() {
        this.packetsFactory = new PacketsFactory();
    }

    @Override
    public EntityController getController() {
        return null;
    }

    @Override
    public IPacketsFactory getPacketsFactory() {
        return packetsFactory;
    }
}
