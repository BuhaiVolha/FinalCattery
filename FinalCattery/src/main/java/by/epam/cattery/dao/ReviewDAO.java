package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Review;

import java.util.List;

public interface ReviewDAO {
    List<Review> findAllReviews() throws DAOException;
    void addReview(Review review) throws DAOException;
    void deleteReview(int reviewId) throws DAOException;
    void updateReview(Review review) throws DAOException;
    Review findSingleReview(int reviewId) throws DAOException;
}
