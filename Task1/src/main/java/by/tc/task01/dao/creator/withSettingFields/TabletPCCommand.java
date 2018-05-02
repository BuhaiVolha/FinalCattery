package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.TabletPC;
import by.tc.task01.entity.criteria.Parameters;

import java.util.Map;

public class TabletPCCommand extends Command {

    @Override
    public Goods createGoodsWith(Map<String, String> parameters) {
        TabletPC tabletPC = new TabletPC();

        tabletPC.setBatteryCapacity(Double.parseDouble(parameters.get(Parameters.TabletPC.BATTERY_CAPACITY.toString())));
        tabletPC.setColor(parameters.get(Parameters.TabletPC.COLOR.toString()));
        tabletPC.setDisplayInches(Double.parseDouble(parameters.get(Parameters.TabletPC.DISPLAY_INCHES.toString())));
        tabletPC.setFlashMemoryCapacity(Double.parseDouble(parameters.get(Parameters.TabletPC.FLASH_MEMORY_CAPACITY.toString())));
        tabletPC.setMemoryRom(Double.parseDouble(parameters.get(Parameters.TabletPC.MEMORY_ROM.toString())));

        return tabletPC;
    }
}
