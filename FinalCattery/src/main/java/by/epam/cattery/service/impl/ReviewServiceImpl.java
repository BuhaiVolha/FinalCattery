package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.mysql.ReviewDAO;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.mysql.UserDAO;
import by.epam.cattery.dao.connection.ConnectionProvider;
import by.epam.cattery.entity.Review;
import by.epam.cattery.service.ReviewService;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;
 // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! проверка статус
// и мессаги красивее сделать
public class ReviewServiceImpl implements ReviewService {
    private static final Logger logger = LogManager.getLogger(ReviewServiceImpl.class);

    private static DAOFactory daoFactory = DAOFactory.getInstance();

    private static ReviewDAO reviewDAO = daoFactory.getReviewDAO();
    private static UserDAO userDAO = daoFactory.getUserDAO();


    @Override
    public List<Review> takeAllReviews() throws ServiceException {
        List<Review> reviews;

        try {
            reviews = reviewDAO.loadAll();

            if (reviews.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception while finding all reviews", e);
        }
        return reviews;
    }


    @Override
    public void writeReview(Review review) throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
            connectionProvider.startTransaction();

            reviewDAO.save(review);
            logger.log(Level.DEBUG, "review added");

            // if (!userDAO.reviewWasLeft) { // !!!!!!!!!!!!!!!!!!!!!
            userDAO.reverseUserReviewFlag(review.getUserLeftId());
            logger.log(Level.DEBUG, "flag reversed");

            connectionProvider.commitTransaction();

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Exception while adding a review", e);

        }  finally {
            connectionProvider.endTransaction();
        }
    }


    @Override
    public void deleteReview(int reviewId, int userId) throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
            connectionProvider.startTransaction();

            // if (userDAO.reviewWasLeft) { // !!!!!!!!!!!!!!!!!!!!!
            userDAO.reverseUserReviewFlag(userId);
            logger.log(Level.DEBUG, "flag reversed");

            reviewDAO.delete(reviewId);
            logger.log(Level.DEBUG, "review deleted");

            connectionProvider.commitTransaction();

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Exception while deleting a review", e);

        }  finally {
            connectionProvider.endTransaction();
        }
    }


    @Override
    public void editReview(Review review) throws ServiceException {

        try {
            reviewDAO.update(review);

        } catch (DAOException e) {
            throw new ServiceException("Exception while editing review", e);
        }
    }


    @Override
    public Review takeSingleReview(int reviewId) throws ServiceException {

        try {
            return reviewDAO.getById(reviewId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while takeSingleReview", e);
        }
    }
}
