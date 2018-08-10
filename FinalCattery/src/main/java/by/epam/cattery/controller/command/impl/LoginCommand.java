package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.entity.User;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.UserIsBannedException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.success-page");
    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty("path.page.error");
    private static final String LOG_IN_FAILED_MESSAGE = ConfigurationManager.getInstance().getMessage("message.loginerror");

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";


    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            String login = requestContent.getParameter(LOGIN);
            String password = requestContent.getParameter(PASSWORD);
            User user = userService.logIn(login, password);

            if (user != null) {
                requestContent.setSessionAttribute("userId", user.getId());
                requestContent.setSessionAttribute("login", user.getLogin());
                requestContent.setSessionAttribute("role", user.getRole());

                return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);

            } else {
                requestContent.setSessionAttribute("errorLoginPassMessage", LOG_IN_FAILED_MESSAGE);

                return new RequestResult(NavigationType.REDIRECT, requestContent.getCurrentPage());
            }

        } catch (UserIsBannedException e) {
            requestContent.setSessionAttribute("errorLoginPassMessage",
                    ConfigurationManager.getInstance().getMessage("message.userbanned"));

            return new RequestResult(NavigationType.REDIRECT, requestContent.getCurrentPage());
        }
    }
}
