package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;

import by.epam.cattery.entity.Offer;


/**
 * Defines specific methods for Offer DAO.
 *
 */
public interface OfferDAO extends GenericDAO<Offer> {


    /**
     * Deletes awaiting offers (with status {@code AWAIT}) of banned user.
     *
     * @param userId the user's id
     * @throws DAOException the dao exception
     *
     */
    void deleteAwaitingOffersOfBannedUser(int userId) throws DAOException;


    /**
     * Check if the offer belongs to the user.
     *
     * @param userId  the user's id
     * @param offerId the offer's id
     * @return the boolean
     * @throws DAOException the dao exception
     *
     */
    boolean checkOfferBelongsToUser(int userId, int offerId) throws DAOException;
}
