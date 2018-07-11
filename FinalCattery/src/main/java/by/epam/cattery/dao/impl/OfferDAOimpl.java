package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.OfferDAO;
import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferDAOimpl implements OfferDAO {
    private final ConnectionPool connectionPool;

    private static final String ADD_OFFER = "INSERT INTO user_offer (user_made_offer_id, " +
            "cat_description, price) VALUES(?,?,?)";
    private static final String SELECT_ALL_OFFERS = "SELECT offer_id, name, lastname, phone, " +
            "cat_description, price, offer_status, expert_message FROM user_offer " +
            "JOIN user ON (user_offer.user_made_offer_id = user.user_id) " +
            "JOIN user_offer_status ON (user_offer.user_offer_status_id = " +
            "user_offer_status.offer_status_id)";
    private static final String CHANGE_OFFER_STATUS = "UPDATE `user_offer` SET `user_offer_status_id`=?, `" +
            "expert_message`=? WHERE `offer_id` = ?;";

    public OfferDAOimpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean makeOffer(Offer offer) throws DAOException { // ретурнить ИД офера?
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(ADD_OFFER, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, offer.getUserMadeOfferId());
            ps.setString(2, offer.getCatDescription());
            ps.setDouble(3, offer.getPrice());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();

            return rs.next();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("error offering a kitten in dao ", e);  // Отдельно каждый

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }

// переделать как со статусом????
    @Override
    public List<Offer> findAllOffersByUserId(String id) throws DAOException {
        List<Offer> offers = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SELECT_ALL_OFFERS + " WHERE user_made_offer_id = ?;");
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                Offer offer = new Offer(); // Отдельн метод
                offer.setId(rs.getInt(1));
                offer.setUserMadeOfferName(rs.getString(2));
                offer.setUserMadeOfferLastname(rs.getString(3));
                offer.setUserMadeOfferPhone(rs.getString(4));
                offer.setCatDescription(rs.getString(5));
                offer.setPrice(rs.getDouble(6));
                offer.setStatus(OfferStatus.valueOf(rs.getString(7)));
                offer.setExpertMessage(rs.getString(8));

                offers.add(offer);
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException("error while connecting via pool", e);
        } catch (SQLException e) {
            throw new DAOException("error during gathering offers", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }

        return offers;
    }


    @Override
    public List<Offer> findAllOffersByStatus(String status) throws DAOException {
        List<Offer> offers = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(SELECT_ALL_OFFERS + " WHERE offer_status = ?;");
            ps.setString(1, status);
            rs = ps.executeQuery();

            while (rs.next()) {
                Offer offer = new Offer();  // Отдельн метод
                offer.setId(rs.getInt(1));
                offer.setUserMadeOfferName(rs.getString(2));
                offer.setUserMadeOfferLastname(rs.getString(3));
                offer.setUserMadeOfferPhone(rs.getString(4));
                offer.setCatDescription(rs.getString(5));
                offer.setPrice(rs.getDouble(6));
                offer.setStatus(OfferStatus.valueOf(rs.getString(7)));
                offer.setExpertMessage(rs.getString(8));

                offers.add(offer);
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException("error while connecting via pool", e);
        } catch (SQLException e) {
            throw new DAOException("error during gathering offers", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }

        return offers;
    }


    @Override
    public void declineOffer(String id, String expertMessage, String status) throws DAOException  {
        Connection con = null;
        PreparedStatement ps = null;
//        private static final String CHANGE_OFFER_STATUS = "UPDATE `user_offer` SET `user_offer_status_id`=?, `" +
//                "expert_message`=? WHERE `offer_id` = ?;";

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(CHANGE_OFFER_STATUS);
            ps.setString(1, OfferStatus.REJECTED.getDateBaseId()); // !!!!
            ps.setString(2, id);
            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("error while connecting via pool", e);
        } catch (SQLException e) {
            throw new DAOException("error during gathering offers", e);
        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }
}
