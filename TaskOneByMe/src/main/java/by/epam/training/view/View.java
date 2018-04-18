package by.epam.training.view;

import java.util.List;

public class View {

    public static void print(Object object) {
        System.out.println(object);
    }

    public static <E> void printList(List<E> list) {
        for (E element : list) {
            System.out.println(element);
        }
    }
}
