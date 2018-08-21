package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;

import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;

import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.util.ConfigurationManager;


/**
 * The command for logging out.
 *
 */
public class LogoutCommand implements ActionCommand {
    private static final String MAIN_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.MAIN_PAGE);


    /**
     *
     * Invalidates user's session and redirects him to main page.
     *
     * @param requestContent - {@link RequestContent) object that accumulates the data from request
     * @return {@link RequestResult) object that contains next page and the type of operation that will be performed
     * @throws ServiceException
     *
     */
    public RequestResult execute(RequestContent requestContent) throws ServiceException {

        requestContent.setSessionToBeInvalidated(true);
        return new RequestResult(NavigationType.REDIRECT, MAIN_PAGE);
    }
}
