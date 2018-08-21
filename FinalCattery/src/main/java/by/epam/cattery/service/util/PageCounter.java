package by.epam.cattery.service.util;


/**
 * The purpose of this class is to count number of pages
 * taking into account their total amount and items per page.
 *
 */
public class PageCounter {
    private static final PageCounter instance = new PageCounter();

    private PageCounter() {
    }


    public int countPages(int totalCount, int itemsPerPage) {
        return (int) Math.ceil((double) totalCount / itemsPerPage);
    }


    public static PageCounter getInstance() {
        return instance;
    }
}
