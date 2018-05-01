package by.tc.task01.dao.creator.withReflection;

import by.tc.task01.dao.creator.Creator;

import by.tc.task01.dao.utils.GoodsParser;
import by.tc.task01.entity.*;
import by.tc.task01.exception.ItemCreationFailedException;
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

    public Sellable createGoodsAndParameterize(String type, Map<String, String> parameters) throws ItemCreationFailedException {
        Sellable sellable = null;

        switch (type) {
            case "Oven":
                sellable = new Oven();
                break;
            case "Laptop":
                sellable = new Laptop();
                break;
            case "Refrigerator":
                sellable = new Refrigerator();
                break;
            case "TabletPC":
                sellable = new TabletPC();
                break;
            case "VacuumCleaner":
                sellable = new VacuumCleaner();
                break;
            case "Speakers":
                sellable = new Speakers();
                break;
            case "TextBook":
                sellable = new TextBook();
                break;
            case "Newspaper":
                sellable = new Newspaper();
                break;
        }
        parameterize(sellable, parameters);

        return sellable;
    }


    private void parameterize(Sellable sellable, Map<String, String> parameters) throws ItemCreationFailedException {
        parameters = parser.makeKeysLookLikeFields(parameters);

        try {
            parameterizeUsingReflection(sellable, parameters);

        } catch (Exception e) {
            LOGGER.log(Level.ERROR, sellable.getClass().getName() + " creation failed");
            throw new ItemCreationFailedException();
        }
    }


    private void parameterizeUsingReflection(Sellable sellable, Map<String, String> parameters) throws ReflectiveOperationException {
        for (String parameterName : parameters.keySet()) {
            Field field = findField(sellable, parameterName);

            if (field.getType().getName().equals(FIELD_TYPE_DOUBLE)) {
                field.set(sellable, Double.parseDouble(parameters.get(parameterName)));
            } else {
                field.set(sellable, parameters.get(parameterName));
            }
        }
    }


    private Field findField(Sellable sellable, String parameterName) throws NoSuchFieldException {
        Field field = sellable.getClass().getDeclaredField(parameterName);
        field.setAccessible(true);

        return field;
    }
}
