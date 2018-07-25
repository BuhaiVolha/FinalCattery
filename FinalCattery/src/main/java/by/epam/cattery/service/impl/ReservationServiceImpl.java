package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.ReservationDAO;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.Reservation;
import by.epam.cattery.service.ReservationService;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public class ReservationServiceImpl implements ReservationService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static ReservationDAO reservationDAO = daoFactory.getReservationDAO();

    @Override
    public void makeReservation(Reservation reservation) throws ServiceException {
        try {
            reservationDAO.addReservation(reservation);

        } catch (DAOException e) {
            throw new ServiceException("Making reservation failed in Service", e);
        }
    }


    @Override
    public List<Reservation> takeAllReservations() throws ServiceException {
        try {
            return reservationDAO.findAllReservations();

        } catch (DAOException e) {
            throw new ServiceException("Showing all reservations failed in Service", e);
        }
    }


    @Override
    public List<Reservation> takeAllReservationsForUser(int userId) throws ServiceException {
        try {
            return reservationDAO.findAllReservationsForUser(userId);

        } catch (DAOException e) {
            throw new ServiceException("Showing all reservations for user failed in Service", e);
        }
    }


    @Override
    public void declineExpiredReservations() throws ServiceException {
        try {
            reservationDAO.declineExpiredReservations();

        } catch (DAOException e) {
            throw new ServiceException("Declining all expired reservations failed in Service", e);
        }
    }


    @Override
    public void cancelReservation(int reservationId) throws ServiceException {
        try {
            reservationDAO.cancelReservation(reservationId);

        } catch (DAOException e) {
            throw new ServiceException("Cancelling reservation failed in Service", e);
        }
    }


    @Override
    public void deleteReservation(int reservationId) throws ServiceException {
        try {
            reservationDAO.deleteReservation(reservationId);

        } catch (DAOException e) {
            throw new ServiceException("Deleting reservation failed in Service", e);
        }
    }


    @Override
    public void renewReservation(int reservationId) throws ServiceException {
        try {
            reservationDAO.renewReservation(reservationId);

        } catch (DAOException e) {
            throw new ServiceException("Renewing reservation failed in Service", e);
        }
    }


    @Override
    public void sellCat(int reservationId) throws ServiceException {
        try {
            reservationDAO.sellCat(reservationId);

        } catch (DAOException e) {
            throw new ServiceException("Selling cat failed in Service", e);
        }
    }


    @Override
    public Map<CatPedigreeType, Integer> countPedigree() throws ServiceException {
        try {
            return reservationDAO.countPedigree();

        } catch (DAOException e) {
            throw new ServiceException("Counting pedigrees failed in Service", e);
        }
    }
}
