package by.epam.buhai.airline.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

import static by.epam.buhai.airline.entity.Specification.*;

public class Airplane extends PassengerPlane implements Serializable {
    private static final long serialVersionUID = 3384171766593245731L;
    private AirplaneTypes airplaneType;
    private AirplaneBodyTypes airplaneBodyType;
    private AirplaneClassCabinsNumber airplaneClassCabinsNumber;

    public Airplane() {
    }

    public Airplane(String name, Manufacturers manufacturer, int crew, int maxSpeedKmPerHour,
                    int rangeKm, int fuelConsumptionLitersPerHour, int seatingCapacity,
                    AirplaneTypes airplaneType, AirplaneBodyTypes airplaneBodyType,
                    AirplaneClassCabinsNumber airplaneClassCabinsNumber) {

        super(PlaneTypes.AIRPLANE, name, manufacturer, crew, maxSpeedKmPerHour, rangeKm,
                fuelConsumptionLitersPerHour, seatingCapacity);
        this.airplaneType = airplaneType;
        this.airplaneBodyType = airplaneBodyType;
        this.airplaneClassCabinsNumber = airplaneClassCabinsNumber;
    }

    public AirplaneTypes getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(AirplaneTypes airplaneType) {
        this.airplaneType = airplaneType;
    }

    public AirplaneBodyTypes getAirplaneBodyType() {
        return airplaneBodyType;
    }

    public void setAirplaneBodyType(AirplaneBodyTypes airplaneBodyType) {
        this.airplaneBodyType = airplaneBodyType;
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
                .append(getAirplaneType(), airplane.getAirplaneType())
                .append(getAirplaneBodyType(), airplane.getAirplaneBodyType())
                .append(getAirplaneClassCabinsNumber(), airplane.getAirplaneClassCabinsNumber())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getAirplaneType())
                .append(getAirplaneBodyType())
                .append(getAirplaneClassCabinsNumber())
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", airplaneType=").append(airplaneType);
        sb.append(", airplaneBodyType=").append(airplaneBodyType);
        sb.append(", airplaneClassCabinsNumber=").append(airplaneClassCabinsNumber);
        sb.append('.');
        return sb.toString();
    }
}
