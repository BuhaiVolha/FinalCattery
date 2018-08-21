package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;

import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;

import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.User;

import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.UserService;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * The command for updating user's colour preference.
 *
 */
public class SetColourPreferenceCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SetColourPreferenceCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);


    /**
     *
     * Updates colour preference for user and makes redirect to a success page.
     *
     * @param requestContent - {@link RequestContent) object that accumulates the data from request
     * @return {@link RequestResult) object that contains next page and the type of operation that will be performed
     * @throws ServiceException
     *
     */
    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        User user = new User();
        user.setColourPreference(requestContent.getParameter(RequestConst.USER_COLOUR_PREFERENCE));
        user.setId((int) requestContent.getSessionAttribute(SessionConst.ID));
        userService.changeColourPreference(user);

        return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);
    }
}
