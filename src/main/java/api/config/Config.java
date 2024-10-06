package api.config;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties properties;
    private static final String CONFIG_FILE = "config.properties";

    private Config() {
        properties = new Properties();
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE);
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class SingletonHelper {
        private static final Config CONFIG_INSTANCE = new Config();
    }

    private static Config getInstance() {
        return SingletonHelper.CONFIG_INSTANCE;
    }

    public static String getProperty(String key) {
        return getInstance().properties.getProperty(key);
    }
}
