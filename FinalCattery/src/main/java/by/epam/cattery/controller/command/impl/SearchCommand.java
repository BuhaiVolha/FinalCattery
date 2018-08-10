package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.entity.dto.SearchCatTO;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.*;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SearchCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SearchCommand.class);

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty("path.page.error");
    private static final String FOUND_CATS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.cats-found");

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        CatService catService = ServiceFactory.getInstance().getCatService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<Cat> cats;
        SearchCatTO searchCatTO = new SearchCatTO();

        String pageValue = requestContent.getParameter("page");
        int page = (pageValue == null) ? 1 : Integer.parseInt(pageValue);

        Cat searchedCat = createCat(requestContent);
        searchCatTO.setItemsPerPage(8);
        searchCatTO.setSearchedCat(searchedCat);
        int discountPercents = 0;

        if (requestContent.getSessionAttribute("role") == Role.USER) {
            int userId = (int) requestContent.getSessionAttribute("userId");
            discountPercents = userService.getDiscount(userId);
            searchCatTO.setUserDiscount(discountPercents);
        }
        catService.searchForCat(searchCatTO, page);
        cats = searchCatTO.getCats();

        for (Cat cat : cats) {
            if (discountPercents == 0) {
                cat.setPriceWithDiscount(cat.getPrice());
            } else {
                cat.setPriceWithDiscount(cat.getPrice() - (cat.getPrice() * discountPercents) / 100);
            }
        }

        catService.getPageCountForFoundCats(searchCatTO);

        requestContent.setAttribute("pageCount", searchCatTO.getPageCount());
        requestContent.setAttribute("page", page);
        requestContent.setAttribute("cats", searchCatTO.getCats());

        return new RequestResult(NavigationType.FORWARD, FOUND_CATS_PAGE);
    }


    private Cat createCat(RequestContent requestContent) {
        Cat cat = new Cat();

        if (!requestContent.getParameter("price").isEmpty()) {
            cat.setPrice(Double.parseDouble(requestContent.getParameter("price")));
        }

        if (!requestContent.getParameter("gender").isEmpty()) {
            cat.setGender(Gender.valueOf(requestContent.getParameter("gender")));
        }
        if (!requestContent.getParameter("status").isEmpty()) {
            cat.setStatus(CatStatus.valueOf(requestContent.getParameter("status")));
        }
        cat.setBodyColour(requestContent.getParameter("body"));
        cat.setEyesColour(requestContent.getParameter("eyes"));

        return cat;
    }
}