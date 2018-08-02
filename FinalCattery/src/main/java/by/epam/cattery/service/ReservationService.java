package by.epam.cattery.service;

import by.epam.cattery.entity.CatPedigreeType;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.Reservation;
import by.epam.cattery.entity.ReservationStatus;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;
import java.util.Map;

public interface ReservationService {
    void makeReservation(Reservation reservation) throws ServiceException; // delete from all reserv where catId =
    List<Reservation> takeAllReservations() throws ServiceException;
    List<Reservation> takeAllReservationsForUser(int userId) throws ServiceException;
    void declineExpiredReservations() throws ServiceException;
    void cancelReservation(int reservationId) throws ServiceException;
    void deleteExpiredReservation(int reservationId) throws ServiceException;
    void renewReservation(int reservationId) throws ServiceException;
    void sellCat(int reservationId) throws ServiceException;
    int getCatIdByReservationId(int reservationId) throws ServiceException;
    Map<CatPedigreeType, Integer> countPedigreeTypes() throws ServiceException;
}
