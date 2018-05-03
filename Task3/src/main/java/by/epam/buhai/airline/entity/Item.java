package by.epam.buhai.airline.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Comparator;

// not for task
public class Item {
    private long itemId;
    private String name;

    public Item(long itemId, String name) {
        this.itemId = itemId;
        this.name = name;
    }

    public Item() {
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        return new EqualsBuilder()
                .append(getItemId(), item.getItemId())
                .append(getName(), item.getName())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getItemId())
                .append(getName())
                .toHashCode();
    }

    public static void main(String[] args) {
        Item item1 = new Item(22, "t-shirt");
        Item item2 = new Item(33, "gloves");

        Comparator<Item> comparator = (o1, o2) -> (int)(o1.getItemId() - o2.getItemId());
        Comparator.comparing(Item::getItemId).thenComparing((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        Comparator.comparing(Item::getItemId).thenComparing(Item::getName);
        comparator.thenComparing(Item::getName);

    }
}
