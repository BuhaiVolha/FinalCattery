package by.epam.training.entity.paperProduct;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Newspaper extends PaperProduct {
    private static final String GOODS_TYPE = "Newspaper";
    private String title;
    private String periodicity;
    private String paidOrFree;

    public static String getGoodsType() {
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
        if (this == o) return true;

        if (!(o instanceof Newspaper)) return false;

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
        sb.append(", paidOrFree = ").append(paidOrFree);
        sb.append(';');
        return sb.toString();
    }
}
