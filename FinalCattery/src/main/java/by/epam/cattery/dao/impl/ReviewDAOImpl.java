package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.ReviewDAO;
import by.epam.cattery.dao.BaseDAO;

import by.epam.cattery.entity.Review;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewDAOImpl extends BaseDAO<Review> implements ReviewDAO {
    private static final Logger logger = LogManager.getLogger(ReviewDAOImpl.class);

    private static final String CREATE_REVIEW = "INSERT INTO user_review (user_left_id, review_text, " +
            "stars_count, date) VALUES(?,?,?,?)";

    private static final String UPDATE_REVIEW = "UPDATE user_review SET review_text=?, stars_count=?, " +
            "date=? WHERE user_left_id=? AND NOT flag_review_deleted;";

    private static final String DELETE_REVIEW = "UPDATE user_review SET flag_review_deleted = 1 WHERE review_id = ?";

    private static final String GET_ALL_REVIEWS = "SELECT review_id, user_left_id, login, review_text, date, " +
            "stars_count FROM user_review JOIN user ON (user_review.user_left_id = user.user_id) WHERE NOT flag_review_deleted; ";

    private static final String GET_REVIEW_BY_ID = "SELECT review_id, user_left_id, login, review_text, date, " +
            "stars_count FROM user_review JOIN user ON (user_review.user_left_id = user.user_id) " +
            "WHERE review_id = ? AND NOT flag_review_deleted;";



    @Override
    public void executeCreateQuery(PreparedStatement ps, Review review) throws SQLException {

        ps.setInt(1, review.getUserLeftId());
        ps.setString(2, review.getText());
        ps.setInt(3, review.getStarsCount());
        ps.setDate(4, review.getDate());
    }


    @Override
    public void executeUpdateQuery(PreparedStatement ps, Review review) throws SQLException {

        ps.setString(1, review.getText());
        ps.setInt(2, review.getStarsCount());
        ps.setDate(3, review.getDate());
        ps.setInt(4, review.getUserLeftId());
    }


    @Override
    public void executeUpdateStatusQuery(PreparedStatement ps, String status, int offerId) {
        logger.log(Level.WARN, "Execute update status is not implemented for Review");
        throw new UnsupportedOperationException();
    }


    @Override
    public void executeDeleteQuery(PreparedStatement ps, int reviewId) throws SQLException {
        ps.setInt(1, reviewId);
    }


    @Override
    public String getCreateQuery() {
        return CREATE_REVIEW;
    }


    @Override
    public String getUpdateQuery() {
        return UPDATE_REVIEW;
    }


    @Override
    public String getUpdateStatusQuery() {
        return null;
    }


    @Override
    public String getDeleteQuery() {
        return DELETE_REVIEW;
    }


    @Override
    public String getQueryForAllObjects() {
        return GET_ALL_REVIEWS;
    }


    @Override
    public String getQueryForSingleObject() {
        return GET_REVIEW_BY_ID;
    }

    @Override
    public String getQueryForStatusCheck() {
        logger.log(Level.WARN, "Execute status check is not implemented for Review");
        throw new UnsupportedOperationException();
    }

    @Override
    public String getQueryForAllObjectsById() {
        logger.log(Level.WARN, "Getting all objects by ID is not implemented for Review");
        throw new UnsupportedOperationException();
    }


    @Override
    public String getQueryForAllObjectsByStatus() {
        logger.log(Level.WARN, "Getting all objects by status is not implemented for Review");
        throw new UnsupportedOperationException();
    }


    @Override
    public Review readResultSet(ResultSet rs) throws SQLException {
        Review review = new Review();

        review.setId(rs.getInt(1));
        review.setUserLeftId(rs.getInt(2));
        review.setUserLeftLogin(rs.getString(3));
        review.setText(rs.getString(4));
        review.setDate(rs.getDate(5));
        review.setStarsCount(rs.getInt(6));

        return review;
    }
}
