package by.epam.buhai.airline.dao.creator;

import by.epam.buhai.airline.dao.Creator;
import by.epam.buhai.airline.dao.dto.AirplaneCommandParameters;
import by.epam.buhai.airline.dao.dto.DTO;
import by.epam.buhai.airline.dao.dto.FreighterCommandParameters;
import by.epam.buhai.airline.dao.dto.SpaceplaneCommandParameters;
import by.epam.buhai.airline.entity.Plane;
import by.epam.buhai.airline.entity.Specification;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;

public final class CreatorWithCommand extends Creator {
    private static final Logger LOGGER = LogManager.getLogger(CreatorWithCommand.class);
    private CommandProvider provider = new CommandProvider();

    @Override
    public Plane createPlaneAndParameterize(String planeType, DTO parameters) {
        Command command = provider.getCommandFor(planeType);
        return command.createPlaneWith(parameters);
    }

    public DTO createDTO(String planeType, Map<String, String> parameters) {
        DTO dto = null;

        switch (Specification.PlaneTypes.valueOf(planeType)) {
            case FREIGHTER:
                dto = new FreighterCommandParameters(parameters);
                break;
            case AIRPLANE:
                dto = new AirplaneCommandParameters(parameters);
                break;
            case COMMERCIAL_SPACEPLANE:
                dto = new SpaceplaneCommandParameters(parameters);
                break;
            default:
                LOGGER.log(Level.ERROR, "No such type of plane");
        }
        return dto;
    }
}
