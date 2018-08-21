package by.epam.cattery.dao;

import by.epam.cattery.dao.impl.*;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private static final UserDAO userDAO = new UserDAOImpl();;
    private static final OfferDAO offerDAO = new OfferDAOImpl();
    private static final CatDAO catDAO = new CatDAOImpl();
    private static final ReviewDAO reviewDAO = new ReviewDAOImpl();
    private static final ReservationDAO reservationDAO = new ReservationDAOImpl();


    private DAOFactory() {
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
