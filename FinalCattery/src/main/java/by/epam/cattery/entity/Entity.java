package by.epam.cattery.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

public class Entity implements Serializable {
    private static final long serialVersionUID = 4852012814608236102L;
    private int id;
    /*
    If they are nullable in the database, then use wrappers. If they are not nullable, and you use wrappers,
    then you'll get an exception if you try and insert a null into the database.
    If your data model doesn't dictate it, then go for a convention, use wrappers all of the time.
    That way people don't have to think, or decide that a value of 0 means null.
    */

    public Entity(int id) {
        this.id = id;
    }

    public Entity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || (getClass() != o.getClass())) return false;
        if (this == o) return true;

        Entity entity = (Entity) o;

        return new EqualsBuilder()
                .append(getId(), entity.getId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .toString();
    }
}
