package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;

import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.LocaleLang;
import by.epam.cattery.entity.Reservation;
import by.epam.cattery.entity.ReservationStatus;

import java.util.List;
import java.util.Map;


/**
 * Defines specific methods for Reservation DAO.
 *
 */
public interface ReservationDAO extends GenericDAO<Reservation> {

    /**
     * Counts how much people chose certain pedigree type.
     *
     * @return the map with pedigree type as key and amount of people who chose it as value
     * @throws DAOException the dao exception
     *
     */
    Map<CatPedigreeType, Integer> getPedigreeStatistics() throws DAOException;


    /**
     * Sets all reservation {@code EXPD} if time to pay passed.
     *
     * @throws DAOException the dao exception
     *
     */
    void setAllReservationExpiredIfTimePassed() throws DAOException;


    /**
     * Deletes all expired reservations of this certain cat.
     *
     * @param catId the cat's id
     * @throws DAOException the dao exception
     *
     */
    void deleteAllExpiredReservationsWithReservedCat(int catId) throws DAOException;


    /**
     * Deletes new reservations (that is of {@code NEW} status) of banned user.
     *
     * @param userId the user's id
     * @throws DAOException the dao exception
     *
     */
    void deleteNewReservationsOfBannedUser(int userId) throws DAOException;


    /**
     * Loads all reservations of certain user taking into account pagination and locale.
     *
     * @param userId       the user's id
     * @param localeLang   {@link LocaleLang} locale
     * @param page         the current page
     * @param itemsPerPage the items per page
     * @return the list of {@link Reservation} objects
     * @throws DAOException the dao exception
     *
     */
    List<Reservation> loadReservationsByUserId(int userId, LocaleLang localeLang, int page, int itemsPerPage) throws DAOException;


    /**
     * Loada all reservations by status taking into account pagination and locale.
     *
     * @param status       {@link ReservationStatus} status
     * @param localeLang   {@link LocaleLang} locale
     * @param page         the current page
     * @param itemsPerPage the items per page
     * @return the list of {@link Reservation} objects
     * @throws DAOException the dao exception
     *
     */
    List<Reservation> loadReservationsByStatus(ReservationStatus status, LocaleLang localeLang, int page, int itemsPerPage) throws DAOException;
}
