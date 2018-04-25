package by.tc.task01.dao.creator.withReflection;

import by.tc.task01.dao.creator.Creator;

import by.tc.task01.entity.*;
import by.tc.task01.exception.ItemCreationFailedException;
import by.tc.task01.main.Main;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;

import java.util.HashMap;
import java.util.Map;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreatorWithReflection extends Creator {
    private static final Logger LOGGER = LogManager.getLogger(CreatorWithReflection.class);

    public CreatorWithReflection() {
    }

    public Goods createGoodsAndParameterize(String type, Map<String, String> parameters) throws ItemCreationFailedException {
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


    private void parameterize(Goods goods, Map<String, String> parameters) throws ItemCreationFailedException {
        parameters = makeKeysLookLikeFields(parameters);

        try {
            parameterizeUsingReflection(goods, parameters);

        } catch (Exception e) {
            LOGGER.log(Level.ERROR, goods.getClass().getName() + " creation failed");
            throw new ItemCreationFailedException();
        }
    }

    private Map<String, String> makeKeysLookLikeFields(Map<String, String> parameters) {
        Map<String, String> convertedParameters = new HashMap<>();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            convertedParameters.put(formatKey(entry.getKey()), entry.getValue());
        }
        return convertedParameters;
    }


    // converts a string like power_consumption
    // into a string powerConsumption
    // in order to match the fields of the class

    private String formatKey(String key) {
        key = key.toLowerCase();
        Pattern p = Pattern.compile("_([a-zA-Z])");
        Matcher m = p.matcher(key);
        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase());
        }
        m.appendTail(sb);

        return new String(sb);
    }


    private void parameterizeUsingReflection(Goods goods, Map<String, String> parameters) throws ReflectiveOperationException {
        for (String parameterName : parameters.keySet()) {
            Field field = findField(goods, parameterName);

            if (field.getType().getName().equals("double")) {
                field.set(goods, Double.valueOf(parameters.get(parameterName)));
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
