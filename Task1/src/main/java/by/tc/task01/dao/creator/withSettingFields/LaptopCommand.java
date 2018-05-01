package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.criteria.Parameters;

import java.util.Map;

public class LaptopCommand extends Command {

    @Override
    public Goods createGoodsWith(Map<String, String> parameters) {
        by.tc.task01.entity.Laptop laptop = new by.tc.task01.entity.Laptop();

        laptop.setBatteryCapacity(Double.parseDouble(parameters.get(Parameters.GoodsType.Laptop.BATTERY_CAPACITY.toString())));
        laptop.setCpu(Double.parseDouble(parameters.get(Parameters.GoodsType.Laptop.CPU.toString())));
        laptop.setDisplayInches(Double.parseDouble(parameters.get(Parameters.GoodsType.Laptop.DISPLAY_INCHES.toString())));
        laptop.setMemoryRom(Double.parseDouble(parameters.get(Parameters.GoodsType.Laptop.MEMORY_ROM.toString())));
        laptop.setSystemMemory(Double.parseDouble(parameters.get(Parameters.GoodsType.Laptop.SYSTEM_MEMORY.toString())));
        laptop.setOs(parameters.get(Parameters.GoodsType.Laptop.OS.toString()));

        return laptop;
    }
}
