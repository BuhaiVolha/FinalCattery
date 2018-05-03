package by.epam.buhai.airline.entity;

import by.epam.buhai.airline.service.AirlineService;
import by.epam.buhai.airline.service.ServiceFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
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

    public List<Plane> sort(Comparator<Plane> comparator) {
        planes.sort(comparator);
        return planes;
    }


    public void showPlanes() {
        for (Plane p : planes) {
            System.out.println(p);
        }
    }


    public List<Plane> findPlaneByFuelConsumption(int startLitersPerHour, int endLitersPerHour) {
        List<Plane> planesByFuelConsumption = new ArrayList<>();

        for (Plane p : planes) {
            int fuel = p.getFuelConsumptionLitersPerHour();
            if ((fuel >= startLitersPerHour) && (fuel <= endLitersPerHour)) {
                planesByFuelConsumption.add(p);
            }
        }
        return planesByFuelConsumption;
    }

    public List<Plane> getPlanes() {
        return planes;
    }

    public int getPlanesNumber() {
        if (planes == null) {
            return 0;
        }
        return planes.size();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Airline ");
        sb.append("name=").append(name);
        sb.append(getPlanesNumber());
        sb.append('.');
        return sb.toString();
    }
}
