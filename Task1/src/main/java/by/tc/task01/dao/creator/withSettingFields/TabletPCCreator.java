package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.TabletPC;
import by.tc.task01.entity.criteria.Parameters;

import java.util.Map;

public class TabletPCCreator implements Creatable {

    @Override
    public Goods parameterize(Map<String, String> parameters) {
        TabletPC tabletPC = new TabletPC();

        tabletPC.setBatteryCapacity(Double.valueOf(parameters.get(Parameters.GoodsType.TabletPC.BATTERY_CAPACITY.toString())));
        tabletPC.setColor(parameters.get(Parameters.GoodsType.TabletPC.COLOR.toString()));
        tabletPC.setDisplayInches(Double.valueOf(parameters.get(Parameters.GoodsType.TabletPC.DISPLAY_INCHES.toString())));
        tabletPC.setFlashMemoryCapacity(Double.valueOf(parameters.get(Parameters.GoodsType.TabletPC.FLASH_MEMORY_CAPACITY.toString())));
        tabletPC.setMemoryRom(Double.valueOf(parameters.get(Parameters.GoodsType.TabletPC.MEMORY_ROM.toString())));

        return tabletPC;
    }
}
