package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.Refrigerator;
import by.tc.task01.entity.criteria.Parameters;

import java.util.Map;

public class RefrigeratorCreator implements Creatable {
    @Override
    public Goods parameterize(Map<String, String> parameters) {
        Refrigerator refrigerator = new Refrigerator();

        refrigerator.setFreezerCapacity(Double.valueOf(parameters.get(Parameters.GoodsType.Refrigerator.FREEZER_CAPACITY.toString())));
        refrigerator.setOverallCapacity(Double.valueOf(parameters.get(Parameters.GoodsType.Refrigerator.OVERALL_CAPACITY.toString())));
        refrigerator.setWeight(Double.valueOf(parameters.get(Parameters.GoodsType.Refrigerator.WEIGHT.toString())));
        refrigerator.setHeight(Double.valueOf(parameters.get(Parameters.GoodsType.Refrigerator.HEIGHT.toString())));
        refrigerator.setPowerConsumption(Double.valueOf(parameters.get(Parameters.GoodsType.Refrigerator.POWER_CONSUMPTION.toString())));
        refrigerator.setWidth(Double.valueOf(parameters.get(Parameters.GoodsType.Refrigerator.WIDTH.toString())));

        return refrigerator;
    }
}
