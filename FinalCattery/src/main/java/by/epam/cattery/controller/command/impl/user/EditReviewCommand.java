package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.constant.PathConst;
import by.epam.cattery.controller.command.constant.RequestConst;
import by.epam.cattery.controller.command.constant.SessionConst;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

public class EditReviewCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(EditReviewCommand.class);

    private static final String SUCCESS_PAGE = ConfigurationManager.getInstance().getProperty(PathConst.SUCCESS_PAGE);


    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        ReviewService reviewService = ServiceFactory.getInstance().getReviewService();

        Review review = createReview(requestContent);
        reviewService.editReview(review);

        return new RequestResult(NavigationType.REDIRECT, SUCCESS_PAGE);
    }


    private Review createReview(RequestContent requestContent) {
        Review review = new Review();

        review.setText(requestContent.getParameter(RequestConst.REVIEW_MESSAGE));
        review.setStarsCount(Integer.parseInt(requestContent.getParameter(RequestConst.REVIEW_RATING)));
        review.setUserLeftId((int) requestContent.getSessionAttribute(SessionConst.ID));
        review.setDate(new Date(System.currentTimeMillis()));

        return review;
    }
}
