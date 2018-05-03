package by.epam.buhai.airline.dao.creator;

import by.epam.buhai.airline.dao.dto.DTO;
import by.epam.buhai.airline.dao.dto.FreighterCommandParameters;
import by.epam.buhai.airline.entity.Freighter;
import by.epam.buhai.airline.entity.Plane;
import by.epam.buhai.airline.entity.Specification;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class FreighterCommand extends Command {
    private static final Logger LOGGER = LogManager.getLogger(SpaceplaneCommand.class);

    @Override
    public Plane createPlaneWith(DTO parameters) {

        FreighterCommandParameters f = (FreighterCommandParameters) parameters;
        Freighter freighter = null;
        try {
            freighter = new Freighter(f.getName(),
                    Specification.Manufacturers.valueOf(f.getManufacturer()),
                    Integer.parseInt(f.getCrew()),
                    Integer.parseInt(f.getMaxSpeedKmPerHour()),
                    Integer.parseInt(f.getRangeKm()),
                    Integer.parseInt(f.getFuelConsumptionLitersPerHour()),
                    Specification.CargoPlaneTypes.valueOf(f.getCargoPlaneType()),
                    Integer.parseInt(f.getCargoTones()),
                    Integer.parseInt(f.getCargoVolumeSquareMeter()));
        } catch (IllegalArgumentException e) {
            LOGGER.log(Level.ERROR, "Freighter parsing has failed");
        }
        return freighter;
    }
}
