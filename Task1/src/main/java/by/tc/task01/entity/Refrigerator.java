package by.tc.task01.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Refrigerator implements Sellable {
    private static final String GOODS_TYPE = "Refrigerator";
    private double powerConsumption;
    private double weight;
    private double freezerCapacity;
    private double overallCapacity;
    private double height;
    private double width;

    public double getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(double powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getFreezerCapacity() {
        return freezerCapacity;
    }

    public void setFreezerCapacity(double freezerCapacity) {
        this.freezerCapacity = freezerCapacity;
    }

    public double getOverallCapacity() {
        return overallCapacity;
    }

    public void setOverallCapacity(double overallCapacity) {
        this.overallCapacity = overallCapacity;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        }
        Refrigerator that = (Refrigerator) o;

        return new EqualsBuilder()
                .append(getPowerConsumption(), that.getPowerConsumption())
                .append(getWeight(), that.getWeight())
                .append(getFreezerCapacity(), that.getFreezerCapacity())
                .append(getOverallCapacity(), that.getOverallCapacity())
                .append(getHeight(), that.getHeight())
                .append(getWidth(), that.getWidth())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getPowerConsumption())
                .append(getWeight())
                .append(getFreezerCapacity())
                .append(getOverallCapacity())
                .append(getHeight())
                .append(getWidth())
                .toHashCode();
    }

    @Override
    public String toString() {
        return GOODS_TYPE + ": power consumption = "
                + powerConsumption
                + ", weight = " + weight
                + ", freezer capacity = "
                + freezerCapacity
                + ", overall capacity = "
                + overallCapacity
                + ", height = " + height
                + ", width = " + width
                + ';';
    }
}
