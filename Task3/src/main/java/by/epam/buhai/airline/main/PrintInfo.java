package by.epam.buhai.airline.main;

import by.epam.buhai.airline.entity.Plane;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;


public final class PrintInfo {
    private static final Logger LOGGER = LogManager.getLogger(PrintInfo.class);
    private static final String EMPTY_LIST_MESSAGE = "There are no planes";

    public static void print(List<Plane> planes) {
        if ((planes != null) && (!planes.isEmpty())) {
            for (Plane p : planes) {
                System.out.println(p);
            }
        } else {
            LOGGER.log(Level.INFO, EMPTY_LIST_MESSAGE);
        }
    }
}
