package by.epam.buhai.airline.main;

import by.epam.buhai.airline.entity.*;
import by.epam.buhai.airline.entity.comparator.SortingParameters;
import by.epam.buhai.airline.service.service_exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        try {
            Airline airline = new Airline("MyCompany");
            AirlineManager manager = AirlineManager.getAirlineManagerFor(airline);
            manager.buyPlanes();

            int totalCargoCapacity = manager.countTotalCargoCapacity();
            int totalPassengerCapacity = manager.countTotalPassengerCapacity();

            LOGGER.log(Level.INFO, manager.getInfoAboutAirline());
            LOGGER.log(Level.INFO, "totalCargoCapacity=" + totalCargoCapacity);
            LOGGER.log(Level.INFO, "totalPassengerCapacity=" + totalPassengerCapacity);

            LOGGER.log(Level.INFO,"Sorting by range:");
            PrintInfo.print(manager.sort(SortingParameters.RANGE));

            LOGGER.log(Level.INFO, "Finding planes by fuel consumption diapason:");
            PrintInfo.print(manager.findPlaneByFuelConsumptionDiapason(1000, 1200));

        } catch (ServiceException e) {
            LOGGER.log(Level.WARN, e.getMessage());
        }
    }
}
