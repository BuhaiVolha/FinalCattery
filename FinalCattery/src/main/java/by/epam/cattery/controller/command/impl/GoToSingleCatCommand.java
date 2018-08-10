package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.Role;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToSingleCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GoToSingleCatCommand.class);

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty("path.page.error");


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        CatService catService = ServiceFactory.getInstance().getCatService();
        Cat cat;


        String operation = requestContent.getParameter("operation");
        int catId = Integer.parseInt(requestContent.getParameter("catId"));
        int discountPercents = 0;

        cat = catService.takeSingleCat(catId);

        if (requestContent.getSessionAttribute("role") == Role.USER) {
            int userId = (int) requestContent.getSessionAttribute("userId");
            UserService userService = ServiceFactory.getInstance().getUserService();

            discountPercents = userService.getDiscount(userId);
            cat.setPriceWithDiscount(cat.getPrice() - (cat.getPrice() * discountPercents) / 100);
        } else {
            cat.setPriceWithDiscount(cat.getPrice());
        }
        requestContent.setAttribute("singleCat", cat);

        return new RequestResult(NavigationType.FORWARD, ConfigurationManager.getInstance()
                .getProperty("path.page." + operation));
    }
}
