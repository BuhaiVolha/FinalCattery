package by.epam.cattery.controller.command.impl.user;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.command.impl.admin.AddCatCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.Review;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.ReviewService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

public class WriteReviewCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger(WriteReviewCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            Review review = createReview(request);

            ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
            reviewService.writeReview(review);

            response.sendRedirect(ConfigurationManager.getProperty("path.page.success-page"));

        } catch (ServiceException e) {
            logger.log(Level.ERROR, "Failed to write a review");
            response.sendRedirect(ConfigurationManager.getProperty("path.page.error"));
        }
    }


    private Review createReview(HttpServletRequest request) {
        Review review = new Review();
        HttpSession session = request.getSession();

        review.setText(request.getParameter("message"));
        review.setStarsCount(Integer.parseInt(request.getParameter("rating")));
        review.setUserLeftId(Integer.parseInt(session.getAttribute("userId").toString()));
        review.setDate(new Date(System.currentTimeMillis()));

        return review;
    }
}
