package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.mysql.UserDAO;

import by.epam.cattery.entity.User;

import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.*;
import by.epam.cattery.service.validation.Validator;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Collections;
import java.util.List;

// ВАЛИДАЦИЯ !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

public class UserServiceImpl implements UserService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();

    private static UserDAO userDAO = daoFactory.getUserDAO();


    @Override
    public int getDiscount(int userId) throws ServiceException {

        try {
            return userDAO.getDiscount(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while finding discount for user", e);
        }
    }


    @Override
    public int register(User user) throws ServiceException, ValidationFailedException {

        if (!Validator.validateUserData(user)) {
            throw new ValidationFailedException("user data invalid!");
        }

        try {
            if (userDAO.loginAlreadyExists(user)) {
                throw new LoginAlreadyExistsException("Login already exists");
            }
            if (userDAO.emailAlreadyExists(user)) {
                throw new EmailAlreadyExistsException("Email already exists");
            }

            String securePass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());  // Отдельный мтеод?
            user.setPassword(securePass);

            return userDAO.saveAndReturnId(user);

        } catch (DAOException e) {
            throw new ServiceException("Registration failed", e);
        }
    }


    @Override
    public User logIn(String login, String password) throws ServiceException {

        try {
            if (userDAO.userIsBanned(login)) {
                throw new UserIsBannedException("User is banned");
            }

            return userDAO.getUserByLoginAndPassword(login, password); // передвавать DTO userDetails ченить такое?

        } catch (DAOException e) {
            throw new ServiceException("Exception while logging in", e);
        }
    }


    @Override
    public void editPersonalInfo(User user) throws ServiceException {

//        if (!Validator.validateUserData(user)) {
//            throw new ValidationFailedException("user data invalid!"); // отдельная валидация на пароль и логин
//        }

        try {

            if (userDAO.emailAlreadyExists(user)) {
                throw new EmailAlreadyExistsException("Email already exists");
            }

            String securePass = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            user.setPassword(securePass);

            userDAO.update(user);

        } catch (DAOException e) {
            throw new ServiceException("Registration failed", e);
        }
    }


    @Override
    public User takeSingleUser(int userId) throws ServiceException {

        try {
            return userDAO.getById(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while finding user info", e);
        }
    }


    @Override
    public List<User> takeAllUsers() throws ServiceException {
        List<User> users;

        try {
            users = userDAO.loadAll();

            if (users.isEmpty()) {
                return Collections.emptyList();
            }
            return users;

        } catch (DAOException e) {
            throw new ServiceException("Exception while showing all users", e);
        }
    }


    @Override
    public void changeColourPreference(User user) throws ServiceException {

        try {
            userDAO.updateColourPreference(user);

        } catch (DAOException e) {
            throw new ServiceException("Exception while showing all users", e);
        }
    }


    @Override
    public String countStatistics() throws ServiceException {

        try {
            return userDAO.getColourPreferenceStatistics();

        } catch (DAOException e) {
            throw new ServiceException("Exception while showing statistics", e);
        }
    }


    @Override
    public void banUser(int userId) throws ServiceException {

        try {
            userDAO.reverseUserBannedFlag(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while banning user", e);
        }
    }


    @Override
    public void unbanUser(int userId) throws ServiceException {

        try {
            userDAO.reverseUserBannedFlag(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while unbanning user", e);
        }
    }


    @Override
    public void makeDiscount(User user) throws ServiceException {

        try {
            userDAO.updateDiscount(user);

        } catch (DAOException e) {
            throw new ServiceException("Exception while making discount", e);
        }
    }


    @Override
    public void makeExpert(int userId) throws ServiceException {

        try {
            userDAO.reverseExpertRole(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while making user an expert", e);
        }
    }


    @Override
    public void unmakeExpert(int userId) throws ServiceException {

        try {
            userDAO.reverseExpertRole(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while unmaking user an expert", e);
        }
    }
}
