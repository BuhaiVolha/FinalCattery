package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;
import by.epam.cattery.controller.command.util.DiscountHelper;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SearchCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SearchCommand.class);

    private static final String FOUND_CATS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.FOUND_CATS);
    private static final int ITEMS_PER_PAGE = 8;
    private static final int DEFAULT_PAGE = 1;


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        CatService catService = ServiceFactory.getInstance().getCatService();
        UserService userService = ServiceFactory.getInstance().getUserService();

        String pageValue = requestContent.getParameter(RequestConst.PAGINATION_PAGE);
        int page = (pageValue == null) ? DEFAULT_PAGE : Integer.parseInt(pageValue);

        Cat searchedCat = createCat(requestContent);
        SearchCatTO searchCatTO = new SearchCatTO();
        searchCatTO.setItemsPerPage(ITEMS_PER_PAGE);
        searchCatTO.setSearchedCat(searchedCat);

        int discountPercents = 0;

        if (requestContent.getSessionAttribute(SessionConst.ROLE) == Role.USER) {
            int userId = (int) requestContent.getSessionAttribute(SessionConst.ID);
            discountPercents = userService.getDiscount(userId);
            searchCatTO.setUserDiscount(discountPercents);
        }
        catService.searchForCat(searchCatTO, page);
        DiscountHelper.getInstance().setPriceWithDiscount(searchCatTO.getCats(), discountPercents);
        catService.getPageCountForFoundCats(searchCatTO);

        requestContent.setPaginationParameters(searchCatTO.getPageCount(), page);
        requestContent.setAttribute(RequestConst.CATS_LIST, searchCatTO.getCats());
        requestContent.setAttribute(RequestConst.SEARCHED_CAT, searchedCat);

        return new RequestResult(NavigationType.FORWARD, FOUND_CATS_PAGE);
    }


    private Cat createCat(RequestContent requestContent) {
        Cat cat = new Cat();

        if (!requestContent.getParameter(RequestConst.CAT_PRICE).isEmpty()) {
            cat.setPrice(Double.parseDouble(requestContent.getParameter(RequestConst.CAT_PRICE)));
        }

        if (!requestContent.getParameter(RequestConst.CAT_GENDER).isEmpty()) {
            cat.setGender(Gender.valueOf(requestContent.getParameter(RequestConst.CAT_GENDER)));
        }
        if (!requestContent.getParameter(RequestConst.CAT_STATUS).isEmpty()) {
            cat.setStatus(CatStatus.valueOf(requestContent.getParameter(RequestConst.CAT_STATUS)));
        }
        cat.setBodyColour(requestContent.getParameter(RequestConst.CAT_BODY_COLOUR));
        cat.setEyesColour(requestContent.getParameter(RequestConst.CAT_EYES_COLOUR));

        return cat;
    }
}