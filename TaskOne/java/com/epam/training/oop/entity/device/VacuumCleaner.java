package com.epam.training.oop.entity.device;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class VacuumCleaner extends Device {
    private static final String GOODS_TYPE = "VacuumCleaner";
    private double powerConsumption;
    private String filterType;
    private String bagType;
    private String wandType;
    private double motorSpeedRegulation;
    private double cleaningWidth;

    public VacuumCleaner() {}

    public static String getGoodsType() {
        return GOODS_TYPE;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getBagType() {
        return bagType;
    }

    public void setBagType(String bagType) {
        this.bagType = bagType;
    }

    public String getWandType() {
        return wandType;
    }

    public void setWandType(String wandType) {
        this.wandType = wandType;
    }

    public double getMotorSpeedRegulation() {
        return motorSpeedRegulation;
    }

    public void setMotorSpeedRegulation(double motorSpeedRegulation) {
        this.motorSpeedRegulation = motorSpeedRegulation;
    }

    public double getCleaningWidth() {
        return cleaningWidth;
    }

    public void setCleaningWidth(double cleaningWidth) {
        this.cleaningWidth = cleaningWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof VacuumCleaner)) return false;

        VacuumCleaner that = (VacuumCleaner) o;

        return new EqualsBuilder()
                .append(getPowerConsumption(), that.getPowerConsumption())
                .append(getMotorSpeedRegulation(), that.getMotorSpeedRegulation())
                .append(getCleaningWidth(), that.getCleaningWidth())
                .append(getFilterType(), that.getFilterType())
                .append(getBagType(), that.getBagType())
                .append(getWandType(), that.getWandType())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getPowerConsumption())
                .append(getFilterType())
                .append(getBagType())
                .append(getWandType())
                .append(getMotorSpeedRegulation())
                .append(getCleaningWidth())
                .toHashCode();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(GOODS_TYPE + ": ");
        sb.append("POWER_CONSUMPTION=" + powerConsumption + ", ");
        sb.append("FILTER_TYPE=" + filterType + ", ");
        sb.append("BAG_TYPE=" + bagType + ", ");
        sb.append("WAND_TYPE=" + wandType + ", ");
        sb.append("MOTOR_SPEED_REGULATION=" + motorSpeedRegulation + ", ");
        sb.append("CLEANING_WIDTH=" + cleaningWidth + ";");
        return sb.toString();
    }
}
