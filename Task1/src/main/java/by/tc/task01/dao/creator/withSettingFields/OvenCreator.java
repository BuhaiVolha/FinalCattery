package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.Oven;
import by.tc.task01.entity.criteria.Parameters;

import java.util.Map;

public class OvenCreator implements Creatable {
    @Override
    public Goods parameterize(Map<String, String> parameters) {
        Oven oven = new Oven();

        oven.setCapacity(Double.valueOf(parameters.get(Parameters.GoodsType.Oven.CAPACITY.toString())));
        oven.setDepth(Double.valueOf(parameters.get(Parameters.GoodsType.Oven.DEPTH.toString())));
        oven.setWeight(Double.valueOf(parameters.get(Parameters.GoodsType.Oven.WEIGHT.toString())));
        oven.setHeight(Double.valueOf(parameters.get(Parameters.GoodsType.Oven.HEIGHT.toString())));
        oven.setPowerConsumption(Double.valueOf(parameters.get(Parameters.GoodsType.Oven.POWER_CONSUMPTION.toString())));
        oven.setWidth(Double.valueOf(parameters.get(Parameters.GoodsType.Oven.WIDTH.toString())));

        return oven;
    }
}
