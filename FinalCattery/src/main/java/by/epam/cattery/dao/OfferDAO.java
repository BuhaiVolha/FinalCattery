package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;

import java.util.List;

public interface OfferDAO {
    boolean makeOffer(Offer offer) throws DAOException;
    List<Offer> findAllOffersByUserId(String id) throws DAOException;
    List<Offer> findAllOffersByStatus(OfferStatus status) throws DAOException;
    void changeOfferStatus(Offer offer, OfferStatus status, boolean forAdmin) throws DAOException;
    Offer findSingleOffer(String id) throws DAOException;
    void changeOfferStatusAndPrice(Offer offer) throws DAOException;
    void deleteOffer(int offerId) throws DAOException;
}
