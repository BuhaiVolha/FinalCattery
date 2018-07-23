package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.ReviewDAO;
import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAOImpl implements ReviewDAO {
    private final ConnectionPool connectionPool;

    public ReviewDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<Review> findApprovedReviews() throws DAOException {
        List<Review> reviews = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement("SELECT review_id, user_left_id, login, review_text " +
                    "FROM user_review JOIN user ON (user_review.user_left_id = user.user_id) " +
                    "WHERE flag_review_approved");
            rs = ps.executeQuery();

            while (rs.next()) {
                Review review = new Review();

                review.setId(rs.getInt(1));
                review.setUserLeftId(rs.getInt(2));
                review.setUserLeftLogin(rs.getString(3));
                review.setText(rs.getString(4));

                reviews.add(review);
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);
        } catch (SQLException e) {
            throw new DAOException("Exception during gathering reviews", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }

        return reviews;
    }
}
