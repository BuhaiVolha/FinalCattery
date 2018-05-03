package by.epam.buhai.airline.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import java.io.Serializable;
import static by.epam.buhai.airline.entity.Specification.*;

public class BusinessJet extends PassengerPlane implements Serializable {
    private static final long serialVersionUID = 5610053131304212688L;
    private BusinessJetTypes businessJetType;
    private boolean hasConferenceArea;
    private int cabinVolumeSquareMeter;
    private int priceDollars;

    public BusinessJet() {
    }

    public BusinessJet(String name, Manufacturers manufacturer, int crew,
                       int maxSpeedKmPerHour, double rangeKm,
                       double fuelConsumptionLitersPerHour, int seatingCapacity, BusinessJetTypes businessJetType,
                       boolean hasConferenceArea, int cabinVolumeSquareMeter, int priceDollars) {

        super(PlaneTypes.BUSINESS_JET, name, manufacturer, crew, maxSpeedKmPerHour,
                rangeKm, fuelConsumptionLitersPerHour, seatingCapacity);
        this.businessJetType = businessJetType;
        this.hasConferenceArea = hasConferenceArea;
        this.cabinVolumeSquareMeter = cabinVolumeSquareMeter;
        this.priceDollars = priceDollars;
    }

    public BusinessJetTypes getBusinessJetType() {
        return businessJetType;
    }

    public void setBusinessJetType(BusinessJetTypes businessJetType) {
        this.businessJetType = businessJetType;
    }

    public boolean isHasConferenceArea() {
        return hasConferenceArea;
    }

    public void setHasConferenceArea(boolean hasConferenceArea) {
        this.hasConferenceArea = hasConferenceArea;
    }

    public int getCabinVolumeSquareMeter() {
        return cabinVolumeSquareMeter;
    }

    public void setCabinVolumeSquareMeter(int cabinVolumeSquareMeter) {
        this.cabinVolumeSquareMeter = cabinVolumeSquareMeter;
    }

    public int getPriceDollars() {
        return priceDollars;
    }

    public void setPriceDollars(int priceDollars) {
        this.priceDollars = priceDollars;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        if (this == o) return true;

        BusinessJet that = (BusinessJet) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(isHasConferenceArea(), that.isHasConferenceArea())
                .append(getCabinVolumeSquareMeter(), that.getCabinVolumeSquareMeter())
                .append(getPriceDollars(), that.getPriceDollars())
                .append(getBusinessJetType(), that.getBusinessJetType())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getBusinessJetType())
                .append(isHasConferenceArea())
                .append(getCabinVolumeSquareMeter())
                .append(getPriceDollars())
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("businessJetType=").append(businessJetType);
        sb.append(", hasConferenceArea=").append(hasConferenceArea);
        sb.append(", cabinVolumeSquareMeter=").append(cabinVolumeSquareMeter);
        sb.append(", priceDollars=").append(priceDollars);
        sb.append('.');
        return sb.toString();
    }
}
