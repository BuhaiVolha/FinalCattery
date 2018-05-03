package by.epam.buhai.airline.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

import static by.epam.buhai.airline.entity.Specification.*;

public class Airplane extends PassengerPlane implements Serializable {
    private static final long serialVersionUID = 3384171766593245731L;
    private AirplaneTypes airplaneTypes;
    private AirplaneBodyTypes airplaneBodyTypes;
    private AirplaneClassCabinsNumber airplaneClassCabinsNumber;

    public Airplane() {
    }

    public Airplane(String name, Manufacturers manufacturer, int crew, int maxSpeedKmPerHour,
                    double rangeKm, double fuelConsumptionLitersPerHour, int seatingCapacity,
                    AirplaneTypes airplaneTypes, AirplaneBodyTypes airplaneBodyTypes,
                    AirplaneClassCabinsNumber airplaneClassCabinsNumber) {

        super(PlaneTypes.AIRPLANE, name, manufacturer, crew, maxSpeedKmPerHour, rangeKm,
                fuelConsumptionLitersPerHour, seatingCapacity);
        this.airplaneTypes = airplaneTypes;
        this.airplaneBodyTypes = airplaneBodyTypes;
        this.airplaneClassCabinsNumber = airplaneClassCabinsNumber;
    }

    public AirplaneTypes getAirplaneTypes() {
        return airplaneTypes;
    }

    public void setAirplaneTypes(AirplaneTypes airplaneTypes) {
        this.airplaneTypes = airplaneTypes;
    }

    public AirplaneBodyTypes getAirplaneBodyTypes() {
        return airplaneBodyTypes;
    }

    public void setAirplaneBodyTypes(AirplaneBodyTypes airplaneBodyTypes) {
        this.airplaneBodyTypes = airplaneBodyTypes;
    }

    public AirplaneClassCabinsNumber getAirplaneClassCabinsNumber() {
        return airplaneClassCabinsNumber;
    }

    public void setAirplaneClassCabinsNumber(AirplaneClassCabinsNumber airplaneClassCabinsNumber) {
        this.airplaneClassCabinsNumber = airplaneClassCabinsNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        if (this == o) return true;

        Airplane airplane = (Airplane) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getAirplaneTypes(), airplane.getAirplaneTypes())
                .append(getAirplaneBodyTypes(), airplane.getAirplaneBodyTypes())
                .append(getAirplaneClassCabinsNumber(), airplane.getAirplaneClassCabinsNumber())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getAirplaneTypes())
                .append(getAirplaneBodyTypes())
                .append(getAirplaneClassCabinsNumber())
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("airplaneTypes=").append(airplaneTypes);
        sb.append(", airplaneBodyTypes=").append(airplaneBodyTypes);
        sb.append(", airplaneClassCabinsNumber=").append(airplaneClassCabinsNumber);
        sb.append('.');
        return sb.toString();
    }
}
