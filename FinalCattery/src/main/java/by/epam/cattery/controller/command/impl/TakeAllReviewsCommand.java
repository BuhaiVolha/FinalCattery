package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
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

public class TakeAllReviewsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAllReviewsCommand.class);

    private static final String ERROR_PAGE = ConfigurationManager.getInstance().getProperty("path.page.error");
    private static final String ALL_REVIEWS_PAGE = ConfigurationManager.getInstance().getProperty("path.page.reviews");


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
        List<Review> reviews;

        String pageValue = requestContent.getParameter("page");
        int page = (pageValue == null) ? 1 : Integer.parseInt(pageValue);
        reviews = reviewService.takeAllReviews(page, 6);

        int pageCount = reviewService.getReviewsPageCount(6);
        requestContent.setAttribute("pageCount", pageCount);
        requestContent.setAttribute("page", page);
        requestContent.setAttribute("approvedReviews", reviews);

        return new RequestResult(NavigationType.FORWARD, ALL_REVIEWS_PAGE);
    }
}
