package by.epam.cattery.controller.command;

import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.service.exception.ServiceException;

public interface ActionCommand {
    RequestResult execute(RequestContent requestContent) throws ServiceException;
}
