package by.tc.task01.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Oven implements Sellable {
    private static final String GOODS_TYPE = "Oven";
    private double powerConsumption;
    private double weight;
    private double capacity;
    private double depth;
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

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
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
        Oven oven = (Oven) o;

        return new EqualsBuilder()
                .append(getPowerConsumption(), oven.getPowerConsumption())
                .append(getWeight(), oven.getWeight())
                .append(getCapacity(), oven.getCapacity())
                .append(getDepth(), oven.getDepth())
                .append(getHeight(), oven.getHeight())
                .append(getWidth(), oven.getWidth())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getPowerConsumption())
                .append(getWeight())
                .append(getCapacity())
                .append(getDepth())
                .append(getHeight())
                .append(getWidth())
                .toHashCode();
    }

    @Override
    public String toString() {
        return GOODS_TYPE + ": power consumption = "
                + powerConsumption
                + ", weight = " + weight
                + ", capacity = " + capacity
                + ", depth = " + depth
                + ", height = " + height
                + ", width = " + width
                + ';';
    }
}
