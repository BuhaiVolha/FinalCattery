package by.epam.cattery.dao.mysql;

import by.epam.cattery.dao.GenericDAO;
import by.epam.cattery.dao.exception.DAOException;

import by.epam.cattery.entity.Cat;

import java.util.List;

public interface CatDAO extends GenericDAO<Cat> {
    List<Cat> searchForCat(Cat cat) throws DAOException;

    boolean checkCatStatus(int offerId, String statusToCheck) throws DAOException;

    void setCatsAvailableIfReservationsExpired() throws DAOException;

    int getCatIdByReservationId(int reservationId) throws DAOException;

    void addPhoto(int catId, String photo) throws DAOException;
}
