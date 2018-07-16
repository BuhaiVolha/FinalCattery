package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    int addUser(User user) throws DAOException;
    boolean loginAlreadyExists(User user) throws DAOException;
    User findUser(String login, String password) throws DAOException;
    User findUserInfo(int userId) throws DAOException;
    List<User> findAllUsers() throws DAOException;
    String countStatistics() throws DAOException;
    void updateColourPreference(User user) throws DAOException;
    void reverseUserBannedFlag(String userId) throws DAOException;
    void setDiscount(User user) throws DAOException;
    void reverseExpertRole(int userId) throws DAOException;
}
