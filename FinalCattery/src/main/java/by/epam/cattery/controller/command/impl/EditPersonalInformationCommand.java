package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.MessageConst;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;
import by.epam.cattery.controller.command.util.PathHelper;
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
    private static final String EDIT_USER_INFO_COMMAND = ConfigurationManager.getInstance().getProperty(PathConst.EDIT_USER_INFO_COMMAND);


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        PathHelper pathHelper = PathHelper.getInstance();
        String path;

        try {
            User user = createUser(requestContent);
            userService.editPersonalInfo(user);

            path = SUCCESS_PAGE;

        } catch (ValidationFailedException e) {
            logger.log(Level.WARN, "Validation failed while editing personal info");
            path = pathHelper.addParameterToPath(EDIT_USER_INFO_COMMAND, RequestConst.EDIT_USER_INFO_FAILED_MESSAGE, MessageConst.INVALID_INPUT);

        } catch (EmailAlreadyExistsException e) {
            logger.log(Level.WARN, "Email already exist and it's not user's");
            path = pathHelper.addParameterToPath(EDIT_USER_INFO_COMMAND, RequestConst.EDIT_USER_INFO_FAILED_MESSAGE, MessageConst.EMAIL_TAKEN);
        }
        return new RequestResult(NavigationType.REDIRECT, path);
    }


    private User createUser(RequestContent requestContent) {
        User user = new User();

        user.setId((int) requestContent.getSessionAttribute(SessionConst.ID));
        user.setLogin(requestContent.getSessionAttribute(SessionConst.LOGIN).toString());
        user.setName(requestContent.getParameter(RequestConst.USER_NAME));
        user.setLastname(requestContent.getParameter(RequestConst.USER_LASTNAME));
        user.setEmail(requestContent.getParameter(RequestConst.USER_EMAIL));
        user.setPhone(requestContent.getParameter(RequestConst.USER_PHONE));
        user.setPassword(requestContent.getParameter(RequestConst.USER_PASSWORD));

        return user;
    }
}
