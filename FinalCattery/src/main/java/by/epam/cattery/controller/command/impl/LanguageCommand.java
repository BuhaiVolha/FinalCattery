package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.SessionConst;

import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;

import by.epam.cattery.service.exception.ServiceException;

/**
 * The command for changing the language.
 *
 */
public class LanguageCommand implements ActionCommand {


    /**
     * Changes the language and redirects to the current page.
     *
     *
     * @param requestContent - {@link RequestContent) object that accumulates the data from request
     * @return {@link RequestResult) object that contains next page and the type of operation that will be performed
     * @throws ServiceException
     *
     */
    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        requestContent.setSessionAttribute(SessionConst.LOCALE, requestContent.getParameter(SessionConst.LOCALE));

        return new RequestResult(NavigationType.REDIRECT, requestContent.getCurrentPage());
    }
}