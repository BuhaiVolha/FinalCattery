package by.epam.training.entity.electricAppliance;

public class Refrigerator extends ElectricAppliance {
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
        return Double.compare(that.getPowerConsumption(), getPowerConsumption()) == 0 &&
                Double.compare(that.getWeight(), getWeight()) == 0 &&
                Double.compare(that.getFreezerCapacity(), getFreezerCapacity()) == 0 &&
                Double.compare(that.getOverallCapacity(), getOverallCapacity()) == 0 &&
                Double.compare(that.getHeight(), getHeight()) == 0 &&
                Double.compare(that.getWidth(), getWidth()) == 0;
    }

    @Override
    public int hashCode() {
        final int prime = 29;
        int result = 1;

        long longBits = Double.doubleToLongBits(powerConsumption);
        result = (prime * result) + (int)(longBits - (longBits >>> 32));
        long longBits1 = Double.doubleToLongBits(weight);
        result = (prime * result) + (int)(longBits1 - (longBits1 >>> 32));
        long longBits2 = Double.doubleToLongBits(freezerCapacity);
        result = (prime * result) + (int)(longBits2 - (longBits2 >>> 32));
        long longBits3 = Double.doubleToLongBits(overallCapacity);
        result = (prime * result) + (int)(longBits3 - (longBits3 >>> 32));
        long longBits4 = Double.doubleToLongBits(height);
        result = (prime * result) + (int)(longBits4 - (longBits4 >>> 32));
        long longBits5 = Double.doubleToLongBits(width);
        result = (prime * result) + (int)(longBits5 - (longBits5 >>> 32));

        return result;
    }

    @Override
    public String toString() {
        return GOODS_TYPE + ": powerConsumption = "
                + powerConsumption
                + ", weight = " + weight
                + ", freezerCapacity = "
                + freezerCapacity
                + ", overallCapacity = "
                + overallCapacity
                + ", height = " + height
                + ", width = " + width
                + ';';
    }
}
