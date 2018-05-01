package by.epam.buhai.airline.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

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
}
