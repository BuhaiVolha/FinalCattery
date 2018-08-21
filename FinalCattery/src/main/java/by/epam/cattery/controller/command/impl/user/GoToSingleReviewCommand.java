package by.epam.cattery.controller.command.impl.user;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The command for taking the single review by id.
 *
 */
public class GoToSingleReviewCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GoToSingleReviewCommand.class);

    private static final String EDIT_REVIEW_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.WRITE_REVIEW);


    /**
     * Finds review by its id and puts offer into {@code requestContent}
     * and forwards to an edit review page.
     *
     *
     * @param requestContent - {@link RequestContent) object that accumulates the data from request
     * @return {@link RequestResult) object that contains next page and the type of operation that will be performed
     * @throws ServiceException
     *
     */
    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        ReviewService reviewService = ServiceFactory.getInstance().getReviewService();

        int reviewId = Integer.parseInt(requestContent.getParameter(RequestConst.REVIEW_ID));
        Review review = reviewService.takeSingleReview(reviewId);

        if (review != null) {
            requestContent.setAttribute(RequestConst.REVIEW, review);
        }
        return new RequestResult(NavigationType.FORWARD, EDIT_REVIEW_PAGE);
    }
}
