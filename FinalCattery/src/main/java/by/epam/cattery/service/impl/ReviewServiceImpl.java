package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.ReviewDAO;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Review;
import by.epam.cattery.service.ReviewService;
import by.epam.cattery.service.exception.ServiceException;

import java.util.Collections;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static ReviewDAO reviewDAO = daoFactory.getReviewDAO();

    @Override
    public List<Review> takeApprovedReviews() throws ServiceException {
        List<Review> reviews;

        try {
            reviews = reviewDAO.findApprovedReviews();

            if (reviews.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            System.out.println(e);
            throw new ServiceException("Error while showing approved reviews", e);
        }
        return reviews;
    }
}
