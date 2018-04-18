package by.epam.training.utils;

import by.epam.training.entity.*;
import by.epam.training.entity.electricAppliance.*;
import by.epam.training.entity.paperProduct.Newspaper;
import by.epam.training.entity.paperProduct.TextBook;

import by.epam.training.exception.ItemCreationFailedException;

import java.lang.reflect.Field;
import java.util.Map;

public class GoodsCreator {
    private static GoodsCreator instance;

    private GoodsCreator() {}

    public static GoodsCreator getInstance() {

        if (instance == null) {
            instance = new GoodsCreator();
        }
        return instance;
    }


    // this method creates a certain type of appliance
    // based on enum types
    // then parameterizes it using Map parameters
    // which consist of keys and values

    public Goods createGoods(GoodsType type, Map<String, String> parameters) throws ItemCreationFailedException {
        Goods goods = null;

        switch (type) {
            case OVEN:
                goods = new Oven();
                break;
            case LAPTOP:
                goods = new Laptop();
                break;
            case REFRIGERATOR:
                goods = new Refrigerator();
                break;
            case TABLET_PC:
                goods = new TabletPC();
                break;
            case VACUUM_CLEANER:
                goods = new VacuumCleaner();
                break;
            case SPEAKERS:
                goods = new Speakers();
                break;
            case TEXTBOOK:
                goods = new TextBook();
                break;
            case NEWSPAPER:
                goods = new Newspaper();
                break;
        }

        parameterize(goods, parameters);
        return goods;
    }


    // this method uses a reflection to access class fields
    // and set values from Map parameters

    private void parameterize(Goods goods, Map<String, String> parameters) throws ItemCreationFailedException {
        try {
            for (String key : parameters.keySet()) {
                Field field = goods.getClass().getDeclaredField(key);
                field.setAccessible(true);

                if (field.getType().getName().equals("double")) {
                    field.set(goods, Double.valueOf(parameters.get(key)));
                } else {
                    field.set(goods, parameters.get(key));
                }
            }
        } catch (Exception e) {
            throw new ItemCreationFailedException(goods.getClass().getName() + " creation failed");
        }
    }
}
