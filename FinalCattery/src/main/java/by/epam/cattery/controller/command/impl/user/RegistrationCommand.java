package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.MessageConst;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;
import by.epam.cattery.controller.command.util.PathHelper;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;

import by.epam.cattery.entity.Role;
import by.epam.cattery.entity.User;

import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;

import by.epam.cattery.service.exception.EmailAlreadyExistsException;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.LoginAlreadyExistsException;
import by.epam.cattery.service.exception.ValidationFailedException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RegistrationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);
    private static final String REGISTRATION_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.REGISTRATION);


    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = createUser(requestContent);

        PathHelper pathHelper = PathHelper.getInstance();
        String path;

        String locale = requestContent.getSessionAttribute(SessionConst.LOCALE).toString();
        String message;

        try {
            int userId = userService.register(user);

            requestContent.setSessionAttribute(SessionConst.ID, userId);
            requestContent.setSessionAttribute(SessionConst.LOGIN, user.getLogin());
            requestContent.setSessionAttribute(SessionConst.ROLE, Role.USER);

            return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);

        } catch (ValidationFailedException e) {
            logger.log(Level.WARN, "Validation of input data failed during registration");
            path = pathHelper.addParameterToPath(REGISTRATION_PAGE, RequestConst.REGISTRATION_FAILED_MESSAGE, MessageConst.INVALID_INPUT);

        } catch (LoginAlreadyExistsException e) {
            logger.log(Level.WARN, "User already exists");
            path = pathHelper.addParameterToPath(REGISTRATION_PAGE, RequestConst.REGISTRATION_FAILED_MESSAGE, MessageConst.LOGIN_ALREADY_EXISTS);

        } catch (EmailAlreadyExistsException e) {
            logger.log(Level.WARN, "Email is already taken");
            path = pathHelper.addParameterToPath(REGISTRATION_PAGE, RequestConst.REGISTRATION_FAILED_MESSAGE, MessageConst.EMAIL_TAKEN);
        }
        return new RequestResult(NavigationType.REDIRECT, path);
    }


    private User createUser(RequestContent requestContent) {
        User user = new User();

        user.setLogin(requestContent.getParameter(RequestConst.USER_LOGIN));
        user.setPassword(requestContent.getParameter(RequestConst.USER_PASSWORD));
        user.setName(requestContent.getParameter(RequestConst.USER_NAME));
        user.setLastname(requestContent.getParameter(RequestConst.USER_LASTNAME));
        user.setEmail(requestContent.getParameter(RequestConst.USER_EMAIL));
        user.setPhone(requestContent.getParameter(RequestConst.USER_PHONE));

        return user;
    }
}
