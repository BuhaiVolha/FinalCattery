package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.Role;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TakeAvailableCatsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAvailableCatsCommand.class);

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty("path.page.error");
    private static final String AVAILABLE_CATS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.cats-available");


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        CatService catService = ServiceFactory.getInstance().getCatService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<Cat> cats;

        String pageValue = requestContent.getParameter("page");
        int page = (pageValue == null) ? 1 : Integer.parseInt(pageValue);
        int discountPercents = 0;

        cats = catService.takeAllCatsByStatus(CatStatus.AVAIL, page, 8);
        int pageCount = catService.getCatsPageCountByStatus(CatStatus.AVAIL, 8);

        if (requestContent.getSessionAttribute("role") == Role.USER) {
            int userId = (int) requestContent.getSessionAttribute("userId");

            discountPercents = userService.getDiscount(userId);
        }

        for (Cat cat : cats) {
            if (discountPercents == 0) {
                cat.setPriceWithDiscount(cat.getPrice());
            } else {
                cat.setPriceWithDiscount(cat.getPrice() - (cat.getPrice() * discountPercents) / 100);
            }
        }
        requestContent.setAttribute("page", page);
        requestContent.setAttribute("pageCount", pageCount);
        requestContent.setAttribute("cats", cats);

        return new RequestResult(NavigationType.FORWARD, AVAILABLE_CATS_PAGE);
    }
}
