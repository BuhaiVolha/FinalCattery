package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Review;

import java.util.List;

public interface ReviewDAO {
    List<Review> findApprovedReviews() throws DAOException;
}
