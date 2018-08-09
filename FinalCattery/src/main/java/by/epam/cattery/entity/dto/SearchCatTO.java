package by.epam.cattery.entity.dto;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.Entity;
import by.epam.cattery.service.util.PageCounter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.List;

public class SearchCatTO extends Entity {
    private static final long serialVersionUID = 4160128363304082334L;
    private List<Cat> cats;
    private Cat searchedCat;
    private int userDiscount;
    private int pageCount;
    private int itemsPerPage;
    private String searchQuery;


    public SearchCatTO() {
    }

    public List<Cat> getCats() {
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int totalFoundCatsCount) {
        this.pageCount = PageCounter.getInstance().countPages(totalFoundCatsCount, itemsPerPage);
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public Cat getSearchedCat() {
        return searchedCat;
    }

    public void setSearchedCat(Cat searchedCat) {
        this.searchedCat = searchedCat;
    }

    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public int getUserDiscount() {
        return userDiscount;
    }

    public void setUserDiscount(int userDiscount) {
        this.userDiscount = userDiscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof SearchCatTO)) return false;

        SearchCatTO that = (SearchCatTO) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getUserDiscount(), that.getUserDiscount())
                .append(getPageCount(), that.getPageCount())
                .append(getItemsPerPage(), that.getItemsPerPage())
                .append(getCats(), that.getCats())
                .append(getSearchedCat(), that.getSearchedCat())
                .append(getSearchQuery(), that.getSearchQuery())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getCats())
                .append(getSearchedCat())
                .append(getUserDiscount())
                .append(getPageCount())
                .append(getItemsPerPage())
                .append(getSearchQuery())
                .toHashCode();
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchCatTO{");
        sb.append("cats=").append(cats);
        sb.append(", searchedCat=").append(searchedCat);
        sb.append(", userDiscount=").append(userDiscount);
        sb.append(", pageCount=").append(pageCount);
        sb.append(", itemsPerPage=").append(itemsPerPage);
        sb.append(", searchQuery='").append(searchQuery).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
