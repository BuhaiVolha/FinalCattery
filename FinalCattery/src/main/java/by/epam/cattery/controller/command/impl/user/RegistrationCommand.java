package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class RegistrationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(RegistrationCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.success-page");
    private static final String REGISTRATION_PAGE = ConfigurationManager.getInstance().getProperty("path.page.reg");
    private static final String EMAIL_TAKEN_MESSAGE = ConfigurationManager.getInstance().getMessage("message.emailtaken");
    private static final String LOGIN_EXISTS_MESSAGE = ConfigurationManager.getInstance().getMessage("message.loginexists");

    // protected?
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = createUser(requestContent);

        try {
            int userId = userService.register(user);

            requestContent.setSessionAttribute("userId", userId);
            requestContent.setSessionAttribute("login", user.getLogin());
            requestContent.setSessionAttribute("role", Role.USER);

            return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);

        } catch (ValidationFailedException e) {
            // заменить на бул?
            // пароль короткий
            logger.log(Level.WARN, "Validation failed: ", e);
            return new RequestResult(NavigationType.FORWARD, REGISTRATION_PAGE);

        } catch (LoginAlreadyExistsException e) {
            logger.log(Level.WARN, "User already exists exception: ", e);
            requestContent.setAttribute("errorLoginExistsMessage", LOGIN_EXISTS_MESSAGE);
            return new RequestResult(NavigationType.FORWARD, REGISTRATION_PAGE);

        } catch (EmailAlreadyExistsException e) {
            requestContent.setAttribute("errorEmailExistsMessage", EMAIL_TAKEN_MESSAGE);
            return new RequestResult(NavigationType.FORWARD, REGISTRATION_PAGE);
        }
    }


    private User createUser(RequestContent requestContent) {
        User user = new User();

        user.setLogin(requestContent.getParameter("login"));
        user.setPassword(requestContent.getParameter("password"));
        user.setName(requestContent.getParameter("name"));
        user.setLastname(requestContent.getParameter("lastname"));
        user.setEmail(requestContent.getParameter("email"));
        user.setPhone(requestContent.getParameter("phone"));

        return user;
    }
}
