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

/**
 * The command for taking the single offer by id.
 *
 */
public class GoToSingleOfferCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GoToSingleOfferCommand.class);

    /**
     * Finds offer by its id and puts offer into {@code requestContent}
     * and forwards to a page defined by {@code operation} parameter from {@code request}.
     *
     *
     * @param requestContent - {@link RequestContent) object that accumulates the data from request
     * @return {@link RequestResult) object that contains next page and the type of operation that will be performed
     * @throws ServiceException
     *
     */
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
