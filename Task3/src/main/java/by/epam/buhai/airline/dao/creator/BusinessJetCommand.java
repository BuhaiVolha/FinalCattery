package by.epam.buhai.airline.dao.creator;

import by.epam.buhai.airline.dao.dto.BusinessJetCommandParameters;
import by.epam.buhai.airline.dao.dto.DTO;
import by.epam.buhai.airline.entity.BusinessJet;
import by.epam.buhai.airline.entity.Plane;
import by.epam.buhai.airline.entity.Specification;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BusinessJetCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger(SpaceplaneCommand.class);

    @Override
    public Plane createPlaneWith(DTO parameters) {
        BusinessJetCommandParameters b = (BusinessJetCommandParameters) parameters;
        BusinessJet businessJet = null;

        try {
            businessJet = new BusinessJet(b.getName(),
                    Specification.Manufacturers.valueOf(b.getManufacturer()),
                    Integer.parseInt(b.getCrew()),
                    Integer.parseInt(b.getMaxSpeedKmPerHour()),
                    Integer.parseInt(b.getRangeKm()),
                    Integer.parseInt(b.getFuelConsumptionLitersPerHour()),
                    Integer.parseInt(b.getSeatingCapacity()),
                    Specification.BusinessJetTypes.valueOf(b.getBusinessJetType()),
                    Boolean.parseBoolean(b.getHasConferenceArea()),
                    Double.parseDouble(b.getUnitCostMlnDollars()));

        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.ERROR, "Business Jet parsing has failed");
        }
        return businessJet;
    }
}
