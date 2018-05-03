package by.epam.buhai.airline.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import java.io.Serializable;
import static by.epam.buhai.airline.entity.Specification.*;

public class Freighter extends Plane implements Serializable {
    private static final long serialVersionUID = 1507391495003649859L;
    private CargoPlaneTypes cargoPlaneType;
    private double cargoTones;
    private double cargoVolumeSquareMeter;


    public Freighter() {
    }

    public Freighter(String name, Manufacturers manufacturer, int crew,
                     int maxSpeedKmPerHour, double rangeKm, double fuelConsumptionLitersPerHour,
                     CargoPlaneTypes cargoPlaneType, double cargoTones, double cargoVolumeSquareMeter) {

        super(PlaneTypes.FREIGHTER, name, manufacturer, crew, maxSpeedKmPerHour,
                rangeKm, fuelConsumptionLitersPerHour);
        this.cargoPlaneType = cargoPlaneType;
        this.cargoTones = cargoTones;
        this.cargoVolumeSquareMeter = cargoVolumeSquareMeter;
    }

    public CargoPlaneTypes getCargoPlaneType() {
        return cargoPlaneType;
    }

    public void setCargoPlaneType(CargoPlaneTypes cargoPlaneType) {
        this.cargoPlaneType = cargoPlaneType;
    }

    public double getCargoTones() {
        return cargoTones;
    }

    public void setCargoTones(double cargoTones) {
        this.cargoTones = cargoTones;
    }

    public double getCargoVolumeSquareMeter() {
        return cargoVolumeSquareMeter;
    }

    public void setCargoVolumeSquareMeter(double cargoVolumeSquareMeter) {
        this.cargoVolumeSquareMeter = cargoVolumeSquareMeter;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        if (this == o) return true;

        Freighter freighter = (Freighter) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getCargoTones(), freighter.getCargoTones())
                .append(getCargoVolumeSquareMeter(), freighter.getCargoVolumeSquareMeter())
                .append(getCargoPlaneType(), freighter.getCargoPlaneType())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getCargoPlaneType())
                .append(getCargoTones())
                .append(getCargoVolumeSquareMeter())
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", cargoPlaneType=").append(cargoPlaneType);
        sb.append(", cargoTones=").append(cargoTones);
        sb.append(", cargoVolumeSquareMeter=").append(cargoVolumeSquareMeter);
        sb.append('.');
        return sb.toString();
    }
}
