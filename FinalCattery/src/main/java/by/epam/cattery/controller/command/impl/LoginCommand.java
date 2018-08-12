package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.MessageConst;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.exception.NoSuchUserException;
import by.epam.cattery.service.exception.ValidationFailedException;
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

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);

    private static final String WRONG_LOGIN_OR_PASSWORD_MESSAGE = ConfigurationManager.getInstance()
            .getMessage(MessageConst.WRONG_LOGIN_OR_PASSWORD);
    private static final String USER_IS_BANNED_MESSAGE = ConfigurationManager.getInstance()
            .getMessage(MessageConst.USER_IS_BANNED);
    private static final String INVALID_INPUT_MESSAGE = ConfigurationManager.getInstance()
            .getMessage(MessageConst.INVALID_INPUT);



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
                requestContent.setSessionAttribute(SessionConst.LOG_IN_FAIL, WRONG_LOGIN_OR_PASSWORD_MESSAGE);
                return new RequestResult(NavigationType.REDIRECT, requestContent.getCurrentPage());
            }
        } catch (ValidationFailedException e) {
            logger.log(Level.WARN, "Validation of input data failed during registration");
            requestContent.setSessionAttribute(SessionConst.LOG_IN_FAIL, INVALID_INPUT_MESSAGE);
            return new RequestResult(NavigationType.REDIRECT, requestContent.getCurrentPage());

        } catch (NoSuchUserException e) {
            logger.log(Level.WARN, "No such user or login or password is wrong");
            requestContent.setSessionAttribute(SessionConst.LOG_IN_FAIL, WRONG_LOGIN_OR_PASSWORD_MESSAGE);
            return new RequestResult(NavigationType.REDIRECT, requestContent.getCurrentPage());

        } catch (UserIsBannedException e) {
            logger.log(Level.WARN, "User is banned");
            requestContent.setSessionAttribute(SessionConst.LOG_IN_FAIL,USER_IS_BANNED_MESSAGE);
            return new RequestResult(NavigationType.REDIRECT, requestContent.getCurrentPage());
        }
    }
}
