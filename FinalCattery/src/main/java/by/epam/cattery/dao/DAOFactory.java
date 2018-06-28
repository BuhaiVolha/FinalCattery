package by.epam.cattery.dao;

import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.impl.UserDAOimpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private UserDAO userDAO;
    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    private DAOFactory() {
        userDAO = new UserDAOimpl(connectionPool);
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
