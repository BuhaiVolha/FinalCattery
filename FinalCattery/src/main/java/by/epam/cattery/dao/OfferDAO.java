package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Offer;

public interface OfferDAO extends GenericDAO<Offer> {
    void addPhoto(int offerId, String photo) throws DAOException;
}
