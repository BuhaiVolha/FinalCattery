package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MakeDiscountCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(BanUserCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.success-page");

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        // Отдельный метод ФОРМ юзер
        int discount = Integer.parseInt(requestContent.getParameter("discount"));
        int userId = Integer.parseInt(requestContent.getParameter("userId"));

        User user = new User();
        user.setId(userId);
        user.setDiscount(discount);

        userService.makeDiscount(user);

        return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);
    }
}
