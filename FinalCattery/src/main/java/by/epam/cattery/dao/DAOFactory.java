package by.epam.cattery.dao;

import by.epam.cattery.dao.impl.*;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private UserDAO userDAO;
    private OfferDAO offerDAO;
    private CatDAO catDAO;
    private ReviewDAO reviewDAO;
    private ReservationDAO reservationDAO;


    private DAOFactory() {
        userDAO = new UserDAOImpl();
        reviewDAO = new ReviewDAOImpl();
        offerDAO = new OfferDAOImpl();
        catDAO = new CatDAOImpl();
        reservationDAO = new ReservationDAOImpl();
    }


    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
    return userDAO;
}

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }

    public OfferDAO getOfferDAO() {
        return offerDAO;
    }

    public CatDAO getCatDAO() {
        return catDAO;
    }

    public ReservationDAO getReservationDAO() {
        return reservationDAO;
    }

}
