package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.BaseDAO;
import by.epam.cattery.dao.OfferDAO;
import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;

import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * {@inheritDoc}
 *
 * @param {@link Offer} the type parameter
 *
 */
public class OfferDAOImpl extends BaseDAO<Offer> implements OfferDAO {
    private static final Logger logger = LogManager.getLogger(OfferDAOImpl.class);

    private static final String CREATE_OFFER =
            "INSERT INTO user_offer " +
                    "(user_made_offer_id, cat_description, price) " +
                    "VALUES(?, ?, ?);";

    private static final String UPDATE_OFFER =
            "UPDATE user_offer " +
                    "SET user_offer_status_id = ?, expert_message = ?, expert_message_to_admin = ?, price = ? " +
                    "WHERE offer_id = ? " +
                    "AND NOT flag_offer_deleted;";
    private static final String UPDATE_OFFER_STATUS =
            "UPDATE user_offer " +
                    "SET user_offer_status_id = ? " +
                    "WHERE offer_id = ? " +
                    "AND NOT flag_offer_deleted;";
    private static final String UPDATE_PHOTO =
            "UPDATE user_offer " +
                    "SET offered_cat_photo = ? " +
                    "WHERE offer_id = ?;";

    private static final String DELETE_OFFER =
            "UPDATE user_offer " +
                    "SET flag_offer_deleted = 1 " +
                    "WHERE offer_id = ?;";
    private static final String DELETE_OFFERS_BY_USER_ID =
            "UPDATE user_offer " +
                    "SET flag_offer_deleted = 1 " +
                    "WHERE user_made_offer_id = ? " +
                    "AND user_offer_status_id = ?;";

    private static final String GET_ALL_OFFERS_BY_STATUS =
            "SELECT offer_id, name, lastname, phone, cat_description, price, user_offer_status_id, expert_message, " +
            "expert_message_to_admin, user_made_offer_id, offered_cat_photo " +
                    "FROM user_offer " +
                        "JOIN user ON (user_offer.user_made_offer_id = user.user_id) " +
                    "WHERE user_offer_status_id = ? " +
                    "AND NOT flag_offer_deleted " +
                    "ORDER BY name LIMIT ? OFFSET ?;";
    private static final String GET_ALL_OFFERS_BY_USER_ID =
            "SELECT offer_id, name, lastname, phone, cat_description, price, user_offer_status_id, expert_message, " +
            "expert_message_to_admin, user_made_offer_id, offered_cat_photo " +
                    "FROM user_offer " +
                        "JOIN user ON (user_offer.user_made_offer_id = user.user_id) " +
                    "WHERE user_made_offer_id = ? " +
                    "AND NOT flag_offer_deleted " +
                    "ORDER BY user_offer_status_id LIMIT ? OFFSET ?;";

    private static final String GET_OFFER_BY_ID =
            "SELECT offer_id, name, lastname, phone, cat_description, price, " +
            "user_offer_status_id, expert_message, expert_message_to_admin, user_made_offer_id, offered_cat_photo " +
                    "FROM user_offer " +
                        "JOIN user ON (user_offer.user_made_offer_id = user.user_id) " +
                    "WHERE offer_id = ? " +
                    "AND NOT flag_offer_deleted;";

    private static final String GET_OFFERS_COUNT =
            "SELECT COUNT(*) " +
                    "FROM user_offer " +
                    "WHERE NOT flag_offer_deleted;";
    private static final String GET_OFFERS_COUNT_BY_STATUS =
            "SELECT COUNT(*) " +
                    "FROM user_offer " +
                    "WHERE user_offer_status_id = ? " +
                    "AND NOT flag_offer_deleted;";
    private static final String GET_OFFERS_COUNT_BY_USER_ID =
            "SELECT COUNT(*) " +
                    "FROM user_offer " +
                        "JOIN user ON (user_offer.user_made_offer_id = user.user_id) " +
                    "WHERE user_made_offer_id = ? " +
                    "AND NOT flag_offer_deleted;";

    private static final String CHECK_OFFER_STATUS =
            "SELECT EXISTS " +
                    "(SELECT 1 " +
                        "FROM user_offer " +
                        "WHERE offer_id = ? " +
                        "AND user_offer_status_id = ? " +
                        "AND NOT flag_offer_deleted);";

    private static final String CHECK_OFFER_BELONGS_TO_USER =
            "SELECT EXISTS " +
                    "(SELECT 1 " +
                        "FROM user_offer " +
                        "WHERE user_made_offer_id = ? " +
                        "AND offer_id = ?);";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void deleteAwaitingOffersOfBannedUser(int userId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(DELETE_OFFERS_BY_USER_ID);
            ps.setInt(1, userId);
            ps.setString(2, OfferStatus.AWAIT.toString());

            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during deleting all offers by user Id", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean checkOfferBelongsToUser(int userId, int offerId)  throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean exists;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(CHECK_OFFER_BELONGS_TO_USER);
            ps.setInt(1, userId);
            ps.setInt(2, offerId);

            rs = ps.executeQuery();
            rs.next();
            exists = rs.getBoolean(1);

            return exists;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during checking whether user has rights to add photo", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void executeCreateQuery(PreparedStatement ps, Offer offer) throws SQLException {

        ps.setInt(1, offer.getUserMadeOfferId());
        ps.setString(2, offer.getCatDescription());
        ps.setDouble(3, offer.getPrice());
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void executeUpdateQuery(PreparedStatement ps, Offer offer) throws SQLException {

        ps.setString(1, offer.getStatus().toString());
        ps.setString(2, offer.getExpertMessage());
        ps.setString(3, offer.getExpertMessageToAdmin());
        ps.setDouble(4, offer.getPrice());
        ps.setInt(5, offer.getId());
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void executeUpdateStatusQuery(PreparedStatement ps, String status, int offerId) throws SQLException {

        ps.setString(1, status);
        ps.setInt(2, offerId);
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void executeDeleteQuery(PreparedStatement ps, int offerId) throws SQLException {
        ps.setInt(1, offerId);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getCreateQuery() {
        return CREATE_OFFER;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getUpdateQuery() {
        return UPDATE_OFFER;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getUpdateStatusQuery() {
        return UPDATE_OFFER_STATUS;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getUpdatePhotoQuery() {
        return UPDATE_PHOTO;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getDeleteQuery() {
        return DELETE_OFFER;
    }

    /**
     * {@inheritDoc}
     *
     * Throws {@link UnsupportedOperationException} because not implemented for Offer
     *
     */
    @Override
    public String getQueryForAllObjects() {
        logger.log(Level.WARN, "Not implemented for Qffer");
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getQueryForAllObjectsByStatus() {
        return GET_ALL_OFFERS_BY_STATUS;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getQueryForTotalCountByStatus() {
        return GET_OFFERS_COUNT_BY_STATUS;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getQueryForTotalCountById() {
        return GET_OFFERS_COUNT_BY_USER_ID;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getQueryForAllObjectsById() {
        return GET_ALL_OFFERS_BY_USER_ID;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getQueryForTotalCount() {
        return GET_OFFERS_COUNT;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getQueryForSingleObject() {
        return GET_OFFER_BY_ID;
    }

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String getQueryForStatusCheck() {
        return CHECK_OFFER_STATUS;
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * {@inheritDoc}
     *
     */
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
        offer.setPhoto(rs.getString(11));

        return offer;
    }
}
