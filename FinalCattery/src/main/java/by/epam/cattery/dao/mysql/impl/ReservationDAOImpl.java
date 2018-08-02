package by.epam.cattery.dao.mysql.impl;

import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.BaseDAO;
import by.epam.cattery.dao.mysql.ReservationDAO;

import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.Reservation;
import by.epam.cattery.entity.ReservationStatus;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

// Заменить тройки на константы какието - типа количество дней

public class ReservationDAOImpl extends BaseDAO<Reservation> implements ReservationDAO {
    private static final Logger logger = LogManager.getLogger(ReservationDAOImpl.class);

    private static final String CREATE_RESERVATION = "INSERT INTO user_reservation (user_id, cat_id, pedigree_type, " +
            "reservation_date, total_cost) VALUES(?,?,?,?,?)";

    private static final String UPDATE_RESERVATION = "UPDATE user_reservation SET " +
            "user_id=?, cat_id=?, pedigree_type=?, reservation_date=?, total_cost=?, " +
            "reservation_status=? WHERE reservation_id = ?;";
    private static final String UPDATE_RESERVATION_STATUS_AND_DATE = "UPDATE user_reservation SET " +
            "reservation_date=?, reservation_status=? WHERE reservation_id = ?;";

    private static final String DELETE_RESERVATION = "DELETE FROM user_reservation WHERE reservation_id = ?;";

    private static final String GET_ALL_RESERVATIONS = "SELECT reservation_id, user.name, user.lastname, cat.name, cat.lastname," +
            "pedigree_type, reservation_date, timestampdiff(DAY, reservation_date, now()) > 3, total_cost, reservation_status " +
            "FROM user_reservation JOIN user ON (user_reservation.user_id = user.user_id) " +
            "JOIN cat ON (user_reservation.cat_id = cat.cat_id)";
    private static final String GET_ALL_RESERVATION_BY_STATUS = "SELECT reservation_id, user.name, user.lastname, cat.name, cat.lastname," +
            "pedigree_type, reservation_date, timestampdiff(DAY, reservation_date, now()) > 3, total_cost, reservation_status " +
            "FROM user_reservation JOIN user ON (user_reservation.user_id = user.user_id) " +
            "JOIN cat ON (user_reservation.cat_id = cat.cat_id) WHERE reservation_status=?";
    private static final String GET_RESERVATION_BY_ID = "SELECT reservation_id, user.name, user.lastname, cat.name, cat.lastname," +
            "pedigree_type, reservation_date, timestampdiff(DAY, reservation_date, now()) > 3, total_cost, reservation_status " +
            "FROM user_reservation JOIN user ON (user_reservation.user_id = user.user_id) " +
            "JOIN cat ON (user_reservation.cat_id = cat.cat_id) WHERE reservation_id=?";
    private static final String GET_RESERVATIONS_BY_USER_ID = "SELECT reservation_id, user.name, user.lastname, cat.name, cat.lastname," +
            "pedigree_type, reservation_date, timestampdiff(DAY, reservation_date, now()) > 3, total_cost, reservation_status " +
            "FROM user_reservation JOIN user ON (user_reservation.user_id = user.user_id) " +
            "JOIN cat ON (user_reservation.cat_id = cat.cat_id) WHERE user.user_id=?";

    private static final String SET_ALL_RESERVATIONS_EXPIRED_IF_TIME_PASSED = "UPDATE user_reservation " +
            "SET reservation_status=? WHERE timestampdiff(DAY, reservation_date, now()) > 3;";

    private static final String GET_PEDIGREE_TYPES = "SELECT pedigree_type, COUNT(pedigree_type) " +
            "FROM user_reservation WHERE reservation_status != ? " +
            "GROUP BY pedigree_type ORDER BY pedigree_type;";


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
            connectionProvider.closeResultSetAndStatement(rs, ps);
        }
    }


    @Override
    public void setAllReservationExpiredWhenTimePasses() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(SET_ALL_RESERVATIONS_EXPIRED_IF_TIME_PASSED);
            ps.setString(1, ReservationStatus.EXPD.toString());

            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during making reservations expired", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeStatement(ps);
        }
    }



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
    public String getDeleteQuery() {
        return DELETE_RESERVATION;
    }


    @Override
    public String getQueryForAllObjects() {
        return GET_ALL_RESERVATIONS;
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
    public String getQueryForAllObjectsByStatus() {
        return GET_ALL_RESERVATION_BY_STATUS;
    }


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

        return reservation;
    }
}
