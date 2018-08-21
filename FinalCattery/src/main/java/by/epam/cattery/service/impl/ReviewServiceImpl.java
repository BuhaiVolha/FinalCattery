package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.ReviewDAO;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.UserDAO;
import by.epam.cattery.dao.connection.ConnectionProvider;

import by.epam.cattery.entity.Review;

import by.epam.cattery.service.ReviewService;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;
import by.epam.cattery.service.util.PageCounter;
import by.epam.cattery.service.validation.Validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;


/**
 * {@inheritDoc}
 *
 */
public class ReviewServiceImpl implements ReviewService {
    private static final Logger logger = LogManager.getLogger(ReviewServiceImpl.class);

    private static DAOFactory daoFactory = DAOFactory.getInstance();

    private static ReviewDAO reviewDAO = daoFactory.getReviewDAO();
    private static UserDAO userDAO = daoFactory.getUserDAO();


    /**
     * {@inheritDoc}
     *
     * The list will be empty if no reviews has been found
     *
     */
    @Override
    public List<Review> takeAllReviews(int page, int itemsPerPage) throws ServiceException {
        List<Review> reviews;

        try {
            reviews = reviewDAO.loadAll(page, itemsPerPage);

            if (reviews.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception while finding all reviews", e);
        }
        return reviews;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int getReviewsPageCount(int itemsPerPage) throws ServiceException {
        int pageCount;

        try {
            int totalCount = reviewDAO.getTotalCount();
            pageCount = PageCounter.getInstance().countPages(totalCount, itemsPerPage);

        } catch (DAOException e) {
            throw new ServiceException("Exception while getting reviews counted", e);
        }
        return pageCount;
    }


    /**
     * {@inheritDoc}
     *
     * The implementation checks if input data are valid otherwise {@link ValidationFailedException} will be thrown
     *
     * The implementation also checks if the user hasn't already added review otherwise {@link DAOException} will be thrown
     *
     */
    @Override
    public void writeReview(Review review) throws ServiceException {

        if (!Validator.getInstance().validateReviewInputData(review)) {
            throw new ValidationFailedException("Review's data are invalid!");
        }

        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
             if (!userDAO.reviewWasAdded(review.getUserLeftId())) {
                 connectionProvider.startTransaction();

                 reviewDAO.save(review);
                 logger.log(Level.DEBUG, "Review added");

                 userDAO.reverseUserReviewFlag(review.getUserLeftId());
                 logger.log(Level.DEBUG, "Flag reversed");

                 connectionProvider.commitTransaction();
             }

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Exception while adding a review", e);

        }  finally {
            connectionProvider.endTransaction();
        }
    }


    /**
     * {@inheritDoc}
     *
     * The implementation checks if the user has added the review in the first place otherwise {@link DAOException} will be thrown
     *
     */
    @Override
    public void deleteReview(int reviewId, int userId) throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
            connectionProvider.startTransaction();

            if (userDAO.reviewWasAdded(userId)) {

                userDAO.reverseUserReviewFlag(userId);
                logger.log(Level.DEBUG, "Flag reversed");

                reviewDAO.delete(reviewId);
                logger.log(Level.DEBUG, "Review deleted");
            }
            connectionProvider.commitTransaction();

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Exception while deleting a review", e);

        }  finally {
            connectionProvider.endTransaction();
        }
    }


    /**
     * {@inheritDoc}
     *
     * The implementation checks if input data are valid otherwise {@link ValidationFailedException} will be thrown
     *
     * The implementation also checks if the user has added the review in the first place otherwise {@link DAOException} will be thrown
     *
     */
    @Override
    public void editReview(Review review) throws ServiceException {

        if (!Validator.getInstance().validateReviewInputData(review)) {
            throw new ValidationFailedException("Review's data are invalid!");
        }

        try {
            if (userDAO.reviewWasAdded(review.getUserLeftId())) {
                reviewDAO.update(review);
            }

        } catch (DAOException e) {
            throw new ServiceException("Exception while editing a review", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Review takeSingleReview(int reviewId) throws ServiceException {

        try {
            return reviewDAO.getById(reviewId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while finding a review", e);
        }
    }
}
