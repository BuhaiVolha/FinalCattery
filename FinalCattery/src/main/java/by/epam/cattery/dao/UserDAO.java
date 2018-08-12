package by.epam.cattery.dao;

import by.epam.cattery.dao.GenericDAO;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.User;


public interface UserDAO extends GenericDAO<User> {
    int getDiscount(int userId) throws DAOException;
    User getUserByLoginAndPassword(String login, String password) throws DAOException;
    String getColourPreferenceStatistics() throws DAOException;
    String getEmail(int userId) throws DAOException;

    void updateColourPreference(User user) throws DAOException;
    void updateDiscount(User user) throws DAOException;

    void reverseUserBannedFlag(int userId) throws DAOException;
    void reverseExpertRole(int userId) throws DAOException;
    void reverseUserReviewFlag(int userId) throws DAOException;

    boolean loginExists(String login) throws DAOException;
    boolean emailAlreadyExists(User user) throws DAOException;
    boolean userIsBanned(String login) throws DAOException;
    boolean reviewWasAdded(int userId) throws DAOException;
}
