package by.epam.cattery.dao.mysql;

import by.epam.cattery.dao.GenericDAO;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Offer;

public interface OfferDAO extends GenericDAO<Offer> {
    boolean checkOfferStatus(int offerId, String statusToCheck) throws DAOException;
}
