package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.util.ConfigurationManager;

public class LogoutCommand implements ActionCommand {
    private static final String MAIN_PAGE = ConfigurationManager.getInstance().getProperty("path.page.main");

    public RequestResult execute(RequestContent requestContent) throws ServiceException {

//        session.removeAttribute("userId");
//        session.removeAttribute("login");
//        session.removeAttribute("role");
//
//        session.invalidate();

        requestContent.setSessionToBeInvalidated(true);

        return new RequestResult(NavigationType.REDIRECT, MAIN_PAGE);
    }
}
