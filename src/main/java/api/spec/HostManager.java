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
        String env = Config.getProperty("environment");
        if (env.equalsIgnoreCase("local")) {
            return true;
        } else if (env.equalsIgnoreCase("remote")) {
            return false;
        } else throw new IllegalStateException("Unknown environment: " + env);
    }
}
