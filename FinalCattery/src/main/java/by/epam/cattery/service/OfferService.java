package by.epam.cattery.service;

import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;

public interface OfferService {
    int offerCat(Offer offer) throws ServiceException;
    List<Offer> takeAllOffersByStatus(OfferStatus status, int page, int itemsPerPage) throws ServiceException;
    int getOffersPageCountByStatus(OfferStatus status, int itemsPerPage) throws ServiceException;
    List<Offer> takeAllOffersForUser(int userId, int page, int itemsPerPage) throws ServiceException;
    int getOffersPageCountByUserId(int userId, int itemsPerPage) throws ServiceException;
    void answerToOffer(Offer offer, OfferStatus statusToCheck) throws ServiceException;
    Offer takeSingleOffer(int offerId) throws ServiceException;
    void deleteOffer(int offerId) throws ServiceException;
    void addPhotoToOffer(int offerId, String photo) throws ServiceException;
    boolean offerBelongsToUser(int userId, int offerId) throws ServiceException;
}
