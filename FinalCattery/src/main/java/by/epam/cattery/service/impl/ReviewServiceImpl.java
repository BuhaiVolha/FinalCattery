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
            reviews = reviewDAO.findAllReviews();

            if (reviews.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            throw new ServiceException("Error while showing reviews", e);
        }
        return reviews;
    }


    @Override
    public void writeReview(Review review) throws ServiceException {

        try {
            reviewDAO.addReview(review);

        } catch (DAOException e) {
            throw new ServiceException("Exception while writing reviews", e);
        }
    }


    @Override
    public void deleteReview(int reviewId) throws ServiceException {

        try {
            reviewDAO.deleteReview(reviewId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while deleting review", e);
        }
    }


    @Override
    public void editReview(Review review) throws ServiceException {

        try {
            reviewDAO.updateReview(review);

        } catch (DAOException e) {
            throw new ServiceException("Exception while editing review", e);
        }
    }


    @Override
    public Review takeSingleReview(int reviewId) throws ServiceException {
        try {
            return reviewDAO.findSingleReview(reviewId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while takeSingleReview", e);
        }
    }
}
