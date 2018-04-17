package com.epam.training.oop.entity.device;

import java.util.Objects;

public class Oven extends Device {
    private static final String GOODS_TYPE = "Oven";
    private double powerConsumption;
    private double weight;
    private double capacity;
    private double depth;
    private double height;
    private double width;

    public Oven() {
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oven oven = (Oven) o;
        return Double.compare(oven.powerConsumption, powerConsumption) == 0 &&
                Double.compare(oven.weight, weight) == 0 &&
                Double.compare(oven.capacity, capacity) == 0 &&
                Double.compare(oven.depth, depth) == 0 &&
                Double.compare(oven.height, height) == 0 &&
                Double.compare(oven.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(powerConsumption, weight, capacity, depth, height, width);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(GOODS_TYPE + ": ");
        sb.append("POWER_CONSUMPTION=" + powerConsumption + ", ");
        sb.append("WEIGHT=" + weight + ", ");
        sb.append("CAPACITY=" + capacity + ", ");
        sb.append("DEPTH=" + depth + ", ");
        sb.append("HEIGHT=" + height + ", ");
        sb.append("WIDTH=" + width + ";");
        return sb.toString();
    }
}
