package by.babanin.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ConfigProperties {
    public static final String LOGIN_BUNDLE_NAME_KEY = "content.page.login";
    public static final String MAIN_BUNDLE_NAME_KEY = "content.page.main";
    private static final String NAME_CONFIG_FILE = "config.properties";
    private static Properties properties = new Properties();

    static {
        URL resource = ConfigProperties.class.getClassLoader().getResource(NAME_CONFIG_FILE);
        try (InputStream is = resource.openStream()) {
            properties.load(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties() {
        return properties;
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getContentLoginPage() {
        return getProperty(LOGIN_BUNDLE_NAME_KEY);
    }

    public static String getContentMainPage() {
        return getProperty(MAIN_BUNDLE_NAME_KEY);
    }
}
