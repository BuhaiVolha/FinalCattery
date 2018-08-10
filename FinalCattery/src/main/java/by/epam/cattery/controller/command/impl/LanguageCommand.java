package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.service.exception.ServiceException;

public class LanguageCommand implements ActionCommand {

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        requestContent.setSessionAttribute("local", requestContent.getParameter("local"));

        return new RequestResult(NavigationType.REDIRECT, requestContent.getCurrentPage());
    }
}