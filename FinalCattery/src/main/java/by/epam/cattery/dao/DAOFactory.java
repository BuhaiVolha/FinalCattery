package by.epam.cattery.dao;

import by.epam.cattery.dao.impl.UserDAOimpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private static final UserDAO userDAO = new UserDAOimpl();

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
}
