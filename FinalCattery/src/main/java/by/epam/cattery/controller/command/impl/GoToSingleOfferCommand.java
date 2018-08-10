package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
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

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        OfferService offerService = ServiceFactory.getInstance().getOfferService();

        String operation = requestContent.getParameter(RequestConst.OPERATION);
        int offerId = Integer.parseInt(requestContent.getParameter(RequestConst.OFFER_ID));
        Offer offer = offerService.takeSingleOffer(offerId);

        requestContent.setAttribute(RequestConst.OFFER, offer);
        requestContent.setAttribute(RequestConst.OFFER_ID, offer.getId());
        requestContent.setAttribute(RequestConst.OPERATION, operation);

        return new RequestResult(NavigationType.FORWARD, ConfigurationManager.getInstance()
                .getProperty(PathConst.PATH_START + operation));
    }
}
