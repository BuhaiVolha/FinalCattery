package by.epam.cattery.service;

import by.epam.cattery.entity.Review;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;

public interface ReviewService {
    List<Review> takeApprovedReviews() throws ServiceException;
    void writeReview(Review review) throws ServiceException;
    void deleteReview(int reviewId) throws ServiceException;
    void editReview(Review review) throws ServiceException;
    Review takeSingleReview(int reviewId) throws ServiceException;
}
