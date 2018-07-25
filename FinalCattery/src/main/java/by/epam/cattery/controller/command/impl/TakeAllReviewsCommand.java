package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
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
import java.util.List;

public class TakeAllReviewsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAllReviewsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Review> reviews = null;

        try {
            ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
            reviews = reviewService.takeApprovedReviews();

            request.setAttribute("approvedReviews", reviews);
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.reviews")).forward(request, response);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to take all reviews", e);
            response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
        }
    }
}
