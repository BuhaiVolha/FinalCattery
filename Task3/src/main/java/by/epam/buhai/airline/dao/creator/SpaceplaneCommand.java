package by.epam.buhai.airline.dao.creator;

import by.epam.buhai.airline.dao.dto.DTO;
import by.epam.buhai.airline.dao.dto.SpaceplaneCommandParameters;
import by.epam.buhai.airline.entity.CommercialSpaceplane;
import by.epam.buhai.airline.entity.Plane;
import by.epam.buhai.airline.entity.Specification;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class SpaceplaneCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger(SpaceplaneCommand.class);

    @Override
    public Plane createPlaneWith(DTO parameters) {

        SpaceplaneCommandParameters s = (SpaceplaneCommandParameters) parameters;
        CommercialSpaceplane spaceplane = null;
        try {
            spaceplane = new CommercialSpaceplane(s.getName(),
                    Specification.Manufacturers.valueOf(s.getManufacturer()),
                    Integer.parseInt(s.getCrew()),
                    Integer.parseInt(s.getMaxSpeedKmPerHour()),
                    Integer.parseInt(s.getRangeKm()),
                    Integer.parseInt(s.getFuelConsumptionLitersPerHour()),
                    Integer.parseInt(s.getSeatingCapacity()),
                    Specification.SpaceplaneTypes.valueOf(s.getSpaceplaneType()),
                    Integer.parseInt(s.getMinutesInMicrogravity()),
                    Integer.parseInt(s.getTicketPriceDollars()));

        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.ERROR, "Spaceplane parsing has failed");
        }
        return spaceplane;
    }
}
