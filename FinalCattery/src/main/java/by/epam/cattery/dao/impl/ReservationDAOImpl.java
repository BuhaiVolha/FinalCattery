package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.ReservationDAO;
import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationDAOImpl implements ReservationDAO {
    private static final Logger logger = LogManager.getLogger(ReservationDAOImpl.class);
    private final ConnectionPool connectionPool;


    private static final String ADD_RESERVATION = "INSERT INTO user_reservation (user_id, cat_id, pedigree_type, " +
            "reservation_date, total_cost) VALUES(?,?,?,?,?)";

    private static final String MAKE_CAT_RESERVED = "UPDATE cat SET sale_status_id = ? WHERE cat_id = ? " +
            "AND sale_status_id != ?;";

    private static final String MAKE_CAT_RESERVED_IF_AVAILABLE = "UPDATE cat JOIN user_reservation " +
            "ON (cat.cat_id = user_reservation.cat_id) SET sale_status_id=? " +
            "WHERE cat.sale_status_id=? AND user_reservation.reservation_id=?;";

    private static final String MAKE_CAT_SOLD = "UPDATE cat JOIN user_reservation " +
            "ON (cat.cat_id = user_reservation.cat_id) SET sale_status_id=? " +
            "WHERE cat.sale_status_id=? AND user_reservation.reservation_id=?;";

    private static final String FIND_ALL_RESERVATION = "SELECT reservation_id, user.name, user.lastname, cat.name, cat.lastname," +
            "pedigree_type, reservation_date, timestampdiff(DAY, reservation_date, now()) > 3, total_cost " +
            "FROM user_reservation JOIN user ON (user_reservation.user_id = user.user_id) " +
            "JOIN cat ON (user_reservation.cat_id = cat.cat_id) WHERE reservation_status=?";

    private static final String FIND_RESERVATIONS_FOR_CERTAIN_USER = "SELECT reservation_id, cat.name, cat.lastname," +
            "pedigree_type, reservation_date, timestampdiff(DAY, reservation_date, now()) > 3, total_cost, " +
            "reservation_status FROM user_reservation JOIN cat ON (user_reservation.cat_id = cat.cat_id)" +
            " WHERE user_id =?";

    private static final String MAKE_RESERVED_CATS_AVAILABLE = "UPDATE cat JOIN user_reservation " +
            "ON (cat.cat_id = user_reservation.cat_id) SET sale_status_id=? " +
            "WHERE timestampdiff(DAY, user_reservation.reservation_date, now()) > 3;";

    private static final String MAKE_RESERVED_CAT_AVAILABLE = "UPDATE cat JOIN user_reservation " +
            "ON (cat.cat_id = user_reservation.cat_id) SET sale_status_id=? " +
            "WHERE user_reservation.reservation_id=?;";

    private static final String MAKE_RESERVATION_EXPIRED = "UPDATE user_reservation " +
            "SET reservation_status=? WHERE timestampdiff(DAY, reservation_date, now()) > 3;";

    private static final String DELETE_CERTAIN_RESERVATION = "DELETE FROM user_reservation WHERE reservation_id = ?;";

    private static final String UPDATE_RESERVATION_DETAILS_AFTER_RENEW = "UPDATE user_reservation SET reservation_date=?, " +
            "reservation_status=? WHERE reservation_id = ?;";

    private static final String CHANGE_RESERVATION_STATUS = "UPDATE user_reservation SET " +
            "reservation_status=? WHERE reservation_id = ?;";

    private static final String COUNT_PEDIGREE_TYPES = "SELECT pedigree_type, COUNT(pedigree_type) " +
            "FROM user_reservation WHERE reservation_status != ? " +
            "GROUP BY pedigree_type ORDER BY pedigree_type;";


    public ReservationDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public void addReservation(Reservation reservation) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2;

        try {
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(ADD_RESERVATION);

            ps.setInt(1, reservation.getUserMadeReservationId());
            ps.setInt(2, reservation.getCatId());
            ps.setString(3, reservation.getPedigreeType().toString());
            ps.setTimestamp(4, reservation.getDateOfReservation());
            ps.setDouble(5, reservation.getTotalCost());

            ps.executeUpdate();

            ps2 = con.prepareStatement(MAKE_CAT_RESERVED);
            ps2.setString(1, CatStatus.RSRV.toString());
            ps2.setInt(2, reservation.getCatId());
            ps2.setString(3, CatStatus.RSRV.toString());

            int i = ps2.executeUpdate();

            if (i != 1) {
                logger.log(Level.WARN, "An attempt to reserve already reserved cat");
                throw new DAOException("Attempt to reserve already reserved cat");
            }

            con.commit();

        } catch (ConnectionPoolException | SQLException e) {

            try {
                con.rollback();
                throw new DAOException("Exception during reserving cat", e);

            } catch (SQLException e1) {
                throw new DAOException("Exception while rollback after error during reserving cat", e);

            } finally {
                connectionPool.closeConnection(con, ps);
            }
        }
    }


    @Override
    public List<Reservation> findAllReservations() throws DAOException {
        List<Reservation> reservations = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(FIND_ALL_RESERVATION);
            ps.setString(1, ReservationStatus.NEW.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation(); // Отдельн метод

                reservation.setId(rs.getInt(1));
                reservation.setUserMadeReservationName(rs.getString(2));
                reservation.setUserMadeReservationLastname(rs.getString(3));
                reservation.setCatName(rs.getString(4));
                reservation.setCatLastname(rs.getString(5));
                reservation.setPedigreeType(CatPedigreeType.valueOf(rs.getString(6)));
                reservation.setDateOfReservation(rs.getTimestamp(7));
                reservation.setExpired(rs.getBoolean(8));
                reservation.setTotalCost(rs.getDouble(9));

                reservations.add(reservation);
            }

            return reservations;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);
        } catch (SQLException e) {
            throw new DAOException("Exception during gathering reservations", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }


    @Override
    public List<Reservation> findAllReservationsForUser(int userId) throws DAOException {
        List<Reservation> reservations = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(FIND_RESERVATIONS_FOR_CERTAIN_USER);
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Reservation reservation = new Reservation(); // Отдельн метод

                reservation.setId(rs.getInt(1));
                reservation.setCatName(rs.getString(2));
                reservation.setCatLastname(rs.getString(3));
                reservation.setPedigreeType(CatPedigreeType.valueOf(rs.getString(4)));
                reservation.setDateOfReservation(rs.getTimestamp(5));
                reservation.setExpired(rs.getBoolean(6));
                reservation.setTotalCost(rs.getDouble(7));
                reservation.setStatus(ReservationStatus.valueOf(rs.getString(8)));

                reservations.add(reservation);
            }

            return reservations;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);
        } catch (SQLException e) {
            throw new DAOException("Exception during gathering reservations", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }


    @Override
    public void declineExpiredReservations() throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2;

        try {
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(MAKE_RESERVED_CATS_AVAILABLE);
            ps.setString(1, CatStatus.AVAIL.toString());

            ps.executeUpdate();

            ps2 = con.prepareStatement(MAKE_RESERVATION_EXPIRED);
            ps2.setString(1, ReservationStatus.EXPD.toString());

            ps2.executeUpdate();

            con.commit();

        } catch (ConnectionPoolException | SQLException e) {
            try {
                con.rollback();
                throw new DAOException("Exception during declining expired reservations", e);

            } catch (SQLException e1) {

                throw new DAOException("Exception while rollback after error during declining expired reservations", e);
            } finally {
                connectionPool.closeConnection(con, ps);
            }
        }
    }


    @Override
    public void deleteReservation(int reservationId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(DELETE_CERTAIN_RESERVATION);
            ps.setInt(1, reservationId);
            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception while deleting reservation from bd ", e);

        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }


    @Override
    public void cancelReservation(int reservationId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2;

        try {
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(MAKE_RESERVED_CAT_AVAILABLE);
            ps.setString(1, CatStatus.AVAIL.toString());
            ps.setInt(2, reservationId);

            ps.executeUpdate();

            ps2 = con.prepareStatement(DELETE_CERTAIN_RESERVATION);
            ps2.setInt(1, reservationId);

            ps2.executeUpdate();

            con.commit();

        } catch (ConnectionPoolException | SQLException e) {
            try {
                con.rollback();
                throw new DAOException("Exception during cancelling reservation", e);

            } catch (SQLException e1) {

                throw new DAOException("Exception while rollback after error during cancelling reservation", e);
            } finally {
                connectionPool.closeConnection(con, ps);
            }
        }
    }


    @Override
    public void renewReservation(int reservationId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2;

        try {
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(MAKE_CAT_RESERVED_IF_AVAILABLE);
            ps.setString(1, CatStatus.RSRV.toString());
            ps.setString(2, CatStatus.AVAIL.toString());
            ps.setInt(3, reservationId);

            int i = ps.executeUpdate();
            if (i != 1) {
                logger.log(Level.WARN, "An attempt to reserve a cat which is not available");
                throw new DAOException("The cat is not available! ");
            }

            ps2 = con.prepareStatement(UPDATE_RESERVATION_DETAILS_AFTER_RENEW);
            ps2.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            ps2.setString(2, ReservationStatus.NEW.toString());
            ps2.setInt(3, reservationId);

            ps2.executeUpdate();

            con.commit();

        } catch (ConnectionPoolException | SQLException e) {
            try {
                con.rollback();
                throw new DAOException("Exception during renewing reservation", e);

            } catch (SQLException e1) {

                throw new DAOException("Exception while rollback after error during renewing reservation", e);
            } finally {
                connectionPool.closeConnection(con, ps);
            }
        }
    }


    @Override
    public void sellCat(int reservationId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement ps2;

        try {
            con = connectionPool.takeConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(MAKE_CAT_SOLD);
            ps.setString(1, CatStatus.SOLD.toString());
            ps.setString(2, CatStatus.RSRV.toString());
            ps.setInt(3, reservationId);

            int i = ps.executeUpdate();
            if (i != 1) {
                logger.log(Level.WARN, "An attempt to sell a cat which is not available");
                throw new DAOException("The cat is not available! ");
            }

            ps2 = con.prepareStatement(CHANGE_RESERVATION_STATUS);
            ps2.setString(1, ReservationStatus.DONE.toString());
            ps2.setInt(2, reservationId);

            ps2.executeUpdate();

            con.commit();

        } catch (ConnectionPoolException | SQLException e) {
            try {
                con.rollback();
                throw new DAOException("Exception during selling cat", e);

            } catch (SQLException e1) {

                throw new DAOException("Exception while rollback after error during selling cat", e);
            } finally {
                connectionPool.closeConnection(con, ps);
            }
        }
    }


    @Override
    public Map<CatPedigreeType, Integer> countPedigree() throws DAOException {
        Map<CatPedigreeType, Integer> pedigreeCount = new HashMap<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(COUNT_PEDIGREE_TYPES);
            ps.setString(1, ReservationStatus.EXPD.toString());
            rs = ps.executeQuery();

            while (rs.next()) {
                pedigreeCount.put(CatPedigreeType.valueOf(rs.getString(1)), rs.getInt(2));
            }
            return pedigreeCount;

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);
        } catch (SQLException e) {
            throw new DAOException("Exception during counting pedigree", e);
        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }
}
