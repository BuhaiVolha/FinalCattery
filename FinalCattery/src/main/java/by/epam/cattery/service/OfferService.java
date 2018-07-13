package by.epam.cattery.service;

import by.epam.cattery.entity.Offer;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;

public interface OfferService {
    boolean offerCat(Offer offer) throws ServiceException;
    List<Offer> showAllOffersByUserId(String id) throws ServiceException;
    List<Offer> showAllOffersByStatus(String status) throws ServiceException;
    void answerToOffer(Offer offer, String status, boolean forAdmin) throws ServiceException; // ?? Без була? тока проерка на Nul?
    Offer showSingleOffer(String id) throws ServiceException;
}
