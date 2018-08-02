package by.epam.cattery.service;

import by.epam.cattery.entity.Review;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;

public interface ReviewService {
    List<Review> takeAllReviews() throws ServiceException;
    void writeReview(Review review) throws ServiceException;
    void deleteReview(int reviewId, int userId) throws ServiceException;
    void editReview(Review review) throws ServiceException;
    Review takeSingleReview(int reviewId) throws ServiceException;
}
