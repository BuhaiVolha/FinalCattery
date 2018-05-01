package by.tc.task01.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;

public class Newspaper extends Goods implements Serializable {
    private static final String GOODS_TYPE = "Newspaper";
    private static final long serialVersionUID = -5732431683444802303L;
    private String title;
    private String periodicity;
    private String paidOrFree;

    public String getGoodsType() {
        return GOODS_TYPE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public String getPaidOrFree() {
        return paidOrFree;
    }

    public void setPaidOrFree(String paidOrFree) {
        this.paidOrFree = paidOrFree;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        if (this == o) {
            return true;
        }
        Newspaper newspaper = (Newspaper) o;

        return new EqualsBuilder()
                .append(getTitle(), newspaper.getTitle())
                .append(getPeriodicity(), newspaper.getPeriodicity())
                .append(getPaidOrFree(), newspaper.getPaidOrFree())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getTitle())
                .append(getPeriodicity())
                .append(getPaidOrFree())
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(GOODS_TYPE);
        sb.append(": title = ").append(title);
        sb.append(", periodicity = ").append(periodicity);
        sb.append(", paid/free = ").append(paidOrFree);
        sb.append(';');
        return sb.toString();
    }
}
