package ch.twidev.betternpc.core.npc.skin;

public class SkinProperty {

    private final String value, signature;

    public SkinProperty(String value, String signature) {
        this.value = value;
        this.signature = signature;
    }

    public String getValue() {
        return value;
    }

    public String getSignature() {
        return signature;
    }
}
