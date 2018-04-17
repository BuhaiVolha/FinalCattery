package com.epam.training.oop.entity.device;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Speakers extends Device {
    private static final String GOODS_TYPE = "Speakers";
    private double powerConsumption;
    private double numberOfSpeakers;
    private String freqencyRange;
    private double cordLength;

    public static String getGoodsType() {
        return GOODS_TYPE;
    }

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public double getNumberOfSpeakers() {
        return numberOfSpeakers;
    }

    public void setNumberOfSpeakers(double numberOfSpeakers) {
        this.numberOfSpeakers = numberOfSpeakers;
    }

    public String getFreqencyRange() {
        return freqencyRange;
    }

    public void setFreqencyRange(String freqencyRange) {
        this.freqencyRange = freqencyRange;
    }

    public double getCordLength() {
        return cordLength;
    }

    public void setCordLength(double cordLength) {
        this.cordLength = cordLength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Speakers)) return false;

        Speakers speakers = (Speakers) o;

        return new EqualsBuilder()
                .append(getPowerConsumption(), speakers.getPowerConsumption())
                .append(getNumberOfSpeakers(), speakers.getNumberOfSpeakers())
                .append(getCordLength(), speakers.getCordLength())
                .append(getFreqencyRange(), speakers.getFreqencyRange())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getPowerConsumption())
                .append(getNumberOfSpeakers())
                .append(getFreqencyRange())
                .append(getCordLength())
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(GOODS_TYPE);
        sb.append(": powerConsumption=").append(powerConsumption);
        sb.append(", numberOfSpeakers=").append(numberOfSpeakers);
        sb.append(", freqencyRange=").append(freqencyRange);
        sb.append(", cordLength=").append(cordLength);
        sb.append(';');
        return sb.toString();
    }
}
