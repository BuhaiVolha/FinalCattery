package by.epam.cattery.controller.command.impl;

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
import java.util.List;

public class TakeAllReviewsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(TakeAllReviewsCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Review> reviews = null;

        String pageValue = request.getParameter("page");
        int page = (pageValue == null) ? 1 : Integer.parseInt(pageValue);

        try {
            ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
            reviews = reviewService.takeAllReviews(page, 6);

            int pageCount = reviewService.getReviewsPageCount(6);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("page", page);
            request.setAttribute("approvedReviews", reviews);

            request.getRequestDispatcher(ConfigurationManager.getInstance()
                    .getProperty("path.page.reviews")).forward(request, response);

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to take all reviews", e);
            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.error"));
        }
    }
}
