package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.User;

public interface UserDAO {
    int addUser(User user) throws DAOException;
    boolean loginAlreadyExists(User user) throws DAOException;
    User findUser(String login, String password) throws DAOException;
}
