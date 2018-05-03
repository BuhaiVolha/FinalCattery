package by.epam.buhai.airline.entity;

import by.epam.buhai.airline.service.AirlineService;
import by.epam.buhai.airline.service.ServiceFactory;

import java.io.Serializable;
import java.util.List;

public class Airline implements Serializable {
    private static final long serialVersionUID = -2654864925890857342L;
    private String name;
    private List<Plane> planes;

    private ServiceFactory factory = ServiceFactory.getInstance();
    private AirlineService service = factory.getAirlineService();

    public Airline(String name) {
        this.name = name;
    }

    public void buyPlanes() {
        planes = service.createPlaneList();
    }
    public void showPlanes() {
        for (Plane p : planes) {
            System.out.println(p);
        }
    }

// int countTotalSeatingCapacity();
    // double countTotalCargoCapacity();
    // sortByFlightDistance();
    // findPlaneByFuelConsumptionRange();

}
