package by.epam.cattery.entity.dto;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.Entity;
import by.epam.cattery.service.util.PageCounter;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

public class SearchCatTO extends Entity {
    private List<Cat> cats;
    private Cat searchedCat;
    private int userDiscount;
    private int pageCount;
    private int itemsPerPage;
    private String searchQuery;


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
}
