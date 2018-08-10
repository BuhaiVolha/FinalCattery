package by.epam.cattery.controller.command.impl.expert;

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

import java.util.List;

public class TakeAwaitingOffersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAwaitingOffersCommand.class);

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty("path.page.error");
    private static final String AWAITING_OFFERS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.offers-awaiting");

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        OfferService offerService = ServiceFactory.getInstance().getOfferService();
        List<Offer> offers;

        String pageValue = requestContent.getParameter("page");
        int page = (pageValue == null) ? 1 : Integer.parseInt(pageValue);

        offers = offerService.takeAllOffersByStatus(OfferStatus.AWAIT, page, 6);
        int pageCount = offerService.getOffersPageCountByStatus(OfferStatus.AWAIT, 6);

        requestContent.setAttribute("pageCount", pageCount);
        requestContent.setAttribute("page", page);
        requestContent.setAttribute("offers", offers);

        return new RequestResult(NavigationType.FORWARD, AWAITING_OFFERS_PAGE);
    }
}
