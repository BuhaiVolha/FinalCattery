package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
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

import javax.servlet.http.HttpSession;

public class SetColourPreferenceCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(SetColourPreferenceCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.success-page");

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = new User();

        user.setColourPreference(requestContent.getParameter("colour"));
        user.setId((int) requestContent.getSessionAttribute("userId"));
        userService.changeColourPreference(user);

        //в кабинет вернуть
        return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);
    }
}
