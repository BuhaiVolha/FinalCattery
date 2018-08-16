package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;

import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.LocaleLang;
import by.epam.cattery.entity.Reservation;
import by.epam.cattery.entity.ReservationStatus;

import java.util.List;
import java.util.Map;


public interface ReservationDAO extends GenericDAO<Reservation> {
    Map<CatPedigreeType, Integer> getPedigreeStatistics() throws DAOException;
    void setAllReservationExpiredIfTimePassed() throws DAOException;
    void deleteAllExpiredReservationsWithReservedCat(int catId) throws DAOException;
    void deleteNewReservationsOfBannedUser(int userId) throws DAOException;
    List<Reservation> loadReservationsByUserId(int userId, LocaleLang localeLang, int page, int itemsPerPage) throws DAOException;
    List<Reservation> loadReservationsByStatus(ReservationStatus status, LocaleLang localeLang, int page, int itemsPerPage) throws DAOException;
}
