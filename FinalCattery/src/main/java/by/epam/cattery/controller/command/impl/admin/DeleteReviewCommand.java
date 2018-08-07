package by.epam.cattery.controller.command.impl.admin;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.util.ConfigurationManager;
import by.epam.cattery.service.ReviewService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteReviewCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(DeleteReviewCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            int reviewId = Integer.parseInt(request.getParameter("reviewId"));

            ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
            reviewService.deleteReview(reviewId, userId);

            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.success-page"));

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Deleting review failed: ", e);
            response.sendRedirect(ConfigurationManager.getInstance().getProperty("path.page.error"));
        }
    }
}
