package by.epam.training.entity.electricAppliance;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class TabletPC extends ElectricAppliance {
    private static final String GOODS_TYPE = "TabletPC";
    private double batteryCapacity;
    private double displayInches;
    private double memoryRom;
    private double flashMemoryCapacity;
    private String color;


    public static String getGoodsType() {
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
        if (this == o) return true;

        if (!(o instanceof TabletPC)) return false;

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

        sb.append(": batteryCapacity = ").append(batteryCapacity);
        sb.append(", displayInches = ").append(displayInches);
        sb.append(", memoryROM = ").append(memoryRom);
        sb.append(", flashMemoryCapacity = ").append(flashMemoryCapacity);
        sb.append(", color = ").append(color);
        sb.append(';');
        return new String(sb);
    }
}
