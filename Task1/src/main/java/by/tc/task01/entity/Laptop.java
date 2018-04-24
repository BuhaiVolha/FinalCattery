package by.tc.task01.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Laptop extends Appliance {
    private static final String GOODS_TYPE = "Laptop";
    private double batteryCapacity;
    private String os;
    private double memoryRom;
    private double systemMemory;
    private double cpu;
    private double displayInches;

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public double getMemoryRom() {
        return memoryRom;
    }

    public void setMemoryRom(double memoryRom) {
        this.memoryRom = memoryRom;
    }

    public double getSystemMemory() {
        return systemMemory;
    }

    public void setSystemMemory(double systemMemory) {
        this.systemMemory = systemMemory;
    }

    public double getCpu() {
        return cpu;
    }

    public void setCpu(double cpu) {
        this.cpu = cpu;
    }

    public double getDisplayInches() {
        return displayInches;
    }

    public void setDisplayInches(double displayInches) {
        this.displayInches = displayInches;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        }
        Laptop laptop = (Laptop) o;

        return new EqualsBuilder()
                .append(getBatteryCapacity(), laptop.getBatteryCapacity())
                .append(getMemoryRom(), laptop.getMemoryRom())
                .append(getSystemMemory(), laptop.getSystemMemory())
                .append(getCpu(), laptop.getCpu())
                .append(getDisplayInches(), laptop.getDisplayInches())
                .append(getOs(), laptop.getOs())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getBatteryCapacity())
                .append(getOs())
                .append(getMemoryRom())
                .append(getSystemMemory())
                .append(getCpu())
                .append(getDisplayInches())
                .toHashCode();
    }

    @Override
    public String toString() {
        return GOODS_TYPE + ": battery capacity = "
                + batteryCapacity
                + ", os = " + os
                + ", memoryROM = " + memoryRom
                + ", system memory = " + systemMemory
                + ", cpu = " + cpu
                + ", display inches = "
                + displayInches
                + ';';
    }
}
