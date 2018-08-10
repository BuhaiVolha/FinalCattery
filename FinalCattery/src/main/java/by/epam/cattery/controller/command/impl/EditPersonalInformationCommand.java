package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.EmailAlreadyExistsException;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditPersonalInformationCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(EditPersonalInformationCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);
    private static final String EMAIL_TAKEN_MESSAGE = ConfigurationManager.getInstance().getMessage("message.emailtaken");
    private static final String EDIT_USER_INFO_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.EDIT_USER_INFO);

    // проверить емаил но не тот что свой.  форвард на го ту сингл юзер??
    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            User user = createUser(requestContent);
            userService.editPersonalInfo(user);

            //Обратно в кабинет
            return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);

        } catch (ValidationFailedException e) {
            // заменить на бул?
            // пароль короткий
            logger.log(Level.WARN, "Validation failed: ", e);
            return new RequestResult(NavigationType.FORWARD, EDIT_USER_INFO_PAGE);

        } catch (EmailAlreadyExistsException e) {
            requestContent.setAttribute("errorEmailExistsMessage", EMAIL_TAKEN_MESSAGE);

            //return new RequestResult(NavigationType.FORWARD, EDIT_USER_INFO_PAGE);
            // GET
            return new RequestResult(NavigationType.FORWARD, "/controller?command=single_user&operation=edit-user-info");
        }
    }


    private User createUser(RequestContent requestContent) {
        User user = new User();

        user.setId((int) requestContent.getSessionAttribute("userId"));
        user.setLogin(requestContent.getSessionAttribute("login").toString());
        user.setName(requestContent.getParameter("name"));
        user.setLastname(requestContent.getParameter("lastname"));
        user.setEmail(requestContent.getParameter("email"));
        user.setPhone(requestContent.getParameter("phone"));
        user.setPassword(requestContent.getParameter("password"));

        return user;
    }
}
