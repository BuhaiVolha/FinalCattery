package by.epam.cattery.service;

import by.epam.cattery.entity.Offer;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;

public interface OfferService {
    boolean offerCat(Offer offer) throws ServiceException;
    List<Offer> takeAllOffersByUserId(String id) throws ServiceException;
    List<Offer> takeAllOffersByStatus(String status) throws ServiceException;
    void answerToOffer(Offer offer, String status, boolean forAdmin) throws ServiceException; // ?? Без була? тока проерка на Nul?
    Offer takeSingleOffer(String id) throws ServiceException;
    void discussPrice(Offer offer) throws ServiceException;
    void deleteOffer(int offerId) throws ServiceException;
}
