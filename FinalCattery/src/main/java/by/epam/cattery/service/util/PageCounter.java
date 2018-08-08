package by.epam.cattery.service.util;


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
