package by.epam.buhai.airline.dao.dto;

import java.util.Map;

public final class FreighterCommandParameters extends DTO {
    private Map<String, String> parameters;

    private String name;
    private String manufacturer;
    private String crew;
    private String maxSpeedKmPerHour;
    private String rangeKm;
    private String fuelConsumptionLitersPerHour;
    private String cargoPlaneType;
    private String cargoTones;
    private String cargoVolumeSquareMeter;

    public FreighterCommandParameters(Map<String, String> parameters) {
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
        cargoPlaneType = parameters.get("cargoPlaneType");
        cargoTones = parameters.get("cargoTones");
        cargoVolumeSquareMeter = parameters.get("cargoVolumeSquareMeter");
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

    public String getCargoPlaneType() {
        return cargoPlaneType;
    }

    public String getCargoTones() {
        return cargoTones;
    }

    public String getCargoVolumeSquareMeter() {
        return cargoVolumeSquareMeter;
    }
}
