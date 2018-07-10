package by.epam.cattery.dao;

import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.impl.CatDAOimpl;
import by.epam.cattery.dao.impl.UserDAOimpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private UserDAO userDAO;
    private CatDAO catDAO;
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private DAOFactory() {
        userDAO = new UserDAOimpl(connectionPool);
        catDAO = new CatDAOimpl(connectionPool);
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
}
