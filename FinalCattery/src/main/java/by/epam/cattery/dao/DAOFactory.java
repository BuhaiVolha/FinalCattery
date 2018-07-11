package by.epam.cattery.dao;

import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.impl.CatDAOimpl;
import by.epam.cattery.dao.impl.OfferDAOimpl;
import by.epam.cattery.dao.impl.ReviewDAOimpl;
import by.epam.cattery.dao.impl.UserDAOimpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private UserDAO userDAO;   // priv static final??
    private CatDAO catDAO;
    private ReviewDAO reviewDAO;
    private OfferDAO offerDAO;

    private DAOFactory() {
        userDAO = new UserDAOimpl(connectionPool);
        catDAO = new CatDAOimpl(connectionPool);
        reviewDAO = new ReviewDAOimpl(connectionPool);
        offerDAO = new OfferDAOimpl(connectionPool);
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
}
