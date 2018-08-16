package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;
import by.epam.cattery.controller.command.util.DiscountHelper;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.LocaleLang;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.Role;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TakeAllCatsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAllCatsCommand.class);

    private static final String ALL_CATS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.ALL_CATS);
    private static final int ITEMS_PER_PAGE = 8;
    private static final int DEFAULT_PAGE = 1;


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        CatService catService = ServiceFactory.getInstance().getCatService();
        UserService userService = ServiceFactory.getInstance().getUserService();

        String pageValue = requestContent.getParameter(RequestConst.PAGINATION_PAGE);
        int page = (pageValue == null) ? DEFAULT_PAGE : Integer.parseInt(pageValue);
        int discountPercents = 0;
        LocaleLang localeLang = LocaleLang.valueOf(requestContent.getSessionAttribute(SessionConst.LOCALE).toString().toUpperCase());

        List<Cat> cats = catService.takeAllCats(localeLang, page, ITEMS_PER_PAGE);
        int pageCount = catService.getCatsPageCount(ITEMS_PER_PAGE);

        if (requestContent.getSessionAttribute(SessionConst.ROLE) == Role.USER) {
            int userId = (int) requestContent.getSessionAttribute(SessionConst.ID);
            discountPercents = userService.getDiscount(userId);
        }

        DiscountHelper.getInstance().setPriceWithDiscount(cats, discountPercents);

        requestContent.setPaginationParameters(pageCount, page);
        requestContent.setAttribute(RequestConst.CATS_LIST, cats);

        return new RequestResult(NavigationType.FORWARD, ALL_CATS_PAGE);
    }
}
