package by.epam.cattery.entity.dto;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.Entity;
import by.epam.cattery.entity.LocaleLang;
import by.epam.cattery.service.util.PageCounter;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;

/**
 * The type Search cat to.
 */
public class SearchCatTO extends Entity {
    private static final long serialVersionUID = -2358283210277377012L;
    private List<Cat> cats;
    private Cat searchedCat;
    private int userDiscount;
    private int pageCount;
    private int itemsPerPage;
    private String searchQuery;
    private LocaleLang localeLang;


    public SearchCatTO(int id, List<Cat> cats, Cat searchedCat, int userDiscount, int pageCount, int itemsPerPage,
                       String searchQuery, LocaleLang localeLang) {
        super(id);
        this.cats = cats;
        this.searchedCat = searchedCat;
        this.userDiscount = userDiscount;
        this.pageCount = pageCount;
        this.itemsPerPage = itemsPerPage;
        this.searchQuery = searchQuery;
        this.localeLang = localeLang;
    }

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

    public LocaleLang getLocaleLang() {
        return localeLang;
    }

    public void setLocaleLang(LocaleLang localeLang) {
        this.localeLang = localeLang;
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
                .append(getLocaleLang(), that.getLocaleLang())
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
                .append(getLocaleLang())
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(super.toString())
                .append("cats", cats)
                .append("searchedCat", searchedCat)
                .append("userDiscount", userDiscount)
                .append("pageCount", pageCount)
                .append("itemsPerPage", itemsPerPage)
                .append("searchQuery", searchQuery)
                .append("localeLang", localeLang)
                .toString();
    }
}
