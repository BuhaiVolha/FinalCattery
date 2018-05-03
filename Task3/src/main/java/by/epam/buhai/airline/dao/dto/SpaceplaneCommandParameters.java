package by.epam.buhai.airline.dao.dto;

import java.util.Map;

public class SpaceplaneCommandParameters extends DTO {
    private Map<String, String> parameters;

    private String name;
    private String manufacturer;
    private String crew;
    private String maxSpeedKmPerHour;
    private String rangeKm;
    private String fuelConsumptionLitersPerHour;
    private String seatingCapacity;
    private String spaceplaneType;
    private String minutesInMicrogravity;
    private String ticketPriceDollars;

    public SpaceplaneCommandParameters(Map<String, String> parameters) {
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
        spaceplaneType = parameters.get("spaceplaneType");
        minutesInMicrogravity = parameters.get("minutesInMicrogravity");
        ticketPriceDollars = parameters.get("ticketPriceDollars");
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

    public String getSpaceplaneType() {
        return spaceplaneType;
    }

    public String getMinutesInMicrogravity() {
        return minutesInMicrogravity;
    }

    public String getTicketPriceDollars() {
        return ticketPriceDollars;
    }
}
