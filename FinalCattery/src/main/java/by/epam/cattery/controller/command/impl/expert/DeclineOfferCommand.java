package by.epam.cattery.controller.command.impl.expert;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;
import by.epam.cattery.service.OfferService;
import by.epam.cattery.service.ServiceFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeclineOfferCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeclineOfferCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
            OfferService offerService = ServiceFactory.getInstance().getOfferService();

            Offer offer = new Offer();
            offer.setExpertMessage(requestContent.getParameter(RequestConst.OFFER_EXPERT_MESSAGE_TO_USER));
            offer.setId(Integer.parseInt(requestContent.getParameter(RequestConst.OFFER_ID)));
            offer.setPrice(Double.parseDouble(requestContent.getParameter(RequestConst.OFFER_PRICE)));
            offer.setStatus(OfferStatus.REJCT);

            offerService.answerToOffer(offer, OfferStatus.AWAIT);

            return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);
    }
}
