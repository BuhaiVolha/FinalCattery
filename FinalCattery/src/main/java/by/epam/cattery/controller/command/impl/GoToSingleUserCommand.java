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

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        int userId = (int) requestContent.getSessionAttribute(SessionConst.ID);
        String operation = requestContent.getParameter(RequestConst.OPERATION);
        User user = userService.takeSingleUser(userId);
        requestContent.setAttribute(RequestConst.USER, user);

        return new RequestResult(NavigationType.FORWARD, ConfigurationManager.getInstance()
                .getProperty(PathConst.PATH_START + operation));
    }
}
