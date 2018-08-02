package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.connection.ConnectionProvider;
import by.epam.cattery.dao.mysql.CatDAO;
import by.epam.cattery.dao.mysql.ReservationDAO;
import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.Reservation;
import by.epam.cattery.entity.ReservationStatus;
import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ReservationServiceImpl implements ReservationService {
    private static final Logger logger = LogManager.getLogger(ReservationServiceImpl.class);

    private static DAOFactory daoFactory = DAOFactory.getInstance();

    private static ReservationDAO reservationDAO = daoFactory.getReservationDAO();
    private static CatDAO catDAO = daoFactory.getCatDAO();

// и удалять все резервации где ИД = ИД этого кота. чтобы зря не висели
    @Override
    public void makeReservation(Reservation reservation) throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
            connectionProvider.startTransaction();
            //connectionProvider.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            reservationDAO.save(reservation);
            int catId = reservation.getCatId();

            if (catDAO.checkCatStatus(catId, CatStatus.AVAIL.toString())) {
                catDAO.updateStatusById(CatStatus.RSRV.toString(), catId);
            }

            connectionProvider.commitTransaction();

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Making reservation failed", e);

        } finally {
            connectionProvider.endTransaction();
        }
    }


    @Override
    public List<Reservation> takeAllReservations() throws ServiceException {

        try {
            return reservationDAO.loadAllByStatus(ReservationStatus.NEW.toString());

        } catch (DAOException e) {
            throw new ServiceException("Showing all (new) reservations failed", e);
        }
    }


    @Override
    public List<Reservation> takeAllReservationsForUser(int userId) throws ServiceException {

        try {
            return reservationDAO.loadAllById(userId);

        } catch (DAOException e) {
            throw new ServiceException("Showing all reservations for user failed", e);
        }
    }


    @Override
    public void declineExpiredReservations() throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
            connectionProvider.startTransaction();

            catDAO.setCatsAvailableIfReservationsExpired();
            reservationDAO.setAllReservationExpiredWhenTimePasses();

            connectionProvider.commitTransaction();

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Declining all expired reservations failed", e);

        }  finally {
            connectionProvider.endTransaction();
        }
    }


    @Override
    public void cancelReservation(int reservationId) throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
            connectionProvider.startTransaction();
            //connectionProvider.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            int catId = getCatIdByReservationId(reservationId);

            catDAO.updateStatusById(CatStatus.AVAIL.toString(), catId);
            reservationDAO.delete(reservationId);

            connectionProvider.commitTransaction();

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Cancelling reservation failed", e);
        } finally {
            connectionProvider.endTransaction();
        }
    }


    @Override
    public void deleteReservation(int reservationId) throws ServiceException {

        try {
            reservationDAO.delete(reservationId);

        } catch (DAOException e) {
            throw new ServiceException("Deleting reservation failed", e);
        }
    }


    @Override
    public void renewReservation(int reservationId) throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
            connectionProvider.startTransaction();
            connectionProvider.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);

            int catId = getCatIdByReservationId(reservationId);

            if (catDAO.checkCatStatus(catId, CatStatus.AVAIL.toString())) {
                catDAO.updateStatusById(CatStatus.RSRV.toString(), catId);
            }
            reservationDAO.updateStatusById(ReservationStatus.NEW.toString(), reservationId);

            connectionProvider.commitTransaction();

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Renewing reservation failed", e);

        }  finally {
            connectionProvider.endTransaction();
        }
    }


    @Override
    public void sellCat(int reservationId) throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
            connectionProvider.startTransaction();

            int catId = getCatIdByReservationId(reservationId);

            if (catDAO.checkCatStatus(catId, CatStatus.RSRV.toString())) {
                catDAO.updateStatusById(CatStatus.SOLD.toString(), catId);
            }
            reservationDAO.updateStatusById(ReservationStatus.DONE.toString(), reservationId);

            connectionProvider.commitTransaction();

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Selling cat failed", e);

        }  finally {
            connectionProvider.endTransaction();
        }
    }


    @Override
    public int getCatIdByReservationId(int reservationId) throws ServiceException {

        try {
            return catDAO.getCatIdByReservationId(reservationId);

        } catch (DAOException e) {
            throw new ServiceException("Finding cat ID by reservation ID failed", e);
        }
    }


    @Override
    public Map<CatPedigreeType, Integer> countPedigreeTypes() throws ServiceException {
        try {
            return reservationDAO.getPedigreeStatistics();

        } catch (DAOException e) {
            throw new ServiceException("Counting pedigrees failed", e);
        }
    }
}
