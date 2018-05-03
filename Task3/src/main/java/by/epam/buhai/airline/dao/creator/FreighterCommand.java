package by.epam.buhai.airline.dao.creator;

import by.epam.buhai.airline.dao.dto.DTO;
import by.epam.buhai.airline.dao.dto.FreighterCommandParameters;
import by.epam.buhai.airline.entity.Freighter;
import by.epam.buhai.airline.entity.Plane;
import by.epam.buhai.airline.entity.Specification;

public class FreighterCommand extends Command {
    @Override
    Plane createPlaneWith(DTO parameters) {

        FreighterCommandParameters f = (FreighterCommandParameters) parameters;
        return new Freighter(f.getName(),
                Specification.Manufacturers.valueOf(f.getManufacturer()),
                Integer.parseInt(f.getCrew()),
                Integer.parseInt(f.getMaxSpeedKmPerHour()),
                Integer.parseInt(f.getRangeKm()),
                Integer.parseInt(f.getFuelConsumptionLitersPerHour()),
                Specification.CargoPlaneTypes.valueOf(f.getCargoPlaneType()),
                Integer.parseInt(f.getCargoTones()),
                Integer.parseInt(f.getCargoVolumeSquareMeter()));
    }
}
