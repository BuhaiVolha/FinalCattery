package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;

import by.epam.cattery.util.ConfigurationManager;

import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;

import by.epam.cattery.service.OfferService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The command for user to accept offered by expert price while discussing the offer is being performed.
 *
 */
public class AcceptOfferedPriceCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(AcceptOfferedPriceCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);


    /**
     *
     * Updates offer status and redirects to the success page.
     * {@code ServiceException} will be thrown if the offer's status wasn't appropriate in the first place.
     *
     * @param requestContent - {@link RequestContent) object that accumulates the data from request
     * @return {@link RequestResult) object that contains next page and the type of operation that will be performed
     * @throws ServiceException
     *
     */
    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        OfferService offerService = ServiceFactory.getInstance().getOfferService();

        Offer offer = createOffer(requestContent);
        offerService.answerToOffer(offer, OfferStatus.DISC);

        return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);
    }


    private Offer createOffer(RequestContent requestContent) {
        Offer offer = new Offer();

        offer.setId(Integer.parseInt(requestContent.getParameter(RequestConst.OFFER_ID)));
        offer.setPrice(Double.parseDouble(requestContent.getParameter(RequestConst.OFFER_PRICE)));
        offer.setStatus(OfferStatus.APRVD);

        return offer;
    }
}
