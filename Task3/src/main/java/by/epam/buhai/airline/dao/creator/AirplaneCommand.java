package by.epam.buhai.airline.dao.creator;

import by.epam.buhai.airline.dao.dto.AirplaneCommandParameters;
import by.epam.buhai.airline.dao.dto.DTO;
import by.epam.buhai.airline.entity.Airplane;
import by.epam.buhai.airline.entity.Plane;
import by.epam.buhai.airline.entity.Specification;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class AirplaneCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger(SpaceplaneCommand.class);

    @Override
    public Plane createPlaneWith(DTO parameters) {
        AirplaneCommandParameters a = (AirplaneCommandParameters) parameters;
        Airplane airplane = null;

        try {
            airplane = new Airplane(a.getName(),
                    Specification.Manufacturers.valueOf(a.getManufacturer()),
                    Integer.parseInt(a.getCrew()),
                    Integer.parseInt(a.getMaxSpeedKmPerHour()),
                    Integer.parseInt(a.getRangeKm()),
                    Integer.parseInt(a.getFuelConsumptionLitersPerHour()),
                    Integer.parseInt(a.getSeatingCapacity()),
                    Specification.AirplaneTypes.valueOf(a.getAirplaneType()),
                    Specification.AirplaneBodyTypes.valueOf(a.getAirplaneBodyType()),
                    Specification.AirplaneClassCabinsNumber.valueOf(a.getAirplaneClassCabinsNumber()));
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.ERROR, "Airplane creation has failed");
        }
        return airplane;
    }
}
