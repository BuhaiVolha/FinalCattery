package by.epam.cattery.dao.connection;

import java.util.ResourceBundle;

public class DBResourceManager {
    private final static DBResourceManager instance = new DBResourceManager();
    private final static String PROPERTIES_PATH = "db_config";
    private ResourceBundle bundle = ResourceBundle.getBundle(PROPERTIES_PATH);

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getParameter(String key){
        return bundle.getString(key);
    }
}
