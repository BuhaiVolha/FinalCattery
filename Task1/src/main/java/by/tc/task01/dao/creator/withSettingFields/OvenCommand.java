package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.Oven;
import by.tc.task01.entity.criteria.Parameters;

import java.util.Map;

public class OvenCommand extends Command {
    @Override
    public Goods createGoodsWith(Map<String, String> parameters) {
        Oven oven = new Oven();

        oven.setCapacity(Double.parseDouble(parameters.get(Parameters.Oven.CAPACITY.toString())));
        oven.setDepth(Double.parseDouble(parameters.get(Parameters.Oven.DEPTH.toString())));
        oven.setWeight(Double.parseDouble(parameters.get(Parameters.Oven.WEIGHT.toString())));
        oven.setHeight(Double.parseDouble(parameters.get(Parameters.Oven.HEIGHT.toString())));
        oven.setPowerConsumption(Double.parseDouble(parameters.get(Parameters.Oven.POWER_CONSUMPTION.toString())));
        oven.setWidth(Double.parseDouble(parameters.get(Parameters.Oven.WIDTH.toString())));

        return oven;
    }
}
