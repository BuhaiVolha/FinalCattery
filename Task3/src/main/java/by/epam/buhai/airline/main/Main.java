package by.epam.buhai.airline.main;

import by.epam.buhai.airline.entity.Item;

import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        Item item1 = new Item(22, "t-shirt");
        Item item2 = new Item(33, "gloves");

        Comparator<Item> comparator = (o1, o2) -> (int)(o1.getItemId() - o2.getItemId());
        Comparator.comparing(Item::getItemId).thenComparing((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        Comparator.comparing(Item::getItemId).thenComparing(Item::getName);
        comparator.thenComparing(Item::getName);

    }
}
