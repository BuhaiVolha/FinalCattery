package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.entity.Role;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class TakeAllUsersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAllUsersCommand.class);

    private static final String ALL_USERS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.ALL_USERS);
    private static final String ACCESS_DENIED_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.ACCESS_DENIED_PAGE);

    private static final int ITEMS_PER_PAGE = 10;
    private static final int DEFAULT_PAGE = 1;


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {

        if (requestContent.getSessionAttribute(SessionConst.ROLE) == Role.ADMIN) {
            UserService userService = ServiceFactory.getInstance().getUserService();

            String pageValue = requestContent.getParameter(RequestConst.PAGINATION_PAGE);
            int page = (pageValue == null) ? DEFAULT_PAGE : Integer.parseInt(pageValue);

            List<User> users = userService.takeAllUsers(page, ITEMS_PER_PAGE);
            int pageCount = userService.getUsersPageCount(ITEMS_PER_PAGE);
            requestContent.setPaginationParameters(pageCount, page);
            requestContent.setAttribute(RequestConst.USERS_LIST, users);

            return new RequestResult(NavigationType.FORWARD, ALL_USERS_PAGE);

        } else {
            return new RequestResult(NavigationType.REDIRECT, ACCESS_DENIED_PAGE);
        }
    }
}
