package by.tc.task01.dao.creator.withSettingFields;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<String, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put("Oven", new OvenCommand());
        commands.put("Laptop", new LaptopCommand());
        commands.put("Refrigerator", new RefrigeratorCommand());
        commands.put("TabletPC", new TabletPCCommand());
        commands.put("VacuumCleaner", new VacuumCleanerCommand());
        commands.put("Speakers", new SpeakersCommand());
        commands.put("TextBook", new TextBookCommand());
        commands.put("Newspaper", new NewspaperCommand());
    }

    public Command getCommandFor(String goodsType) {
        return commands.get(goodsType);
    }
}
