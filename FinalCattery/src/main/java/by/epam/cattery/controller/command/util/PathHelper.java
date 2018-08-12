package by.epam.cattery.controller.command.util;

import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URI;
import java.net.URISyntaxException;

public class PathHelper {
    private static final Logger logger = LogManager.getLogger(PathHelper.class);

    private static final PathHelper instance = new PathHelper();

    private PathHelper() {
    }


    public String addParameterToPath(String path, String name, int value) {

        String valueToString = Integer.toString(value);
        return addParameterToPath(path, name, valueToString);
    }


    public String addParameterToPath(String path, String name, String value) {

        try {
            return new URIBuilder(path).setParameter(name, value).build().toString();

        } catch (URISyntaxException e) {
            logger.log(Level.ERROR, "Failed to add parameters to path.", e);
            throw new RuntimeException(e);
        }
    }


    public static PathHelper getInstance() {
        return instance;
    }
}
