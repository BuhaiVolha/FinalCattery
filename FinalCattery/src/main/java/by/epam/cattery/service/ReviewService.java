package by.epam.cattery.service;

import by.epam.cattery.entity.Review;

import by.epam.cattery.service.exception.ServiceException;

import java.util.List;


/**
 * Defines methods to work with review data.
 */
public interface ReviewService {

    /**
     * Finds all reviews taking into account pagination.
     *
     * @param page         the current page
     * @param itemsPerPage the number of items per page
     * @return {@link Review} objects as list
     * @throws ServiceException if exception in DAO occurred
     *
     */
    List<Review> takeAllReviews(int page, int itemsPerPage) throws ServiceException;


    /**
     * Counts pages for reviews.
     *
     * @param itemsPerPage the items per page
     * @return the total page count
     * @throws ServiceException if exception in DAO occurred
     *
     */
    int getReviewsPageCount(int itemsPerPage) throws ServiceException;


    /**
     * Adds new review.
     *
     * @param review {@link Review}
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void writeReview(Review review) throws ServiceException;


    /**
     * Deletes the review by {@code reviewId}.
     *
     * @param reviewId the review's id
     * @param userId   the user's id, to check if the user has added a review
     * @throws ServiceException if exception in DAO occurred or the data input was invalid
     *
     */
    void deleteReview(int reviewId, int userId) throws ServiceException;


    /**
     * Edit review.
     *
     * @param review {@link Review}
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void editReview(Review review) throws ServiceException;


    /**
     * Take single review review.
     *
     * @param reviewId the review's id
     * @return {@link Review}
     * @throws ServiceException if exception in DAO occurred or the data input was invalid
     *
     */
    Review takeSingleReview(int reviewId) throws ServiceException;
}
