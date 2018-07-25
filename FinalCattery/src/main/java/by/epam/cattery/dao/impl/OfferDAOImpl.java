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

public class OfferDAOImpl implements OfferDAO {
    private final ConnectionPool connectionPool;

    private static final String ADD_OFFER = "INSERT INTO user_offer (user_made_offer_id, " +
            "cat_description, price) VALUES(?,?,?)";

    private static final String SELECT_ALL_OFFERS = "SELECT offer_id, name, lastname, phone, " +
            "cat_description, price, user_offer_status_id, expert_message, expert_message_to_admin FROM user_offer " +
            "JOIN user ON (user_offer.user_made_offer_id = user.user_id) ";

    private static final String SELECT_SINGLE_OFFER = "SELECT offer_id, name, lastname, phone, " +
            "cat_description, price, user_offer_status_id, expert_message, expert_message_to_admin, user_made_offer_id FROM user_offer " +
            "JOIN user ON (user_offer.user_made_offer_id = user.user_id) ";

    private static final String UPDATE_OFFER_STATUS_FOR_USER = "UPDATE `user_offer` SET `user_offer_status_id`=?, `" +
            "expert_message`=? WHERE `offer_id` = ?;";

    private static final String UPDATE_OFFER_STATUS_FOR_ADMIN = "UPDATE user_offer SET user_offer_status_id=?, " +
            "expert_message_to_admin=? WHERE offer_id = ?;";

    private static final String UPDATE_OFFER_DETAILS_FOR_USER = "UPDATE `user_offer` SET `user_offer_status_id`=?, `" +
            "expert_message`=?, price=? WHERE `offer_id` = ?;";

    private static final String DELETE_CERTAIN_OFFER = "DELETE FROM user_offer WHERE offer_id = ?;";

    private static final String CONDITION_BY_OFFER_STATUS = " WHERE user_offer_status_id = ?;";
    private static final String CONDITION_BY_USER = " WHERE user_made_offer_id = ?;";
    private static final String CONDITION_BY_OFFER = "WHERE offer_id = ?;";


    public OfferDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public boolean addOffer(Offer offer) throws DAOException {
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
            throw new DAOException("Exception offering a kitten in dao ", e);  // Отдельно каждый

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }


    @Override
    public List<Offer> findAllOffersByUserId(String id) throws DAOException {
        List<Offer> offers = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(SELECT_ALL_OFFERS + CONDITION_BY_USER);
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
            return offers;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);
        } catch (SQLException e) {
            throw new DAOException("Exception during gathering offers", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }


    @Override
    public List<Offer> findAllOffersByStatus(OfferStatus status) throws DAOException {
        List<Offer> offers = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(SELECT_ALL_OFFERS + CONDITION_BY_OFFER_STATUS);
            ps.setString(1, status.toString());
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
                offer.setExpertMessageToAdmin(rs.getString(9));

                offers.add(offer);
            }
            return offers;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);
        } catch (SQLException e) {
            throw new DAOException("Exception during gathering offers by status", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }


    @Override
    public void changeOfferStatus(Offer offer, OfferStatus status, boolean forAdmin) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();

            if (forAdmin) {
                ps = con.prepareStatement(UPDATE_OFFER_STATUS_FOR_ADMIN);
                ps.setString(2, offer.getExpertMessageToAdmin());
            } else {
                ps = con.prepareStatement(UPDATE_OFFER_STATUS_FOR_USER);
                ps.setString(2, offer.getExpertMessage());
            }

            ps.setString(1, status.toString());
            ps.setInt(3, offer.getId());
            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);

        } catch (SQLException e) {
            throw new DAOException("Exception during changing offer status", e);

        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }


    @Override
    public void changeOfferStatusAndPrice(Offer offer) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_OFFER_DETAILS_FOR_USER); // разбить на фрагменты

            ps.setString(1, OfferStatus.DISC.toString());
            ps.setString(2, offer.getExpertMessage());
            System.out.println(offer.getPrice());
            ps.setDouble(3, offer.getPrice());
            ps.setInt(4, offer.getId());

            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);

        } catch (SQLException e) {
            throw new DAOException("Exception during changing offer's status and price", e);

        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }


    @Override
    public Offer findSingleOffer(String id) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Offer offer = null;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(SELECT_SINGLE_OFFER + CONDITION_BY_OFFER);
            ps.setString(1, id);
            rs = ps.executeQuery();
            boolean offerExists = rs.next();

            if (offerExists) {
                offer = new Offer();

                offer.setId(rs.getInt(1));  // Отдельным методом
                offer.setUserMadeOfferName(rs.getString(2));
                offer.setUserMadeOfferLastname(rs.getString(3));
                offer.setUserMadeOfferPhone(rs.getString(4));
                offer.setCatDescription(rs.getString(5));
                offer.setPrice(rs.getDouble(6));
                offer.setStatus(OfferStatus.valueOf(rs.getString(7)));
                offer.setExpertMessage(rs.getString(8));
                offer.setExpertMessageToAdmin(rs.getString(9));
                offer.setUserMadeOfferId(rs.getInt(10));
            }
            return offer;

        } catch (ConnectionPoolException | SQLException e) {

            throw new DAOException("Exception while finding user in bd ", e);

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }


    @Override
    public void deleteOffer(int offerId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(DELETE_CERTAIN_OFFER);
            ps.setInt(1, offerId);
            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception while deleting offer from bd ", e);

        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }
}
