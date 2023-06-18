package ch.twidev.betternpc.nms.common;

import ch.twidev.betternpc.nms.common.controller.EntityController;

public interface INMSManager {

    EntityController getController();

    IPacketsFactory getPacketsFactory();

}
