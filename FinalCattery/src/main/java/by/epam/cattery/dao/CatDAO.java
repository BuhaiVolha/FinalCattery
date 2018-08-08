package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.dto.SearchCatTO;

import java.util.List;


public interface CatDAO extends GenericDAO<Cat> {
    void searchForCat(SearchCatTO searchCatTO, int page) throws DAOException;
    void getTotalCountForFoundCats(SearchCatTO searchCatTO) throws DAOException;
    void setCatsAvailableIfReservationsExpired() throws DAOException;
    int getCatIdByReservationId(int reservationId) throws DAOException;
}
