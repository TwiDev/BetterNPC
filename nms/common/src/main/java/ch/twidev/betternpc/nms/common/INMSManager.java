package ch.twidev.betternpc.nms.common;

public interface INMSManager {

    AbstractPacketsFactory getPacketsFactory();

    void loadAllNMSEntities();

}
