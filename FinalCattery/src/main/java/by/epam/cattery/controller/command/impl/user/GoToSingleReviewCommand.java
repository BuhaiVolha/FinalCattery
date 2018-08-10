package by.epam.cattery.controller.command.impl.user;

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

public class GoToSingleReviewCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GoToSingleReviewCommand.class);

    private static final String EDIT_REVIEW_PAGE = ConfigurationManager.getInstance().getProperty("path.page.edit-review");

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
        Review review;

        int reviewId = Integer.parseInt(requestContent.getParameter("reviewId"));
        review = reviewService.takeSingleReview(reviewId);

        if (review != null) {
            requestContent.setAttribute("review", review);
        }

        return new RequestResult(NavigationType.FORWARD, EDIT_REVIEW_PAGE);
    }
}
