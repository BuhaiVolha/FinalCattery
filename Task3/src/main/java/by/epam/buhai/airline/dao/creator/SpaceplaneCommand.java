package by.epam.buhai.airline.dao.creator;

import by.epam.buhai.airline.dao.dto.DTO;
import by.epam.buhai.airline.dao.dto.SpaceplaneCommandParameters;
import by.epam.buhai.airline.entity.CommercialSpaceplane;
import by.epam.buhai.airline.entity.Plane;
import by.epam.buhai.airline.entity.Specification;

public class SpaceplaneCommand extends Command {

    @Override
    Plane createPlaneWith(DTO parameters) {
        SpaceplaneCommandParameters s = (SpaceplaneCommandParameters) parameters;
        return new CommercialSpaceplane(s.getName(),
                Specification.Manufacturers.valueOf(s.getManufacturer()),
                Integer.parseInt(s.getCrew()),
                Integer.parseInt(s.getMaxSpeedKmPerHour()),
                Integer.parseInt(s.getRangeKm()),
                Integer.parseInt(s.getFuelConsumptionLitersPerHour()),
                Integer.parseInt(s.getSeatingCapacity()),
                Specification.SpaceplaneTypes.valueOf(s.getSpaceplaneType()),
                Integer.parseInt(s.getMinutesInMicrogravity()),
                Integer.parseInt(s.getTicketPriceDollars()));
    }
}
