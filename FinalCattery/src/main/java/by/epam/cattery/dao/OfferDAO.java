package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Offer;

import java.util.List;

public interface OfferDAO {
    boolean makeOffer(Offer offer) throws DAOException;
    List<Offer> findAllOffersByUserId(String id) throws DAOException;
    List<Offer> findAllOffersByStatus(String status) throws DAOException;
    void declineOffer(String id, String expertMessage, String status) throws DAOException;
}
