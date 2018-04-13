package by.epam.training.entity;

import by.epam.training.exception.ItemCreationFailedException;
import java.lang.reflect.Field;
import java.util.Map;

public class ApplianceCreator {
    private static ApplianceCreator instance;

    private ApplianceCreator() {}

    public static ApplianceCreator getInstance() {

        if (instance == null) {
            instance = new ApplianceCreator();
        }
        return instance;
    }


    // this method creates a certain type of appliance
    // based on enum types
    // then parameterizes it using Map parameters
    // which consist of keys and values
    // for example, DEPTH=33
    public Appliance createAppliance(ApplianceType type, Map<String, String> parameters) throws ItemCreationFailedException {
        Appliance appliance = null;

        switch (type) {
            case OVEN:
                appliance = new Oven();
                break;
            case LAPTOP:
                appliance = new Laptop();
                break;
            case REFRIGERATOR:
                appliance = new Refrigerator();
        }

        parameterize(appliance, parameters);
        return appliance;
    }


    // this method uses a reflection to access class fields
    // and set values from Map parameters
    private void parameterize(Appliance appliance, Map<String, String> parameters) throws ItemCreationFailedException {
        try {
            for (String key : parameters.keySet()) {
                Field field = appliance.getClass().getDeclaredField(key);
                field.setAccessible(true);

                if (field.getType().getName().equals("double")) {
                    field.set(appliance, Double.valueOf(parameters.get(key)));
                } else {
                    field.set(appliance, parameters.get(key));
                }
            }
        } catch (Exception e) {
            throw new ItemCreationFailedException(appliance.getClass().getName() + " creation failed");
        }
    }
}
