package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.entity.User;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.UserIsBannedException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(LoginCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);

    private static final String LOG_IN_FAILED_MESSAGE = ConfigurationManager.getInstance().getMessage("message.loginerror");



    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            String login = requestContent.getParameter(RequestConst.USER_LOGIN);
            String password = requestContent.getParameter(RequestConst.USER_PASSWORD);
            User user = userService.logIn(login, password);

            if (user != null) {
                requestContent.setSessionAttribute(SessionConst.ID, user.getId());
                requestContent.setSessionAttribute(SessionConst.LOGIN, user.getLogin());
                requestContent.setSessionAttribute(SessionConst.ROLE, user.getRole());

                return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);

            } else {
                requestContent.setSessionAttribute(SessionConst.LOG_IN_FAIL, LOG_IN_FAILED_MESSAGE);

                return new RequestResult(NavigationType.REDIRECT, requestContent.getCurrentPage());
            }

        } catch (UserIsBannedException e) {
            requestContent.setSessionAttribute(SessionConst.LOG_IN_FAIL,
                    ConfigurationManager.getInstance().getMessage("message.userbanned"));

            return new RequestResult(NavigationType.REDIRECT, requestContent.getCurrentPage());
        }
    }
}
