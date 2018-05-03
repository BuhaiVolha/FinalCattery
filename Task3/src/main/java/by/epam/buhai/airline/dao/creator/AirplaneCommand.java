package by.epam.buhai.airline.dao.creator;

import by.epam.buhai.airline.dao.dto.AirplaneCommandParameters;
import by.epam.buhai.airline.dao.dto.DTO;
import by.epam.buhai.airline.entity.Airplane;
import by.epam.buhai.airline.entity.Plane;
import by.epam.buhai.airline.entity.Specification;

public final class AirplaneCommand extends Command {

    @Override
    public Plane createPlaneWith(DTO parameters) {
        AirplaneCommandParameters a = (AirplaneCommandParameters) parameters;
        return new Airplane(a.getName(),
                Specification.Manufacturers.valueOf(a.getManufacturer()),
                Integer.parseInt(a.getCrew()),
                Integer.parseInt(a.getMaxSpeedKmPerHour()),
                Integer.parseInt(a.getRangeKm()),
                Integer.parseInt(a.getFuelConsumptionLitersPerHour()),
                Integer.parseInt(a.getSeatingCapacity()),
                Specification.AirplaneTypes.valueOf(a.getAirplaneType()),
                Specification.AirplaneBodyTypes.valueOf(a.getAirplaneBodyType()),
                Specification.AirplaneClassCabinsNumber.valueOf(a.getAirplaneClassCabinsNumber()));
    }
}
