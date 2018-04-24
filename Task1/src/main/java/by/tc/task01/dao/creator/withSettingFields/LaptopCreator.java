package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.Laptop;
import by.tc.task01.entity.criteria.Parameters;

import java.util.Map;

public class LaptopCreator implements Creatable {

    @Override
    public Goods parameterize(Map<String, String> parameters) {
        Laptop laptop = new Laptop();

        laptop.setBatteryCapacity(Double.valueOf(parameters.get(Parameters.GoodsType.Laptop.BATTERY_CAPACITY.toString())));
        laptop.setCpu(Double.valueOf(parameters.get(Parameters.GoodsType.Laptop.CPU.toString())));
        laptop.setDisplayInches(Double.valueOf(parameters.get(Parameters.GoodsType.Laptop.DISPLAY_INCHES.toString())));
        laptop.setMemoryRom(Double.valueOf(parameters.get(Parameters.GoodsType.Laptop.MEMORY_ROM.toString())));
        laptop.setSystemMemory(Double.valueOf(parameters.get(Parameters.GoodsType.Laptop.SYSTEM_MEMORY.toString())));
        laptop.setOs(parameters.get(Parameters.GoodsType.Laptop.OS.toString()));

        return laptop;
    }
}
