package by.epam.buhai.airline.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import java.io.Serializable;
import static by.epam.buhai.airline.entity.Specification.*;

public class BusinessJet extends PassengerPlane implements Serializable {
    private static final long serialVersionUID = 5610053131304212688L;
    private BusinessJetTypes businessJetType;
    private boolean hasConferenceArea;
    private double unitCostMlnDollars;

    public BusinessJet() {
    }

    public BusinessJet(String name, Manufacturers manufacturer, int crew,
                       int maxSpeedKmPerHour, int rangeKm,
                       int fuelConsumptionLitersPerHour, int seatingCapacity, BusinessJetTypes businessJetType,
                       boolean hasConferenceArea, double unitCostMlnDollars) {

        super(PlaneTypes.BUSINESS_JET, name, manufacturer, crew, maxSpeedKmPerHour,
                rangeKm, fuelConsumptionLitersPerHour, seatingCapacity);
        this.businessJetType = businessJetType;
        this.hasConferenceArea = hasConferenceArea;
        this.unitCostMlnDollars = unitCostMlnDollars;
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


    public double getUnitCostMlnDollars() {
        return unitCostMlnDollars;
    }

    public void setUnitCostMlnDollars(double unitCostMlnDollars) {
        this.unitCostMlnDollars = unitCostMlnDollars;
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
                .append(getUnitCostMlnDollars(), that.getUnitCostMlnDollars())
                .append(getBusinessJetType(), that.getBusinessJetType())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getBusinessJetType())
                .append(isHasConferenceArea())
                .append(getUnitCostMlnDollars())
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append(", businessJetType=").append(businessJetType);
        sb.append(", hasConferenceArea=").append(hasConferenceArea);
        sb.append(", unitCostMlnDollars=").append(unitCostMlnDollars);
        sb.append('.');
        return sb.toString();
    }
}
