package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.ReviewDAO;
import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.ReservationStatus;
import by.epam.cattery.entity.Review;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAOImpl implements ReviewDAO {
    private static final Logger logger = LogManager.getLogger(ReviewDAOImpl.class);
    private final ConnectionPool connectionPool;


    private static final String FIND_CERTAIN_REVIEW = "SELECT review_id, review_text, stars_count FROM user_review " +
            "WHERE review_id = ?;";

    private static final String FIND_ALL_REVIEWS = "SELECT review_id, user_left_id, login, review_text, date, " +
            "stars_count FROM user_review JOIN user ON (user_review.user_left_id = user.user_id); ";

    private static final String ADD_REVIEW = "INSERT INTO user_review (user_left_id, review_text, stars_count, date) " +
            "VALUES(?,?,?,?)";

    private static final String CHANGE_USER_REVIEW_FLAG = "UPDATE user SET flag_review_left=1 " +
            "WHERE user_id = ? AND !flag_review_left;";

    private static final String RESET_USER_REVIEW_FLAG = "UPDATE user JOIN user_review " +
            "ON (user_id = user_left_id) SET flag_review_left=0 WHERE review_id=?;";

    private static final String DELETE_CERTAIN_REVIEW = "DELETE FROM user_review WHERE review_id = ?;";

    private static final String UPDATE_USER_REVIEW = "UPDATE user_review SET review_text=?, stars_count=?, " +
            "date=? WHERE user_left_id=?;";



    public ReviewDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Review> findAllReviews() throws DAOException {
        List<Review> reviews = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(FIND_ALL_REVIEWS);
            rs = ps.executeQuery();

            while (rs.next()) {
                Review review = new Review();

                review.setId(rs.getInt(1));
                review.setUserLeftId(rs.getInt(2));
                review.setUserLeftLogin(rs.getString(3));
                review.setText(rs.getString(4));
                review.setDate(rs.getDate(5));
                review.setStarsCount(rs.getInt(6));

                reviews.add(review);
            }
            return reviews;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);
        } catch (SQLException e) {
            throw new DAOException("Exception during gathering reviews", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }


    @Override
    public void addReview(Review review) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2;

        try {
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(ADD_REVIEW);

            ps.setInt(1, review.getUserLeftId());
            ps.setString(2, review.getText());
            ps.setInt(3, review.getStarsCount());
            ps.setDate(4, review.getDate());

            ps.executeUpdate();

            ps2 = con.prepareStatement(CHANGE_USER_REVIEW_FLAG);
            ps2.setInt(1, review.getUserLeftId());

            int i = ps2.executeUpdate();
            if (i != 1) {
                logger.log(Level.WARN, "An attempt to add already added review");
                throw new DAOException("Review has already been left! ");
            }

            con.commit();

        } catch (ConnectionPoolException | SQLException e) {
            try {
                con.rollback();
                throw new DAOException("Exception during adding review", e);

            } catch (SQLException e1) {

                throw new DAOException("Exception while rollback after error during adding review", e);
            } finally {
                connectionPool.closeConnection(con, ps);
            }
        }
    }


    @Override
    public void deleteReview(int reviewId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2;

        try {
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(RESET_USER_REVIEW_FLAG);
            ps.setInt(1, reviewId);

            ps.executeUpdate();

            ps2 = con.prepareStatement(DELETE_CERTAIN_REVIEW);
            ps2.setInt(1, reviewId);

            ps2.executeUpdate();

            con.commit();

        } catch (ConnectionPoolException | SQLException e) {
            try {
                con.rollback();
                throw new DAOException("Exception during deleting review", e);

            } catch (SQLException e1) {

                throw new DAOException("Exception while rollback after error during deleting review", e);
            } finally {
                connectionPool.closeConnection(con, ps);
            }
        }
    }


    @Override
    public void updateReview(Review review) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(UPDATE_USER_REVIEW);
            ps.setString(1, review.getText());
            ps.setInt(2, review.getStarsCount());
            ps.setDate(3, review.getDate());
            ps.setInt(4, review.getUserLeftId());

            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);
        } catch (SQLException e) {
            throw new DAOException("Exception during updating review", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }


    @Override
    public Review findSingleReview(int reviewId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Review review = null;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(FIND_CERTAIN_REVIEW);
            ps.setInt(1, reviewId);
            rs = ps.executeQuery();

            boolean reviewExists = rs.next();

            if (reviewExists) {
                review = new Review();

                review.setId(rs.getInt(1));
                review.setText(rs.getString(2));
                review.setStarsCount(rs.getInt(3));
            }
            return review;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception while finding review in bd ", e);

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }
}
