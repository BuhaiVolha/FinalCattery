package by.epam.cattery.dao.mysql;

import by.epam.cattery.dao.GenericDAO;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.Reservation;

import java.util.Map;

public interface ReservationDAO extends GenericDAO<Reservation> {
    Map<CatPedigreeType, Integer> getPedigreeStatistics() throws DAOException;
    void setAllReservationExpiredWhenTimePasses() throws DAOException;
}
