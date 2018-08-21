package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.content.NavigationType;
import by.epam.cattery.controller.content.RequestContent;
import by.epam.cattery.controller.content.RequestResult;

import by.epam.cattery.util.ConfigurationManager;

import by.epam.cattery.entity.Review;

import by.epam.cattery.service.ReviewService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


/**
 * The command for taking all reviews.
 *
 */
public class TakeAllReviewsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAllReviewsCommand.class);

    private static final String ALL_REVIEWS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.REVIEWS);
    private static final int ITEMS_PER_PAGE = 6;
    private static final int DEFAULT_PAGE = 1;


    /**
     *
     * Loads all reviews, counts pagination details. Puts them into {@code requestContent}.
     * Makes forward to a page with reviews display.
     *
     * @param requestContent - {@link RequestContent) object that accumulates the data from request
     * @return {@link RequestResult) object that contains next page and the type of operation that will be performed
     * @throws ServiceException
     *
     */
    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
        List<Review> reviews;

        String pageValue = requestContent.getParameter(RequestConst.PAGINATION_PAGE);
        int page = (pageValue == null) ? DEFAULT_PAGE : Integer.parseInt(pageValue);
        reviews = reviewService.takeAllReviews(page, ITEMS_PER_PAGE);

        int pageCount = reviewService.getReviewsPageCount(ITEMS_PER_PAGE);
        requestContent.setPaginationParameters(pageCount, page);
        requestContent.setAttribute(RequestConst.REVIEWS_LIST, reviews);

        return new RequestResult(NavigationType.FORWARD, ALL_REVIEWS_PAGE);
    }
}
