package ch.twidev.betternpc.core.exception;

import ch.twidev.betternpc.core.BetterNPCPlugin;

public class PluginEnableException extends RuntimeException{

    public PluginEnableException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        BetterNPCPlugin.get().stop();

        super.printStackTrace();
    }
}
