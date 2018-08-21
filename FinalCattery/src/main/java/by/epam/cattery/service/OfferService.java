package by.epam.cattery.service;

import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;

import by.epam.cattery.service.exception.ServiceException;

import java.util.List;


/**
 * Defines methods to work with offer data.
 *
 */
public interface OfferService {

    /**
     * Saves offer and returns its id.
     *
     * @param offer the {@link Offer}
     * @return the offer's id
     * @throws ServiceException if exception in DAO occurred
     *
     */
    int offerCat(Offer offer) throws ServiceException;


    /**
     * Takes all offers by status list taking into account pagination.
     *
     * @param status       {@link OfferStatus} of which the offers will be taken
     * @param page         the current page
     * @param itemsPerPage the items per page
     * @return list of {@link Offer} objects
     * @throws ServiceException if exception in DAO occurred
     *
     */
    List<Offer> takeAllOffersByStatus(OfferStatus status, int page, int itemsPerPage) throws ServiceException;


    /**
     * Counts pages for offers of certain status.
     *
     * @param status       {@link OfferStatus} of which the offers will be taken
     * @param itemsPerPage the items per page
     * @return the total page count
     * @throws ServiceException if exception in DAO occurred
     *
     */
    int getOffersPageCountByStatus(OfferStatus status, int itemsPerPage) throws ServiceException;


    /**
     * Takes all offers for certain user taking into account pagination.
     *
     * @param userId       the user's id
     * @param page         the current page
     * @param itemsPerPage the items per page
     * @return list of {@link Offer} objects
     * @throws ServiceException if exception in DAO occurred
     *
     */
    List<Offer> takeAllOffersForUser(int userId, int page, int itemsPerPage) throws ServiceException;


    /**
     * Counts pages for offers of certain user.
     *
     * @param userId       the user's id
     * @param itemsPerPage the items per page
     * @return the total page count
     * @throws ServiceException if exception in DAO occurred
     *
     */
    int getOffersPageCountByUserId(int userId, int itemsPerPage) throws ServiceException;


    /**
     * Answers to offer by changing it's status.
     *
     * @param offer         {@link Offer}
     * @param statusToCheck {@link OfferStatus} to check if the current status matches the required one
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void answerToOffer(Offer offer, OfferStatus statusToCheck) throws ServiceException;


    /**
     * Takes single offer by {@code offerId}.
     *
     * @param offerId the offer's id
     * @return {@link Offer}
     * @throws ServiceException if exception in DAO occurred
     *
     */
    Offer takeSingleOffer(int offerId) throws ServiceException;


    /**
     * Deletes the offer.
     *
     * @param offerId the offer's id
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void deleteOffer(int offerId) throws ServiceException;


    /**
     * Adds photo to the offer.
     *
     * @param offerId the offer's id
     * @param photo   the photo's name
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void addPhotoToOffer(int offerId, String photo) throws ServiceException;


    /**
     * Checks if the offer belongs to the user.
     *
     * @param userId  the user's id
     * @param offerId the offer's id
     * @return the boolean
     * @throws ServiceException if exception in DAO occurred
     *
     */
    boolean offerBelongsToUser(int userId, int offerId) throws ServiceException;
}
