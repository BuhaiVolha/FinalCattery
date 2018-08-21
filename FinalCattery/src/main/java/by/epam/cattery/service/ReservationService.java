package by.epam.cattery.service;

import by.epam.cattery.entity.*;

import by.epam.cattery.service.exception.ServiceException;

import java.util.List;
import java.util.Map;


/**
 * Defines methods to work with reservation data.
 *
 */
public interface ReservationService {


    /**
     * Saves the reservation.
     *
     * @param reservation {@link Reservation}
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void makeReservation(Reservation reservation) throws ServiceException;


    /**
     * Take all reservations by status taking into account pagination and locale.
     *
     * @param status       {@link ReservationStatus}
     * @param localeLang   the locale - {@link LocaleLang}
     * @param page         the current page
     * @param itemsPerPage the items per page
     * @return a list of {@link Reservation} objects
     * @throws ServiceException if exception in DAO occurred
     *
     */
    List<Reservation> takeAllReservationsByStatus(ReservationStatus status, LocaleLang localeLang, int page, int itemsPerPage) throws ServiceException;


    /**
     * Counts pages for reservations of certain status.
     *
     * @param status       {@link ReservationStatus}
     * @param itemsPerPage the items per page
     * @return the total page count
     * @throws ServiceException if exception in DAO occurred
     *
     */
    int getReservationsPageCountByStatus(ReservationStatus status, int itemsPerPage) throws ServiceException;


    /**
     * Takes all reservations for certain user taking into account pagination and locale.
     *
     * @param userId       the user's id
     * @param localeLang   the locale - {@link LocaleLang}
     * @param page         the current page
     * @param itemsPerPage the items per page
     * @return a list of {@link Reservation} objects
     * @throws ServiceException if exception in DAO occurred
     *
     */
    List<Reservation> takeAllReservationsForUser(int userId, LocaleLang localeLang, int page, int itemsPerPage) throws ServiceException;


    /**
     * Counts pages for reservations of certain user.
     *
     * @param userId       the user's id
     * @param itemsPerPage the items per page
     * @return the total page count
     * @throws ServiceException if exception in DAO occurred
     *
     */
    int getReservationsPageCountByUserId(int userId, int itemsPerPage) throws ServiceException;


    /**
     * Declines all expired reservations. If the time for paying has passed, reservation is made
     * {@code EXPIRED} and the cat is made {@code AVAILABLE}
     *
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void declineExpiredReservations() throws ServiceException;


    /**
     * Cancels reservation and making the cat {@code AVAILABLE} again.
     *
     * @param reservationId the reservation's id
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void cancelReservation(int reservationId) throws ServiceException;


    /**
     * Adds cheque's photo to certain reservation.
     *
     * @param reservationId the reservation's id
     * @param photo         the photo's name
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void addChequePhoto(int reservationId, String photo) throws ServiceException;


    /**
     * Deletes expired reservation.
     *
     * @param reservationId the reservation's id
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void deleteExpiredReservation(int reservationId) throws ServiceException;


    /**
     * Renews reservation after it has been made {@code EXPIRED}, making it {@code NEW} again.
     *
     * @param reservationId the reservation's id
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void renewReservation(int reservationId) throws ServiceException;


    /**
     * Makes cat {@code SOLD} and completes the reservations, making it's status {@code DONE}.
     *
     * @param reservationId the reservation id
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void sellCat(int reservationId) throws ServiceException;


    /**
     * Gets cat's id by the reservation's id.
     *
     * @param reservationId the reservation's id
     * @return this reservations's cat's id
     * @throws ServiceException if exception in DAO occurred
     *
     */
    int getCatIdByReservationId(int reservationId) throws ServiceException;


    /**
     * Counts how much people chose certain pedigree type (to prepare materials maybe?)
     *
     * @return the map with pedigree type as key and amount of people who chose it as value
     * @throws ServiceException if exception in DAO occurred
     *
     */
    Map<CatPedigreeType, Integer> countPedigreeTypes() throws ServiceException;
}
