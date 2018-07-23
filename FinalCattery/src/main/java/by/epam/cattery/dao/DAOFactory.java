package by.epam.cattery.dao;

import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.impl.*;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private UserDAO userDAO;   // priv static final??
    private CatDAO catDAO;
    private ReviewDAO reviewDAO;
    private OfferDAO offerDAO;
    private ReservationDAO reservationDAO;

    private DAOFactory() {
        userDAO = new UserDAOImpl(connectionPool);
        catDAO = new CatDAOImpl(connectionPool);
        reviewDAO = new ReviewDAOImpl(connectionPool);
        offerDAO = new OfferDAOImpl(connectionPool);
        reservationDAO = new ReservationDAOImpl(connectionPool);
    }


    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public CatDAO getCatDAO() {
        return catDAO;
    }

    public ReviewDAO getReviewDAO() {
        return reviewDAO;
    }

    public OfferDAO getOfferDAO() {
        return offerDAO;
    }

    public ReservationDAO getReservationDAO() {
        return reservationDAO;
    }
}
