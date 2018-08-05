package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.Reservation;

import java.util.Map;

public interface ReservationDAO extends GenericDAO<Reservation> {
    Map<CatPedigreeType, Integer> getPedigreeStatistics() throws DAOException;

    void setAllReservationExpiredIfTimePassed() throws DAOException;
    void deleteAllExpiredReservationsWithReservedCat(int catId) throws DAOException;
}
