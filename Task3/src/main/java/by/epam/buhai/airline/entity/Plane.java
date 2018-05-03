package by.epam.buhai.airline.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import static by.epam.buhai.airline.entity.Specification.*;
import java.io.Serializable;

public abstract class Plane implements Serializable{
    private static final long serialVersionUID = -6901954101734766180L;
    private PlaneTypes type;
    private String name;
    private Manufacturers manufacturer;
    private int crew;
    private int maxSpeedKmPerHour;
    private int rangeKm;
    private int fuelConsumptionLitersPerHour;

    public Plane() {
    }

    public Plane(PlaneTypes type, String name, Manufacturers manufacturer, int crew,
                 int maxSpeedKmPerHour, int rangeKm, int fuelConsumptionLitersPerHour) {
        this.type = type;
        this.name = name;
        this.manufacturer = manufacturer;
        this.crew = crew;
        this.maxSpeedKmPerHour = maxSpeedKmPerHour;
        this.rangeKm = rangeKm;
        this.fuelConsumptionLitersPerHour = fuelConsumptionLitersPerHour;
    }

    public PlaneTypes getType() {
        return type;
    }

    public void setType(PlaneTypes type) {
        this.type = type;
    }

    public Manufacturers getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturers manufacturer) {
        this.manufacturer = manufacturer;
    }


    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public int getMaxSpeedKmPerHour() {
        return maxSpeedKmPerHour;
    }

    public void setMaxSpeedKmPerHour(int maxSpeedKmPerHour) {
        this.maxSpeedKmPerHour = maxSpeedKmPerHour;
    }

    public int getRangeKm() {
        return rangeKm;
    }

    public void setRangeKm(int rangeKm) {
        this.rangeKm = rangeKm;
    }

    public int getFuelConsumptionLitersPerHour() {
        return fuelConsumptionLitersPerHour;
    }

    public void setFuelConsumptionLitersPerHour(int fuelConsumptionLitersPerHour) {
        this.fuelConsumptionLitersPerHour = fuelConsumptionLitersPerHour;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        if (this == o) return true;

        Plane plane = (Plane) o;

        return new EqualsBuilder()
                .append(getType(), plane.getType())
                .append(getCrew(), plane.getCrew())
                .append(getMaxSpeedKmPerHour(), plane.getMaxSpeedKmPerHour())
                .append(getRangeKm(), plane.getRangeKm())
                .append(getFuelConsumptionLitersPerHour(), plane.getFuelConsumptionLitersPerHour())
                .append(getManufacturer(), plane.getManufacturer())
                .append(getName(), plane.getName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getType())
                .append(getManufacturer())
                .append(getName())
                .append(getCrew())
                .append(getMaxSpeedKmPerHour())
                .append(getRangeKm())
                .append(getFuelConsumptionLitersPerHour())
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(type.toString()).append(":");
        sb.append(" name=").append(name);
        sb.append(", manufacturer=").append(manufacturer);
        sb.append(", crew=").append(crew);
        sb.append(", maxSpeedKmPerHour=").append(maxSpeedKmPerHour);
        sb.append(", rangeKm=").append(rangeKm);
        sb.append(", fuelConsumptionLitersPerHour=").append(fuelConsumptionLitersPerHour);
        return sb.toString();
    }
}
