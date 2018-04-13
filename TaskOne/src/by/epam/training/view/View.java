package by.epam.training.view;

import by.epam.training.entity.Appliance;
import java.util.List;

public class View {

    public static void print(Object object) {
        System.out.println(object);
    }

    public static void printList(List<Appliance> list) {
        for (Appliance a : list) {
            System.out.println(a);
        }
    }
}

