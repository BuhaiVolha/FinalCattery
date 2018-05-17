package by.tc.task01.dao.creator.withReflection;

import by.tc.task01.dao.creator.Creator;
import by.tc.task01.dao.dao_exception.GoodsCreationFailedException;
import by.tc.task01.dao.utils.GoodsParser;
import by.tc.task01.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.Field;
import java.util.Map;


public class CreatorWithReflection extends Creator {
    private static final Logger LOGGER = LogManager.getLogger(CreatorWithReflection.class);
    private GoodsParser parser = GoodsParser.getParser();
    private static final String FIELD_TYPE_DOUBLE = "double";

    public CreatorWithReflection() {
    }

    public Goods createGoodsAndParameterize(String type, Map<String, String> parameters) throws GoodsCreationFailedException {
        Goods goods = null;

        switch (type) {
            case "Oven":
                goods = new Oven();
                break;
            case "Laptop":
                goods = new Laptop();
                break;
            case "Refrigerator":
                goods = new Refrigerator();
                break;
            case "TabletPC":
                goods = new TabletPC();
                break;
            case "VacuumCleaner":
                goods = new VacuumCleaner();
                break;
            case "Speakers":
                goods = new Speakers();
                break;
            case "TextBook":
                goods = new TextBook();
                break;
            case "Newspaper":
                goods = new Newspaper();
                break;
        }
        parameterize(goods, parameters);

        return goods;
    }


    private void parameterize(Goods goods, Map<String, String> parameters) throws GoodsCreationFailedException {
        parameters = parser.makeKeysLookLikeFields(parameters);

        try {
            parameterizeUsingReflection(goods, parameters);

        } catch (Exception e) {
            LOGGER.log(Level.ERROR, goods.getClass().getName() + " creation failed");
            throw new GoodsCreationFailedException();
        }
    }


    private void parameterizeUsingReflection(Goods goods, Map<String, String> parameters) throws ReflectiveOperationException {
        for (String parameterName : parameters.keySet()) {
            Field field = findField(goods, parameterName);

            if (field.getType().getName().equals(FIELD_TYPE_DOUBLE)) {
                field.set(goods, Double.parseDouble(parameters.get(parameterName)));
            } else {
                field.set(goods, parameters.get(parameterName));
            }
        }
    }


    private Field findField(Goods goods, String parameterName) throws NoSuchFieldException {
        Field field = goods.getClass().getDeclaredField(parameterName);
        field.setAccessible(true);

        return field;
    }
}
