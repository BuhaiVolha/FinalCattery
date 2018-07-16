package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.UserDAO;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.User;
import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.UserAlreadyExistsException;
import by.epam.cattery.service.exception.ValidationFailedException;
import by.epam.cattery.service.validation.Validator;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static UserDAO userDAO = daoFactory.getUserDAO();

    @Override
    public int register(User user) throws ServiceException, ValidationFailedException {

        if (!Validator.validateUserData(user)) {
            throw new ValidationFailedException("user data invalid!");
        }
        try {
            if (userDAO.loginAlreadyExists(user)) {
                throw new UserAlreadyExistsException("User already exists");
            }

            String securePass = BCrypt.hashpw(user.getUserPass(), BCrypt.gensalt());
            user.setUserPass(securePass);

            return userDAO.addUser(user);

        } catch (DAOException e) {
            throw new ServiceException("Registration failed", e);
        }
    }


    @Override
    public User logIn(String login, String password) throws ServiceException {
//        if (!Validator.validateUserData(user)) {
//            System.err.println("user data invalid!");
//        }

        try {
            return userDAO.findUser(login, password); // передвавать юхера просто?

        } catch (DAOException e) {
            throw new ServiceException("Exception while logging in", e);
        }
    }


    @Override
    public User showUser(int userId) throws ServiceException {
        try {
            return userDAO.findUserInfo(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while finding user info", e);
        }
    }


    @Override
    public List<User> showAllUser() throws ServiceException {
        List<User> users;

        try {
            users = userDAO.findAllUsers();

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
    public String showStatistics() throws ServiceException {

        try {
            return userDAO.countStatistics();

        } catch (DAOException e) {
            throw new ServiceException("Exception while showing statistics", e);
        }
    }


    @Override
    public void banUser(String userId) throws ServiceException {

        try {
            userDAO.reverseUserBannedFlag(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while banning user", e);
        }
    }


    @Override
    public void unbanUser(String userId) throws ServiceException {

        try {
            userDAO.reverseUserBannedFlag(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while unbanning user", e);
        }
    }


    @Override
    public void makeDiscount(User user) throws ServiceException {

        try {
            userDAO.setDiscount(user);

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
