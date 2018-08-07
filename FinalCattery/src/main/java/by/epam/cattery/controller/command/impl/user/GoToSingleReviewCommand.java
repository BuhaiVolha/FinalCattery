package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.entity.Review;
import by.epam.cattery.service.ReviewService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GoToSingleReviewCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(GoToSingleReviewCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
        Review review;

        try {
            int reviewId = Integer.parseInt(request.getParameter("reviewId"));
            review = reviewService.takeSingleReview(reviewId);

            if (review != null) {
                request.setAttribute("review", review);
            }

            request.getRequestDispatcher(ConfigurationManager.getInstance()
                    .getProperty("path.page.edit-review")).forward(request, response);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to go to a single review: ", e);
            //redirect
        }
    }
}
