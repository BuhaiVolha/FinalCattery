package by.epam.cattery.controller.command.impl.admin;

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


/**
 * The command for taking all approved offers.
 *
 */
public class TakeApprovedOffersCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeApprovedOffersCommand.class);

    private static final String APPROVED_OFFERS_PAGE = ConfigurationManager.getInstance()
            .getProperty(PathConst.APPROVED_OFFERS);
    private static final int ITEMS_PER_PAGE = 6;
    private static final int DEFAULT_PAGE = 1;


    /**
     *
     * Loads all offers by status {@code APRVD}, counts pagination details. Puts them into {@code requestContent}.
     * Makes forward to a page with offers display.
     *
     * @param requestContent - {@link RequestContent) object that accumulates the data from request
     * @return {@link RequestResult) object that contains next page and the type of operation that will be performed
     * @throws ServiceException
     *
     */
    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        OfferService offerService = ServiceFactory.getInstance().getOfferService();

        String pageValue = requestContent.getParameter(RequestConst.PAGINATION_PAGE);
        int page = (pageValue == null) ? DEFAULT_PAGE : Integer.parseInt(pageValue);

        List<Offer> offers = offerService.takeAllOffersByStatus(OfferStatus.APRVD, page, ITEMS_PER_PAGE);
        int pageCount = offerService.getOffersPageCountByStatus(OfferStatus.APRVD, ITEMS_PER_PAGE);

        requestContent.setPaginationParameters(pageCount, page);
        requestContent.setAttribute(RequestConst.OFFERS_LIST, offers);

        return new RequestResult(NavigationType.FORWARD, APPROVED_OFFERS_PAGE);
    }
}
