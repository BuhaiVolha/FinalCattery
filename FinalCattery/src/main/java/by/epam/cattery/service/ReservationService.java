package by.epam.cattery.service;

import by.epam.cattery.entity.*;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface ReservationService {
    void makeReservation(Reservation reservation) throws ServiceException;
    List<Reservation> takeAllReservationsByStatus(ReservationStatus status, LocaleLang localeLang, int page, int itemsPerPage) throws ServiceException;
    int getReservationsPageCountByStatus(ReservationStatus status, int itemsPerPage) throws ServiceException;
    List<Reservation> takeAllReservationsForUser(int userId, LocaleLang localeLang, int page, int itemsPerPage) throws ServiceException;
    int getReservationsPageCountByUserId(int userId, int itemsPerPage) throws ServiceException;
    void declineExpiredReservations() throws ServiceException;
    void cancelReservation(int reservationId) throws ServiceException;
    void addChequePhoto(int reservationId, String photo) throws ServiceException;
    void deleteExpiredReservation(int reservationId) throws ServiceException;
    void renewReservation(int reservationId) throws ServiceException;
    void sellCat(int reservationId) throws ServiceException;
    int getCatIdByReservationId(int reservationId) throws ServiceException;
    Map<CatPedigreeType, Integer> countPedigreeTypes() throws ServiceException;
}
