package api.spec;

import api.config.Config;

public class HostManager {
    private static final String HOST;

    private HostManager() {
    }

    static {
        if (isLocal()) {
            HOST = Config.getProperty("host.local");
        } else HOST = Config.getProperty("host.remote");
    }

    public static String getHost() {
        return HOST;
    }

    public static boolean isLocal() {
        return switch (Config.getProperty("environment").toLowerCase()) {
            case "local" -> true;
            case "remote" -> false;
            default -> throw new IllegalStateException("Unknown environment. Expected one of: local, remote");
        };
    }
}
