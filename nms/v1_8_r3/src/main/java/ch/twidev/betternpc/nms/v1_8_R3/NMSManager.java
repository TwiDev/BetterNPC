package ch.twidev.betternpc.nms.v1_8_R3;

import ch.twidev.betternpc.api.nms.AbstractPacketsFactory;
import ch.twidev.betternpc.api.nms.INMSManager;
import ch.twidev.betternpc.nms.common.controller.EntityControllers;

public class NMSManager implements INMSManager {

    final PacketsFactory packetsFactory;

    public NMSManager() {
        this.packetsFactory = new PacketsFactory();
    }

    @Override
    public AbstractPacketsFactory getPacketsFactory() {
        return packetsFactory;
    }

    @Override
    public void loadAllNMSEntities() {
        EntityControllers.loadControllersFromPackage("ch.twidev.betternpc.nms.v1_8_R3.controller");
    }
}
