package by.epam.cattery.service;

import by.epam.cattery.entity.User;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;

import java.util.List;

public interface UserService {
    int register(User user) throws ServiceException;
    int getDiscount(int userId) throws ServiceException;
    User logIn(String login, String password) throws ServiceException;
    User takeSingleUser(int userId) throws ServiceException;
    List<User> takeAllUsers() throws ServiceException;
    String countStatistics() throws ServiceException;
    void changeColourPreference(User user) throws ServiceException;
    void banUser(int userId) throws ServiceException;
    void unbanUser(int userId) throws ServiceException;
    void makeDiscount(User user) throws ServiceException;
    void makeExpert(int userId) throws ServiceException;
    void unmakeExpert(int userId) throws ServiceException;
    void editPersonalInfo(User user) throws ServiceException;
}
