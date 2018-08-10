package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.util.ConfigurationManager;

public class LogoutCommand implements ActionCommand {
    private static final String MAIN_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.MAIN_PAGE);


    public RequestResult execute(RequestContent requestContent) throws ServiceException {

        requestContent.setSessionToBeInvalidated(true);
        return new RequestResult(NavigationType.REDIRECT, MAIN_PAGE);
    }
}
