package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
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


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OfferCatCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(OfferCatCommand.class);

    private static final String UPLOAD_CAT_PHOTO_PAGE = ConfigurationManager.getInstance().getProperty("path.page.cat-offer-photo");

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        OfferService offerService = ServiceFactory.getInstance().getOfferService();

        Offer offer = createOffer(requestContent);
        offer.setUserMadeOfferId((int) (requestContent.getSessionAttribute("userId")));
        int offerId = offerService.offerCat(offer);

        //request.setAttribute("offerId", offerId);
//            response.sendRedirect(ConfigurationManager.getInstance()
//                    .getProperty("path.page.cat-offer-photo")+"?offerId="+offerId);
        return new RequestResult(NavigationType.REDIRECT, UPLOAD_CAT_PHOTO_PAGE + "?offerId=" + offerId);
    }


    private Offer createOffer(RequestContent requestContent) {
        Offer offer = new Offer();

        offer.setCatDescription(requestContent.getParameter("catDescription"));
        offer.setPrice(Double.parseDouble(requestContent.getParameter("price")));
        offer.setStatus(OfferStatus.AWAIT);

        return offer;
    }
}
