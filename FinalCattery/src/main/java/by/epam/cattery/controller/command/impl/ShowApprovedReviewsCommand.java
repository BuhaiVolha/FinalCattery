package by.epam.cattery.controller.command.impl;

import by.epam.cattery.controller.command.ActionCommand;
import by.epam.cattery.controller.util.ConfigurationManager;
import by.epam.cattery.entity.Review;
import by.epam.cattery.service.ReviewService;
import by.epam.cattery.service.ServiceFactory;
import by.epam.cattery.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowApprovedReviewsCommand implements ActionCommand {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Review> reviews = null;

        try {
            ReviewService reviewService = ServiceFactory.getInstance().getReviewService();
            reviews = reviewService.showApprovedReviews();

        } catch (ServiceException e) {
            //redirect
            System.out.println(e);
            System.out.println("reviews aren't here");
        }
//
//        session.setAttribute("approvedReviews", reviews);
//        response.sendRedirect(ConfigurationManager.getProperty("path.page.reviews"));
//
        request.setAttribute("approvedReviews", reviews);
        request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.reviews")).forward(request, response);
    }
}
