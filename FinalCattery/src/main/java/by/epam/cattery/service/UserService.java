package by.epam.cattery.service;

import by.epam.cattery.entity.User;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;

import java.util.List;
import java.util.Map;

public interface UserService {
    int register(User user) throws ServiceException, ValidationFailedException;
    User logIn(String login, String password) throws ServiceException;
    User showUser(int userId) throws ServiceException;
    // проверка на забанен ли
    List<User> showAllUser() throws ServiceException;
    String showStatistics() throws ServiceException;
    void changeColourPreference(User user) throws ServiceException;
    void banUser(String userId) throws ServiceException;
    void unbanUser(String userId) throws ServiceException;
    void makeDiscount(User user) throws ServiceException;
    void makeExpert(int userId) throws ServiceException;
    void unmakeExpert(int userId) throws ServiceException;
}
