package by.epam.buhai.airline.main;

import by.epam.buhai.airline.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Comparator;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        Airline airline = new Airline("MyCompany");
        AirlineManager manager = AirlineManager.getAirlineManagerFor(airline);
        manager.buyPlanes();

        int totalCargoCapacity = manager.countTotalCargoCapacity();
        int totalPassengerCapacity = manager.countTotalPassengerCapacity();

        LOGGER.log(Level.INFO, manager.getInfoAboutAirline());
        LOGGER.log(Level.INFO, "totalCargoCapacity=" + totalCargoCapacity);
        LOGGER.log(Level.INFO, "totalPassengerCapacity=" + totalPassengerCapacity);

        LOGGER.log(Level.INFO,"Sorting by range:");
        Comparator<Plane> comparatorByRange = Comparator.comparing(Plane::getRangeKm)
                .thenComparing((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        PrintInfo.print(manager.sort(comparatorByRange));

        LOGGER.log(Level.INFO, "Finding planes by fuel consumption diapason:");
        PrintInfo.print(manager.findPlaneByFuelConsumptionDiapason(1000, 1200));
    }
}
