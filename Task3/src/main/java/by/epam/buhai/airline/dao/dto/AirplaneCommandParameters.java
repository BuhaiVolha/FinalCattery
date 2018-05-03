package by.epam.buhai.airline.dao.dto;

import java.util.Map;

public final class AirplaneCommandParameters extends DTO {
    private Map<String, String> parameters;

    private String name;
    private String manufacturer;
    private String crew;
    private String maxSpeedKmPerHour;
    private String rangeKm;
    private String fuelConsumptionLitersPerHour;
    private String seatingCapacity;
    private String airplaneType;
    private String airplaneBodyType;
    private String airplaneClassCabinsNumber;

    public AirplaneCommandParameters(Map<String, String> parameters) {
        this.parameters = parameters;
        initFields();
    }

     public final void initFields() {
        name = parameters.get("name");
        manufacturer = parameters.get("manufacturer");
        crew = parameters.get("crew");
        maxSpeedKmPerHour = parameters.get("maxSpeedKmPerHour");
        rangeKm = parameters.get("rangeKm");
        fuelConsumptionLitersPerHour = parameters.get("fuelConsumptionLitersPerHour");
        seatingCapacity = parameters.get("seatingCapacity");
        airplaneType = parameters.get("airplaneType");
        airplaneBodyType = parameters.get("airplaneBodyType");
        airplaneClassCabinsNumber = parameters.get("airplaneClassCabinsNumber");
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCrew() {
        return crew;
    }

    public String getMaxSpeedKmPerHour() {
        return maxSpeedKmPerHour;
    }

    public String getRangeKm() {
        return rangeKm;
    }

    public String getFuelConsumptionLitersPerHour() {
        return fuelConsumptionLitersPerHour;
    }

    public String getSeatingCapacity() {
        return seatingCapacity;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public String getAirplaneBodyType() {
        return airplaneBodyType;
    }

    public String getAirplaneClassCabinsNumber() {
        return airplaneClassCabinsNumber;
    }
}
