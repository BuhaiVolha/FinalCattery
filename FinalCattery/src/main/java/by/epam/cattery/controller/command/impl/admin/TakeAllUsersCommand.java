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

import java.util.List;


public class TakeAllUsersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAllUsersCommand.class);

    private static final String ALL_USERS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.manage-users");

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> users;

        String pageValue = requestContent.getParameter("page");
        int page = (pageValue == null) ? 1 : Integer.parseInt(pageValue);

        users = userService.takeAllUsers(page, 10);

        int pageCount = userService.getUsersPageCount(10);
        requestContent.setAttribute("pageCount", pageCount);
        requestContent.setAttribute("page", page);
        requestContent.setAttribute("users", users);

        return new RequestResult(NavigationType.FORWARD, ALL_USERS_PAGE);
    }
}
