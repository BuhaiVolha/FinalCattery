package by.epam.cattery.controller.command.impl.expert;

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

import java.util.List;

public class TakeAwaitingOffersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAwaitingOffersCommand.class);

    private static final String AWAITING_OFFERS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.AWAITING_OFFERS);
    private static final int ITEMS_PER_PAGE = 6;
    private static final int DEFAULT_PAGE = 1;


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        OfferService offerService = ServiceFactory.getInstance().getOfferService();

        String pageValue = requestContent.getParameter(RequestConst.PAGINATION_PAGE);
        int page = (pageValue == null) ? DEFAULT_PAGE : Integer.parseInt(pageValue);

        List<Offer> offers = offerService.takeAllOffersByStatus(OfferStatus.AWAIT, page, ITEMS_PER_PAGE);
        int pageCount = offerService.getOffersPageCountByStatus(OfferStatus.AWAIT, ITEMS_PER_PAGE);
        requestContent.setPaginationParameters(pageCount, page);
        requestContent.setAttribute(RequestConst.OFFERS_LIST, offers);

        return new RequestResult(NavigationType.FORWARD, AWAITING_OFFERS_PAGE);
    }
}
