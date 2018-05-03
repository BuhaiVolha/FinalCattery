package by.epam.buhai.airline.entity;

import by.epam.buhai.airline.entity.validation.Validator;
import by.epam.buhai.airline.service.AirlineService;
import by.epam.buhai.airline.service.ServiceFactory;

import java.io.Serializable;
import java.util.*;

public final class AirlineManager implements Serializable {
    private static final long serialVersionUID = -4454739363445255356L;
    private ServiceFactory factory = ServiceFactory.getInstance();
    private AirlineService service = factory.getAirlineService();

    private static AirlineManager manager;
    private Airline airline;
    private static final Airline DEFAULT_AIRLINE = new Airline("Nameless");
    private List<Plane> planes;

    private AirlineManager(Airline airline) {
        this.airline = airline;
    }

    public static AirlineManager getAirlineManagerFor(Airline airline) {
        if (manager == null) {
            Optional<Airline> optional = Optional.ofNullable(airline);
            return new AirlineManager(optional.orElse(DEFAULT_AIRLINE));
        }
        return manager;
    }


    public void buyPlanes() {
        airline.setPlanes(service.createPlaneList());
        planes = airline.getPlanes();
    }

    public void addPlane(Plane plane) {
        planes.add(plane);
    }

    public String getInfoAboutAirline() {
        return airline.toString();
    }


    public int countTotalPassengerCapacity() {
        int result = 0;

        for (Plane p : planes) {
            if (p instanceof PassengerPlane) {
                result += ((PassengerPlane) p).getSeatingCapacity();
            }
        }
        return result;
    }

    public int countTotalCargoCapacity() {
        int result = 0;

        for (Plane p : planes) {
            if (p instanceof Freighter) {
                result += ((Freighter) p).getCargoTones();
            }
        }
        return result;
    }

    public List<Plane> sort(Comparator<Plane> comparator) {

        planes.sort(comparator);
        return planes;
    }

    public List<Plane> findPlaneByFuelConsumptionDiapason(int startLitersPerHour, int endLitersPerHour) {
        List<Plane> planesByFuelConsumption = new ArrayList<>();

        if (Validator.checkDiapasonInvalid(startLitersPerHour, endLitersPerHour)) {
            for (Plane p : planes) {
                int fuel = p.getFuelConsumptionLitersPerHour();

                if ((fuel >= startLitersPerHour)
                        && (fuel <= endLitersPerHour)) {
                    planesByFuelConsumption.add(p);
                }
            }
        }
        return planesByFuelConsumption;
    }
}
