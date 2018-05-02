package by.tc.task01.dao.creator.withSettingFields;

import java.util.HashMap;
import java.util.Map;
import static by.tc.task01.entity.criteria.Parameters.*;

public class CommandProvider {
    private Map<String, Command> commands = new HashMap<>();

    CommandProvider() {
        commands.put(Oven.class.getSimpleName(), new OvenCommand());
        commands.put(Laptop.class.getSimpleName(), new LaptopCommand());
        commands.put(Refrigerator.class.getSimpleName(), new RefrigeratorCommand());
        commands.put(TabletPC.class.getSimpleName(), new TabletPCCommand());
        commands.put(VacuumCleaner.class.getSimpleName(), new VacuumCleanerCommand());
        commands.put(Speakers.class.getSimpleName(), new SpeakersCommand());
        commands.put(TextBook.class.getSimpleName(), new TextBookCommand());
        commands.put(Newspaper.class.getSimpleName(), new NewspaperCommand());
    }

    public Command getCommandFor(String goodsType) {
        return commands.get(goodsType);
    }
}
