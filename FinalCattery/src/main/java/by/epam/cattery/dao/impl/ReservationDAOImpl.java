package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.BaseDAO;
import by.epam.cattery.dao.ReservationDAO;

import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.Reservation;
import by.epam.cattery.entity.ReservationStatus;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ReservationDAOImpl extends BaseDAO<Reservation> implements ReservationDAO {
    private static final Logger logger = LogManager.getLogger(ReservationDAOImpl.class);

    private static final int DAYS_TILL_RESERVATION_EXPIRES = 3;

    private static final String CREATE_RESERVATION =
            "INSERT INTO user_reservation " +
                    "(user_id, cat_id, pedigree_type, reservation_date, total_cost) " +
                    "VALUES(?, ?, ?, ?, ?);";

    private static final String UPDATE_RESERVATION =
            "UPDATE user_reservation " +
                    "SET user_id = ?, cat_id = ?, pedigree_type = ?, reservation_date = ?, total_cost = ?, reservation_status = ? " +
                    "WHERE reservation_id = ? " +
                    "AND NOT flag_reservation_deleted;";
    private static final String UPDATE_RESERVATION_STATUS_AND_DATE =
            "UPDATE user_reservation " +
                    "SET reservation_date = ?, reservation_status = ? " +
                    "WHERE reservation_id = ? " +
                    "AND NOT flag_reservation_deleted;";
    private static final String UPDATE_PHOTO =
            "UPDATE user_reservation " +
                    "SET cheque_photo = ? " +
                    "WHERE reservation_id = ?";

    private static final String DELETE_RESERVATION =
            "UPDATE user_reservation " +
                    "SET flag_reservation_deleted = 1 " +
                    "WHERE reservation_id = ?;";
    private static final String DELETE_RESERVATIONS_BY_USER_ID =
            "UPDATE user_reservation " +
                    "SET flag_reservation_deleted = 1 " +
                    "WHERE user_id = ? " +
                    "AND reservation_status = ?;";

    private static final String GET_ALL_RESERVATIONS_BY_STATUS =
            "SELECT reservation_id, user.name, user.lastname, cat.name, cat.lastname, pedigree_type, reservation_date, " +
                    "timestampdiff(DAY, reservation_date, now()) > " + DAYS_TILL_RESERVATION_EXPIRES +
                    ", total_cost, reservation_status, cat_photo, cheque_photo " +
                    "FROM user_reservation " +
                        "JOIN user ON (user_reservation.user_id = user.user_id) " +
                        "JOIN cat ON (user_reservation.cat_id = cat.cat_id) " +
                    "WHERE reservation_status = ? " +
                    "AND NOT flag_reservation_deleted " +
                    "ORDER BY user.name LIMIT ? OFFSET ?;";
    private static final String GET_RESERVATIONS_BY_USER_ID =
            "SELECT reservation_id, user.name, user.lastname, cat.name, cat.lastname, pedigree_type, reservation_date, " +
                    "timestampdiff(DAY, reservation_date, now()) > " + DAYS_TILL_RESERVATION_EXPIRES +
                    ", total_cost, reservation_status, cat_photo, cheque_photo " +
                    "FROM user_reservation " +
                        "JOIN user ON (user_reservation.user_id = user.user_id) " +
                        "JOIN cat ON (user_reservation.cat_id = cat.cat_id) " +
                    "WHERE user.user_id = ? " +
                    "AND NOT flag_reservation_deleted " +
                    "ORDER BY cat.name LIMIT ? OFFSET ?;";

    private static final String GET_RESERVATIONS_COUNT_BY_STATUS =
            "SELECT COUNT(*) " +
                    "FROM user_reservation " +
                    "WHERE reservation_status=? " +
                    "AND NOT flag_reservation_deleted";
    private static final String GET_RESERVATIONS_COUNT_BY_USER_ID =
            "SELECT COUNT(*) " +
                    "FROM user_reservation " +
                        "JOIN user ON (user_reservation.user_id = user.user_id) " +
                    "WHERE user.user_id=? " +
                    "AND NOT flag_reservation_deleted";

    private static final String GET_RESERVATION_BY_ID =
            "SELECT reservation_id, user.name, user.lastname, cat.name, cat.lastname, pedigree_type, reservation_date, " +
                    "timestampdiff(DAY, reservation_date, now()) > " + DAYS_TILL_RESERVATION_EXPIRES +
                    ", total_cost, reservation_status, cat_photo, cheque_photo " +
                    "FROM user_reservation " +
                        "JOIN user ON (user_reservation.user_id = user.user_id) " +
                        "JOIN cat ON (user_reservation.cat_id = cat.cat_id) " +
                    "WHERE reservation_id=? " +
                    "AND NOT flag_reservation_deleted;";

    private static final String SET_ALL_RESERVATIONS_EXPIRED_IF_TIME_PASSED =
            "UPDATE user_reservation " +
                    "SET reservation_status=? " +
                    "WHERE reservation_status=? " +
                    "AND timestampdiff(DAY, reservation_date, now()) > " + DAYS_TILL_RESERVATION_EXPIRES +
                    " AND NOT flag_reservation_deleted;";

    private static final String DELETE_EXPIRED_RESERVATIONS_BY_CAT_ID =
            "UPDATE user_reservation " +
                    "SET flag_reservation_deleted = 1 " +
                    "WHERE cat_id = ? " +
                    "AND reservation_status = ?;";

    private static final String GET_PEDIGREE_TYPES =
            "SELECT pedigree_type, " +
                    "COUNT(pedigree_type) " +
                    "FROM user_reservation " +
                    "WHERE reservation_status != ? " +
                    "AND NOT flag_reservation_deleted " +
                    "GROUP BY pedigree_type " +
                    "ORDER BY pedigree_type;";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public Map<CatPedigreeType, Integer> getPedigreeStatistics() throws DAOException {
        Map<CatPedigreeType, Integer> pedigreeCount = new HashMap<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(GET_PEDIGREE_TYPES);
            ps.setString(1, ReservationStatus.EXPD.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                pedigreeCount.put(CatPedigreeType.valueOf(rs.getString(1)), rs.getInt(2));
            }
            return pedigreeCount;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during counting pedigree statistics", e);
        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }


    @Override
    public void setAllReservationExpiredIfTimePassed() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(SET_ALL_RESERVATIONS_EXPIRED_IF_TIME_PASSED);
            ps.setString(1, ReservationStatus.EXPD.toString());
            ps.setString(2, ReservationStatus.NEW.toString());

            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during making reservations expired", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }


    @Override
    public void deleteAllExpiredReservationsWithReservedCat(int catId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(DELETE_EXPIRED_RESERVATIONS_BY_CAT_ID);
            ps.setInt(1, catId);
            ps.setString(2, ReservationStatus.EXPD.toString());

            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during deleting expired reservations with reserved cat", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }


    @Override
    public void deleteNewReservationsOfBannedUser(int userId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(DELETE_RESERVATIONS_BY_USER_ID);
            ps.setInt(1, userId);
            ps.setString(2, ReservationStatus.NEW.toString());

            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during deleting all reservations by user Id", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void executeCreateQuery(PreparedStatement ps, Reservation reservation) throws SQLException {

        ps.setInt(1, reservation.getUserMadeReservationId());
        ps.setInt(2, reservation.getCatId());
        ps.setString(3, reservation.getPedigreeType().toString());
        ps.setTimestamp(4, reservation.getDateOfReservation());
        ps.setDouble(5, reservation.getTotalCost());
    }


    @Override
    public void executeUpdateQuery(PreparedStatement ps, Reservation reservation) throws SQLException {

        ps.setInt(1, reservation.getUserMadeReservationId());
        ps.setInt(2, reservation.getCatId());
        ps.setString(3, reservation.getPedigreeType().toString());
        ps.setTimestamp(4, reservation.getDateOfReservation());
        ps.setDouble(5, reservation.getTotalCost());
        ps.setString(6, ReservationStatus.NEW.toString());
        ps.setInt(7, reservation.getId());
    }


    @Override
    public void executeUpdateStatusQuery(PreparedStatement ps, String status, int reservationId) throws SQLException {

        ps.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
        ps.setString(2, status);
        ps.setInt(3, reservationId);
    }


    @Override
    public void executeDeleteQuery(PreparedStatement ps, int reservationId) throws SQLException {
        ps.setInt(1, reservationId);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public String getCreateQuery() {
        return CREATE_RESERVATION;
    }


    @Override
    public String getUpdateQuery() {
        return UPDATE_RESERVATION;
    }


    @Override
    public String getUpdateStatusQuery() {
        return UPDATE_RESERVATION_STATUS_AND_DATE;
    }


    @Override
    public String getUpdatePhotoQuery() {
        return UPDATE_PHOTO;
    }


    @Override
    public String getDeleteQuery() {
        return DELETE_RESERVATION;
    }


    @Override
    public String getQueryForAllObjects() {
        logger.log(Level.WARN, "Getiing all objects not implemented for Reservation");
        throw new UnsupportedOperationException();
    }

    @Override
    public String getQueryForAllObjectsByStatus() {
        return GET_ALL_RESERVATIONS_BY_STATUS;
    }


    @Override
    public String getQueryForTotalCount() {
        logger.log(Level.WARN, "Counting all objects not implemented for Reservation");
        throw new UnsupportedOperationException();
    }

    @Override
    public String getQueryForTotalCountByStatus() {
        return GET_RESERVATIONS_COUNT_BY_STATUS;
    }

    @Override
    public String getQueryForTotalCountById() {
        return GET_RESERVATIONS_COUNT_BY_USER_ID;
    }

    @Override
    public String getQueryForSingleObject() {
        return GET_RESERVATION_BY_ID;
    }


    @Override
    public String getQueryForAllObjectsById() {
        return GET_RESERVATIONS_BY_USER_ID;
    }

    @Override
    public String getQueryForStatusCheck() {
        logger.log(Level.WARN, "Execute status check is not implemented for Reservation");
        throw new UnsupportedOperationException();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public Reservation readResultSet(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();

        reservation.setId(rs.getInt(1));
        reservation.setUserMadeReservationName(rs.getString(2));
        reservation.setUserMadeReservationLastname(rs.getString(3));
        reservation.setCatName(rs.getString(4));
        reservation.setCatLastname(rs.getString(5));
        reservation.setPedigreeType(CatPedigreeType.valueOf(rs.getString(6)));
        reservation.setDateOfReservation(rs.getTimestamp(7));
        reservation.setExpired(rs.getBoolean(8));
        reservation.setTotalCost(rs.getDouble(9));
        reservation.setStatus(ReservationStatus.valueOf(rs.getString(10)));
        reservation.setCatPhoto(rs.getString(11));
        reservation.setChequePhoto(rs.getString(12));

        return reservation;
    }
}
