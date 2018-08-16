package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.connection.ConnectionProvider;

import by.epam.cattery.dao.CatDAO;
import by.epam.cattery.dao.ReservationDAO;

import by.epam.cattery.entity.*;

import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.exception.ServiceException;

import by.epam.cattery.service.util.PageCounter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
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
    public List<Reservation> takeAllReservationsByStatus(ReservationStatus status, LocaleLang localeLang, int page, int itemsPerPage)  throws ServiceException {
        List<Reservation> reservations;

        try {
            reservations = reservationDAO.loadReservationsByStatus(status, localeLang, page, itemsPerPage);

            if (reservations.isEmpty()) {
                return Collections.emptyList();
            }

        } catch (DAOException e) {
            throw new ServiceException("Showing all reservations by status failed", e);
        }
        return reservations;
    }


    @Override
    public int getReservationsPageCountByStatus(ReservationStatus status, int itemsPerPage) throws ServiceException {
        int pageCount = 0;

        try {
            int totalCount = reservationDAO.getTotalCountByStatus(status.toString());
            pageCount = PageCounter.getInstance().countPages(totalCount, itemsPerPage);

        } catch (DAOException e) {
            throw new ServiceException("Exception while getting reservations counted by status", e);
        }
        return pageCount;
    }


    @Override
    public List<Reservation> takeAllReservationsForUser(int userId, LocaleLang localeLang, int page, int itemsPerPage) throws ServiceException {
        List<Reservation> reservations;

        try {
            reservations = reservationDAO.loadReservationsByUserId(userId, localeLang, page, itemsPerPage);

            if (reservations.isEmpty()) {
                return Collections.emptyList();
            }

        } catch (DAOException e) {
            throw new ServiceException("Showing all reservations by Id failed", e);
        }
        return reservations;
    }


    @Override
    public int getReservationsPageCountByUserId(int userId, int itemsPerPage) throws ServiceException {
        int pageCount = 0;

        try {
            int totalCount = reservationDAO.getTotalCountById(userId);
            pageCount = PageCounter.getInstance().countPages(totalCount, itemsPerPage);

        } catch (DAOException e) {
            throw new ServiceException("Exception while getting reservations counted by Id", e);
        }
        return pageCount;
    }


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
    public void addChequePhoto(int reservationId, String photo) throws ServiceException {
        try {
            reservationDAO.updatePhoto(reservationId, photo);

        } catch (DAOException e) {
            throw new ServiceException("Exception while adding photo for an reservation", e);
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
