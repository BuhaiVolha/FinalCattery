package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.Reservation;

import java.util.List;
import java.util.Map;

public interface ReservationDAO {
    void addReservation(Reservation reservation) throws DAOException;
    List<Reservation> findAllReservations() throws DAOException;
    List<Reservation> findAllReservationsForUser(int userId) throws DAOException;
    void declineExpiredReservations() throws DAOException;
    void deleteReservation(int reservationId) throws DAOException;
    void cancelReservation(int reservationId) throws DAOException;
    void renewReservation(int reservationId) throws DAOException;
    void sellCat(int reservationId) throws DAOException;
    Map<CatPedigreeType, Integer> countPedigree() throws DAOException;
}
