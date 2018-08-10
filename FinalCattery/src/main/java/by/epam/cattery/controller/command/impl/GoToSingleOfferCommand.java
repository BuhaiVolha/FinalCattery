package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.service.OfferService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToSingleOfferCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GoToSingleOfferCommand.class);

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty("path.page.error");


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        OfferService offerService = ServiceFactory.getInstance().getOfferService();
        Offer offer;

        String operation = requestContent.getParameter("operation");
        int offerId = Integer.parseInt(requestContent.getParameter("offerId"));
        offer = offerService.takeSingleOffer(offerId);

        requestContent.setAttribute("offer", offer);
        requestContent.setAttribute("offerId", offer.getId());
        requestContent.setAttribute("operation", operation);

        return new RequestResult(NavigationType.FORWARD, ConfigurationManager.getInstance()
                .getProperty("path.page." + operation));
    }
}
