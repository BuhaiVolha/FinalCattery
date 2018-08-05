package by.epam.cattery.service;

import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;

public interface OfferService {
    int offerCat(Offer offer) throws ServiceException;
    List<Offer> takeAllOffersByUserId(int id) throws ServiceException;
    List<Offer> takeAllOffersByStatus(OfferStatus status) throws ServiceException;
    void answerToOffer(Offer offer, OfferStatus statusToCheck) throws ServiceException;
    Offer takeSingleOffer(int offerId) throws ServiceException;
    void deleteOffer(int offerId) throws ServiceException;
    void addPhotoToOffer(int offerId, String photo) throws ServiceException;
}
