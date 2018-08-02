package by.epam.cattery.dao.mysql.impl;

import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.BaseDAO;
import by.epam.cattery.dao.mysql.OfferDAO;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferDAOImpl extends BaseDAO<Offer> implements OfferDAO {
    private static final String CREATE_OFFER = "INSERT INTO user_offer (user_made_offer_id, " +
            "cat_description, price) VALUES(?, ?, ?)";

    private static final String UPDATE_OFFER = "UPDATE user_offer SET user_offer_status_id = ?, " +
            "expert_message = ?, expert_message_to_admin = ?, price = ? WHERE offer_id = ?;";
    private static final String UPDATE_OFFER_STATUS = "UPDATE user_offer SET user_offer_status_id=? " +
            "WHERE offer_id = ?;";

    private static final String DELETE_OFFER = "DELETE FROM user_offer WHERE offer_id = ?;";

    private static final String GET_ALL_OFFERS = "SELECT offer_id, name, lastname, phone, " +
            "cat_description, price, user_offer_status_id, expert_message, expert_message_to_admin, user_made_offer_id FROM user_offer " +
            "JOIN user ON (user_offer.user_made_offer_id = user.user_id) ";
    private static final String GET_OFFER_BY_ID = "SELECT offer_id, name, lastname, phone, " +
            "cat_description, price, user_offer_status_id, expert_message, expert_message_to_admin, user_made_offer_id FROM user_offer " +
            "JOIN user ON (user_offer.user_made_offer_id = user.user_id) WHERE offer_id = ?;";

    private static final String CONDITION_BY_OFFER_STATUS = " WHERE user_offer_status_id = ?;";
    private static final String CONDITION_BY_USER_ID = " WHERE user_made_offer_id = ?;"; // соедиить

    private static final String CHECK_OFFER_STATUS = "SELECT EXISTS (SELECT 1 FROM user_offer " +
            "WHERE offer_id =? AND user_offer_status_id=?)";


    @Override
    public boolean checkOfferStatus(int offerId, String statusToCheck) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean statusMatches;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(CHECK_OFFER_STATUS);
            ps.setInt(1, offerId);
            ps.setString(2, statusToCheck);

            rs = ps.executeQuery();
            rs.next();
            statusMatches = rs.getBoolean(1);

            if (!statusMatches) {
                throw new DAOException("Status wasn't right for the operation");
            }
            return statusMatches;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during checking offer status", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResultSetAndStatement(rs, ps);
        }
    }


    @Override
    public void executeCreateQuery(PreparedStatement ps, Offer offer) throws SQLException {

        ps.setInt(1, offer.getUserMadeOfferId());
        ps.setString(2, offer.getCatDescription());
        ps.setDouble(3, offer.getPrice());
    }


    @Override
    public void executeUpdateQuery(PreparedStatement ps, Offer offer) throws SQLException {

        ps.setString(1, offer.getStatus().toString()); // null проверка
        ps.setString(2, offer.getExpertMessage());
        ps.setString(3, offer.getExpertMessageToAdmin());
        ps.setDouble(4, offer.getPrice());
        ps.setInt(5, offer.getId());
    }


    @Override
    public void executeUpdateStatusQuery(PreparedStatement ps, String status, int offerId) throws SQLException {

        ps.setString(1, status);
        ps.setInt(2, offerId);
    }


    @Override
    public void executeDeleteQuery(PreparedStatement ps, int offerId) throws SQLException {
        ps.setInt(1, offerId);
    }


    @Override
    public String getCreateQuery() {
        return CREATE_OFFER;
    }


    @Override
    public String getUpdateQuery() {
        return UPDATE_OFFER;
    }


    @Override
    public String getUpdateStatusQuery() {
        return UPDATE_OFFER_STATUS;
    }


    @Override
    public String getDeleteQuery() {
        return DELETE_OFFER;
    }


    @Override
    public String getQueryForAllObjects() {
        return GET_ALL_OFFERS;
    }


    @Override
    public String getQueryForSingleObject() {
        return GET_OFFER_BY_ID;
    }


    @Override
    public String getQueryForAllObjectsById() {
        return GET_ALL_OFFERS + CONDITION_BY_USER_ID;
    }


    @Override
    public String getQueryForAllObjectsByStatus() {
        return GET_ALL_OFFERS + CONDITION_BY_OFFER_STATUS;
    }


    @Override
    public Offer readResultSet(ResultSet rs) throws SQLException {
        Offer offer = new Offer();

        offer.setId(rs.getInt(1));
        offer.setUserMadeOfferName(rs.getString(2));
        offer.setUserMadeOfferLastname(rs.getString(3));
        offer.setUserMadeOfferPhone(rs.getString(4));
        offer.setCatDescription(rs.getString(5));
        offer.setPrice(rs.getDouble(6));
        offer.setStatus(OfferStatus.valueOf(rs.getString(7)));
        offer.setExpertMessage(rs.getString(8));
        offer.setExpertMessageToAdmin(rs.getString(9));
        offer.setUserMadeOfferId(rs.getInt(10));

        return offer;
    }
}
