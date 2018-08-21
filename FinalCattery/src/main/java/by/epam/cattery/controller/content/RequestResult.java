package by.epam.cattery.controller.content;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * Contains the {@code page} to which {@code sendRedirect()} or {@code forward()} will be performed,
 * the latter is defined by {@code navigationType}
 *
 */
public class RequestResult {
    private NavigationType navigationType;
    private String page;

    public RequestResult(NavigationType navigationType, String page) {
        this.navigationType = navigationType;
        this.page = page;
    }

    public NavigationType getNavigationType() {
        return navigationType;
    }

    public void setNavigationType(NavigationType navigationType) {
        this.navigationType = navigationType;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof RequestResult)) return false;

        RequestResult that = (RequestResult) o;

        return new EqualsBuilder()
                .append(getNavigationType(), that.getNavigationType())
                .append(getPage(), that.getPage())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getNavigationType())
                .append(getPage())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("navigationType", navigationType)
                .append("page", page)
                .toString();
    }
}
