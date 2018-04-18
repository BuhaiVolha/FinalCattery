package by.epam.training.entity.electricAppliance;

import java.util.Objects;

public class Laptop extends ElectricAppliance {
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
        return Double.compare(laptop.getBatteryCapacity(), getBatteryCapacity()) == 0 &&
                Double.compare(laptop.getMemoryRom(), getMemoryRom()) == 0 &&
                Double.compare(laptop.getSystemMemory(), getSystemMemory()) == 0 &&
                Double.compare(laptop.getCpu(), getCpu()) == 0 &&
                Double.compare(laptop.getDisplayInches(), getDisplayInches()) == 0 &&
                Objects.equals(getOs(), laptop.getOs());
    }

    @Override
    public int hashCode() {
        final int prime = 29;
        int result = 1;

        long longBits = Double.doubleToLongBits(batteryCapacity);
        result = (prime * result) + (int)(longBits - (longBits >>> 32));
        result = (prime * result) + (os == null ? 0 : os.hashCode());
        long longBits2 = Double.doubleToLongBits(memoryRom);
        result = (prime * result) + (int)(longBits2 - (longBits2 >>> 32));
        long longBits3 = Double.doubleToLongBits(systemMemory);
        result = (prime * result) + (int)(longBits3 - (longBits3 >>> 32));
        long longBits4 = Double.doubleToLongBits(cpu);
        result = (prime * result) + (int)(longBits4 - (longBits4 >>> 32));
        long longBits5 = Double.doubleToLongBits(displayInches);
        result = (prime * result) + (int)(longBits5 - (longBits5 >>> 32));

        return result;
    }

    @Override
    public String toString() {
        return GOODS_TYPE + ": batteryCapacity = "
                + batteryCapacity
                + ", os = " + os
                + ", memoryROM = " + memoryRom
                + ", systemMemory = " + systemMemory
                + ", cpu = " + cpu
                + ", displayInches = "
                + displayInches
                + ';';
    }
}
