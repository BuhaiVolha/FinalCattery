package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;
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

public class GoToSingleUserCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GoToSingleUserCommand.class);

    private static final String ACCESS_DENIED_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.ACCESS_DENIED_PAGE);

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        String path = ACCESS_DENIED_PAGE;

        if (requestContent.getSessionAttribute(SessionConst.ROLE) != null) {
            UserService userService = ServiceFactory.getInstance().getUserService();

            int userId = (int) requestContent.getSessionAttribute(SessionConst.ID);
            String operation = requestContent.getParameter(RequestConst.OPERATION);
            User user = userService.takeSingleUser(userId);
            requestContent.setAttribute(RequestConst.USER, user);

            path = ConfigurationManager.getInstance().getProperty(PathConst.PATH_START + operation);
        }

        return new RequestResult(NavigationType.FORWARD, path);
    }
}
