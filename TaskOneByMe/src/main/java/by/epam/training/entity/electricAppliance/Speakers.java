package by.epam.training.entity.electricAppliance;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Speakers extends ElectricAppliance {
    private static final String GOODS_TYPE = "Speakers";
    private double powerConsumption;
    private double numberOfSpeakers;
    private String frequencyRange;
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

    public String getFrequencyRange() {
        return frequencyRange;
    }

    public void setFrequencyRange(String frequencyRange) {
        this.frequencyRange = frequencyRange;
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
                .append(getFrequencyRange(), speakers.getFrequencyRange())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getPowerConsumption())
                .append(getNumberOfSpeakers())
                .append(getFrequencyRange())
                .append(getCordLength())
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(GOODS_TYPE);
        sb.append(": power consumption = ").append(powerConsumption);
        sb.append(", number of speakers = ").append(numberOfSpeakers);
        sb.append(", frequency range = ").append(frequencyRange);
        sb.append(", cord length = ").append(cordLength);
        sb.append(';');
        return sb.toString();
    }
}
