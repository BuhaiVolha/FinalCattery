package com.epam.training.oop.entity.device;

import java.util.Objects;

public class Refrigerator extends Device {
    private static final String GOODS_TYPE = "Refrigerator";
    private double powerConsumption;
    private double weight;
    private double freezerCapacity;
    private double overallCapacity;
    private double height;
    private double width;

    public Refrigerator() {
    }


    public static String getGoodsType() {
        return GOODS_TYPE;
    }

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Refrigerator that = (Refrigerator) o;
        return Double.compare(that.powerConsumption, powerConsumption) == 0 &&
                Double.compare(that.weight, weight) == 0 &&
                Double.compare(that.freezerCapacity, freezerCapacity) == 0 &&
                Double.compare(that.overallCapacity, overallCapacity) == 0 &&
                Double.compare(that.height, height) == 0 &&
                Double.compare(that.width, width) == 0;
    }

    @Override
    public int hashCode() {

        return Objects.hash(powerConsumption, weight, freezerCapacity, overallCapacity, height, width);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(GOODS_TYPE + ": ");
        sb.append("POWER_CONSUMPTION=" + powerConsumption + ", ");
        sb.append("WEIGHT=" + weight + ", ");
        sb.append("FREEZER_CAPACITY=" + freezerCapacity + ", ");
        sb.append("OVERALL_CAPACITY=" + overallCapacity + ", ");
        sb.append("HEIGHT=" + height + ", ");
        sb.append("WIDTH=" + width + ";");

        return sb.toString();
    }
}
