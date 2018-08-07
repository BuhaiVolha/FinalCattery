package by.epam.cattery.util;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final ConfigurationManager instance = new ConfigurationManager();

    private final static ResourceBundle path = ResourceBundle.getBundle("path");
    private final static ResourceBundle messages = ResourceBundle.getBundle("messages");
    private final static ResourceBundle databaseParameters = ResourceBundle.getBundle("db_config");

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        return instance;
    }

    public String getProperty(String key) {
        return path.getString(key);
    }

    public String getMessage(String key) {
        return messages.getString(key);
    }

    public String getDatabaseParameters(String key) {
        return databaseParameters.getString(key);
    }
}
