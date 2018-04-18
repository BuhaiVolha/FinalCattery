package by.epam.training.entity.paperProduct;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class TextBook extends PaperProduct {
    private static final String GOODS_TYPE = "TextBook";
    private String title;
    private String subject;
    private String author;
    private double numberOfPages;

    public static String getGoodsType() {
        return GOODS_TYPE;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(double numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof TextBook)) return false;

        TextBook textBook = (TextBook) o;

        return new EqualsBuilder()
                .append(getNumberOfPages(), textBook.getNumberOfPages())
                .append(getTitle(), textBook.getTitle())
                .append(getSubject(), textBook.getSubject())
                .append(getAuthor(), textBook.getAuthor())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getTitle())
                .append(getSubject())
                .append(getAuthor())
                .append(getNumberOfPages())
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(GOODS_TYPE);
        sb.append(": title = ").append(title);
        sb.append(", subject = ").append(subject);
        sb.append(", author = ").append(author);
        sb.append(", numberOfPages = ").append(numberOfPages);
        sb.append(';');
        return sb.toString();
    }
}
