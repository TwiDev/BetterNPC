package ch.twidev.betternpc.api.nms;

public interface INMSManager {

    AbstractPacketsFactory getPacketsFactory();

    void loadAllNMSEntities();

}
