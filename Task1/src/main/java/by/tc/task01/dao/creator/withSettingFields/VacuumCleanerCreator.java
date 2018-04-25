package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.VacuumCleaner;
import by.tc.task01.entity.criteria.Parameters;

import java.util.Map;

public class VacuumCleanerCreator implements Creatable {

    @Override
    public Goods parameterize(Map<String, String> parameters) {
        VacuumCleaner vacuumCleaner = new VacuumCleaner();

        vacuumCleaner.setBagType(parameters.get(Parameters.GoodsType.VacuumCleaner.BAG_TYPE.toString()));
        vacuumCleaner.setCleaningWidth(Double.parseDouble(parameters.get(Parameters.GoodsType.VacuumCleaner.CLEANING_WIDTH.toString())));
        vacuumCleaner.setFilterType(parameters.get(Parameters.GoodsType.VacuumCleaner.FILTER_TYPE.toString()));
        vacuumCleaner.setMotorSpeedRegulation(Double.parseDouble(parameters.get(Parameters.GoodsType.VacuumCleaner.MOTOR_SPEED_REGULATION.toString())));
        vacuumCleaner.setPowerConsumption(Double.parseDouble(parameters.get(Parameters.GoodsType.VacuumCleaner.POWER_CONSUMPTION.toString())));
        vacuumCleaner.setWandType(parameters.get(Parameters.GoodsType.VacuumCleaner.WAND_TYPE.toString()));

        return vacuumCleaner;
    }
}
