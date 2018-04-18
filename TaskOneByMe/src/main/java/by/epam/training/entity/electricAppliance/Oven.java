package by.epam.training.entity.electricAppliance;

public class Oven extends ElectricAppliance {
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
        return Double.compare(oven.getPowerConsumption(), getPowerConsumption()) == 0 &&
                Double.compare(oven.getWeight(), getWeight()) == 0 &&
                Double.compare(oven.getCapacity(), getCapacity()) == 0 &&
                Double.compare(oven.getDepth(), getDepth()) == 0 &&
                Double.compare(oven.getHeight(), getHeight()) == 0 &&
                Double.compare(oven.getWidth(), getWidth()) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 29;
        int result = 1;

        long longBits = Double.doubleToLongBits(powerConsumption);
        result = (prime * result) + (int)(longBits - (longBits >>> 32));
        long longBits1 = Double.doubleToLongBits(weight);
        result = (prime * result) + (int)(longBits1 - (longBits1 >>> 32));
        long longBits2 = Double.doubleToLongBits(capacity);
        result = (prime * result) + (int)(longBits2 - (longBits2 >>> 32));
        long longBits3 = Double.doubleToLongBits(depth);
        result = (prime * result) + (int)(longBits3 - (longBits3 >>> 32));
        long longBits4 = Double.doubleToLongBits(height);
        result = (prime * result) + (int)(longBits4 - (longBits4 >>> 32));
        long longBits5 = Double.doubleToLongBits(width);
        result = (prime * result) + (int)(longBits5 - (longBits5 >>> 32));

        return result;
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
