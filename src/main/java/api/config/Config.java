package api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private final Properties properties;
    private static final String CONFIG_FILE = "config.properties";

    private Config() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream == null) {
                throw new IllegalStateException("Missing config file: " + CONFIG_FILE);
            }
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load config file: " + CONFIG_FILE, e);
        }
    }

    private static class SingletonHelper {
        private static final Config CONFIG_INSTANCE = new Config();
    }

    private static Config getInstance() {
        return SingletonHelper.CONFIG_INSTANCE;
    }

    public static String getProperty(String key) {
        String value = getInstance().properties.getProperty(key);
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing required config key: " + key);
        }
        return value.trim();
    }
}
