package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.connection.ConnectionProvider;

import by.epam.cattery.dao.CatDAO;
import by.epam.cattery.dao.ReservationDAO;

import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.Reservation;
import by.epam.cattery.entity.ReservationStatus;

import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.exception.ServiceException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;


public class ReservationServiceImpl implements ReservationService {
    private static final Logger logger = LogManager.getLogger(ReservationServiceImpl.class);

    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static ReservationDAO reservationDAO = daoFactory.getReservationDAO();
    private static CatDAO catDAO = daoFactory.getCatDAO();


    @Override
    public void makeReservation(Reservation reservation) throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
            connectionProvider.startTransaction();

            int catId = reservation.getCatId();

            if (catDAO.checkStatus(catId, CatStatus.AVAIL.toString())) {
                reservationDAO.deleteAllExpiredReservationsWithReservedCat(catId);
                reservationDAO.save(reservation);
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


    // передавать как INT число дней? чтоб можно было менять
    @Override
    public void declineExpiredReservations() throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
            connectionProvider.startTransaction();

            catDAO.setCatsAvailableIfReservationsExpired();
            reservationDAO.setAllReservationExpiredIfTimePassed();

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
    public void deleteExpiredReservation(int reservationId) throws ServiceException {

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

            int catId = getCatIdByReservationId(reservationId);

            if (catDAO.checkStatus(catId, CatStatus.AVAIL.toString())) {
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

            if (catDAO.checkStatus(catId, CatStatus.RSRV.toString())) {
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
