package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.dao.creator.Creator;
import by.tc.task01.entity.Goods;

import java.util.HashMap;
import java.util.Map;

public class CreatorSettingFields extends Creator {
    private Map<String, Creatable> typeBuilders = new HashMap<>();

    public CreatorSettingFields() {
        initTypeInstances();
    }

    private void initTypeInstances() {
        typeBuilders.put("Oven", new OvenCreator());
        typeBuilders.put("Laptop", new LaptopCreator());
        typeBuilders.put("Refrigerator", new RefrigeratorCreator());
        typeBuilders.put("TabletPC", new TabletPCCreator());
        typeBuilders.put("VacuumCleaner", new VacuumCleanerCreator());
        typeBuilders.put("Speakers", new SpeakersCreator());
        typeBuilders.put("TextBook", new TextBookCreator());
        typeBuilders.put("Newspaper", new NewspaperCreator());
    }

    public Goods createGoodsAndParameterize(String goodsType, Map<String, String> parameters) {
        Creatable creatable = create(goodsType);
        return creatable.parameterize(parameters);
    }

    private Creatable create(String type) {
        return typeBuilders.get(type);
    }
}
