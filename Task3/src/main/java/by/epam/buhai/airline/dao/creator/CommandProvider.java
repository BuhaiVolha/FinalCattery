package by.epam.buhai.airline.dao.creator;

import by.epam.buhai.airline.entity.Specification;
import java.util.EnumMap;

public class CommandProvider {
    private EnumMap<Specification.PlaneTypes, Command> commands = new EnumMap<>(Specification.PlaneTypes.class);

    CommandProvider() {
        commands.put(Specification.PlaneTypes.FREIGHTER, new FreighterCommand());
        commands.put(Specification.PlaneTypes.AIRPLANE, new AirplaneCommand());
        commands.put(Specification.PlaneTypes.COMMERCIAL_SPACEPLANE, new SpaceplaneCommand());
        commands.put(Specification.PlaneTypes.BUSINESS_JET, new BusinessJetCommand());
    }

    public Command getCommandFor(String planeType) {
        return commands.get(Specification.PlaneTypes.valueOf(planeType));
    }
}
