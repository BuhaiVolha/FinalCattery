package by.epam.buhai.airline.entity;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class Airline implements Serializable {
    private static final long serialVersionUID = -2654864925890857342L;
    private String name;
    private List<Plane> planes;

    public Airline(String name) {
        this.name = name;
    }

    public List<Plane> getPlanes() {
        if (planes == null) {
            return Collections.emptyList();
        }
        return planes;
    }

    public void setPlanes(List<Plane> planes) {
        this.planes = planes;
    }

    public int NumberOfPlanes() {
        if (planes == null) {
            return 0;
        }
        return planes.size();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Airline ");
        sb.append("name: ").append(name);
        sb.append(", total number of planes=").append(NumberOfPlanes());
        sb.append('.');
        return sb.toString();
    }
}
