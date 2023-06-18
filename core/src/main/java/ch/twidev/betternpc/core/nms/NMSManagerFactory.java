package ch.twidev.betternpc.core.nms;

import ch.twidev.betternpc.nms.common.INMSManager;

@FunctionalInterface
public interface NMSManagerFactory {

    INMSManager create() throws UnknownVersionException, OutdatedVersionException;

    static NMSManagerFactory unknownVersion() {
        return () -> {
            throw new UnknownVersionException();
        };
    }

    static NMSManagerFactory outdatedVersion(String minimumSupportedVersion) {
        return () -> {
            throw new OutdatedVersionException(minimumSupportedVersion);
        };
    }


    public static class UnknownVersionException extends Exception {
    }

    public static class OutdatedVersionException extends Exception {

        private final String minimumSupportedVersion;

        public OutdatedVersionException(String minimumSupportedVersion) {
            this.minimumSupportedVersion = minimumSupportedVersion;
        }

        public String getMinimumSupportedVersion() {
            return minimumSupportedVersion;
        }

    }

}
