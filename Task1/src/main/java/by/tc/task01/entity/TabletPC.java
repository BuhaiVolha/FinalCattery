package by.tc.task01.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class TabletPC extends Appliance {
    private static final String GOODS_TYPE = "TabletPC";
    private double batteryCapacity;
    private double displayInches;
    private double memoryRom;
    private double flashMemoryCapacity;
    private String color;


    public String getGoodsType() {
        return GOODS_TYPE;
    }

    public double getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }

    public double getDisplayInches() {
        return displayInches;
    }

    public void setDisplayInches(double displayInches) {
        this.displayInches = displayInches;
    }

    public double getMemoryRom() {
        return memoryRom;
    }

    public void setMemoryRom(double memoryROM) {
        this.memoryRom = memoryROM;
    }

    public double getFlashMemoryCapacity() {
        return flashMemoryCapacity;
    }

    public void setFlashMemoryCapacity(double flashMemoryCapacity) {
        this.flashMemoryCapacity = flashMemoryCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        }
        TabletPC tabletPC = (TabletPC) o;

        return new EqualsBuilder()
                .append(getBatteryCapacity(), tabletPC.getBatteryCapacity())
                .append(getDisplayInches(), tabletPC.getDisplayInches())
                .append(getMemoryRom(), tabletPC.getMemoryRom())
                .append(getFlashMemoryCapacity(), tabletPC.getFlashMemoryCapacity())
                .append(getColor(), tabletPC.getColor())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getBatteryCapacity())
                .append(getDisplayInches())
                .append(getMemoryRom())
                .append(getFlashMemoryCapacity())
                .append(getColor())
                .toHashCode();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(GOODS_TYPE);

        sb.append(": battery capacity = ").append(batteryCapacity);
        sb.append(", display inches = ").append(displayInches);
        sb.append(", memoryROM = ").append(memoryRom);
        sb.append(", flash memory capacity = ").append(flashMemoryCapacity);
        sb.append(", color = ").append(color);
        sb.append(';');
        return new String(sb);
    }
}
