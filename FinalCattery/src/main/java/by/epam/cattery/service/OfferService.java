package by.epam.cattery.service;

import by.epam.cattery.entity.Offer;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;

public interface OfferService {
    boolean offerKitten(Offer offer) throws ServiceException;
    List<Offer> showAllOffersByUserId(String id) throws ServiceException;
    List<Offer> showAllOffersByStatus(String status) throws ServiceException;
    void declineOffer(String id, String expertMessage, String status) throws ServiceException;
}
