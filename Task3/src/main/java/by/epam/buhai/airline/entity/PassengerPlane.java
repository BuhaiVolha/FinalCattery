package by.epam.buhai.airline.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import static by.epam.buhai.airline.entity.Specification.*;

public abstract class PassengerPlane extends Plane {
    private int seatingCapacity;

    public PassengerPlane() {
    }

    public PassengerPlane(PlaneTypes type, String name, Manufacturers manufacturer, int crew,
                          int maxSpeedKmPerHour, double rangeKm,
                          double fuelConsumptionLitersPerHour, int seatingCapacity) {

        super(type, name, manufacturer, crew, maxSpeedKmPerHour,
                rangeKm, fuelConsumptionLitersPerHour);
        this.seatingCapacity = seatingCapacity;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        if (this == o) return true;

        PassengerPlane that = (PassengerPlane) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getSeatingCapacity(), that.getSeatingCapacity())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getSeatingCapacity())
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", seatingCapacity=").append(seatingCapacity);
        return sb.toString();
    }
}
