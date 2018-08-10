package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;
import by.epam.cattery.service.OfferService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OfferCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(OfferCatCommand.class);

    private static final String UPLOAD_CAT_PHOTO_PAGE = ConfigurationManager.getInstance()
            .getProperty(PathConst.UPLOAD_CAT_PHOTO_PAGE);

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        OfferService offerService = ServiceFactory.getInstance().getOfferService();

        Offer offer = createOffer(requestContent);
        offer.setUserMadeOfferId((int) (requestContent.getSessionAttribute(SessionConst.ID)));
        int offerId = offerService.offerCat(offer);

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                    .getProperty("path.page.cat-offer-photo")+"?offerId="+offerId);
        return new RequestResult(NavigationType.REDIRECT, UPLOAD_CAT_PHOTO_PAGE + "?offerId=" + offerId);
    }


    private Offer createOffer(RequestContent requestContent) {
        Offer offer = new Offer();

        offer.setCatDescription(requestContent.getParameter(RequestConst.OFFER_CAT_DESCRIPTION));
        offer.setPrice(Double.parseDouble(requestContent.getParameter(RequestConst.OFFER_PRICE)));
        offer.setStatus(OfferStatus.AWAIT);

        return offer;
    }
}
