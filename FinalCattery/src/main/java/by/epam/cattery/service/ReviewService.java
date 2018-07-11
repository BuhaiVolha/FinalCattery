package by.epam.cattery.service;

import by.epam.cattery.entity.Review;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;

public interface ReviewService {
    List<Review> showApprovedReviews() throws ServiceException;

}
