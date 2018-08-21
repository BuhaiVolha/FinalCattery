package by.epam.cattery.service.impl;

import by.epam.cattery.dao.*;
import by.epam.cattery.dao.connection.ConnectionProvider;
import by.epam.cattery.dao.exception.DAOException;

import by.epam.cattery.entity.User;

import by.epam.cattery.service.UserService;
import by.epam.cattery.service.exception.*;
import by.epam.cattery.service.util.PageCounter;
import by.epam.cattery.service.util.PasswordEncrypter;
import by.epam.cattery.service.validation.Validator;

import java.util.Collections;
import java.util.List;


/**
 * {@inheritDoc}
 *
 */
public class UserServiceImpl implements UserService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();

    private static UserDAO userDAO = daoFactory.getUserDAO();
    private static CatDAO catDAO = daoFactory.getCatDAO();
    private static ReservationDAO reservationDAO = daoFactory.getReservationDAO();
    private static OfferDAO offerDAO = daoFactory.getOfferDAO();


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int getDiscount(int userId) throws ServiceException {

        try {
            return userDAO.getDiscount(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while finding discount for user", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     * The implementation checks if input data are valid otherwise {@link ValidationFailedException} will be thrown
     *
     * If login already exists, {@link by.epam.cattery.service.exception.LoginAlreadyExistsException} is thrown
     *
     * If email is already taken, {@link by.epam.cattery.service.exception.EmailAlreadyExistsException} is thrown
     *
     */
    @Override
    public int register(User user) throws ServiceException {

        if (!Validator.getInstance().validateRegistrationInputData(user)) {
            throw new ValidationFailedException("User's data are invalid!");
        }

        try {
            if (userDAO.loginExists(user.getLogin())) {
                throw new LoginAlreadyExistsException("Login already exists");
            }
            if (userDAO.emailAlreadyExists(user)) {
                throw new EmailAlreadyExistsException("Email already exists");
            }

            String securePass = PasswordEncrypter.getInstance().encryptPassword(user.getPassword());
            user.setPassword(securePass);

            return userDAO.saveAndReturnId(user);

        } catch (DAOException e) {
            throw new ServiceException("Registration failed", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     * The implementation checks if input data are valid otherwise {@link ValidationFailedException} will be thrown
     *
     * If the user is banned, {@link by.epam.cattery.service.exception.UserIsBannedException} is thrown
     *
     * If the password is wrong or no user with such login exists,
     * {@link by.epam.cattery.service.exception.NoSuchUserException} is thrown
     *
     */
    @Override
    public User logIn(String login, String password) throws ServiceException {

        if (!Validator.getInstance().validateLoginInputData(login, password)) {
            throw new ValidationFailedException("User's data are invalid!");
        }
        try {
            if (!userDAO.loginExists(login)) {
                throw new NoSuchUserException("No such user exists");
            }
            if (userDAO.userIsBanned(login)) {
                throw new UserIsBannedException("User is banned");
            }

            return userDAO.getUserByLoginAndPassword(login, password);

        } catch (DAOException e) {
            throw new ServiceException("Exception while logging in", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     * The implementation checks if input data are valid otherwise {@link ValidationFailedException} will be thrown
     *
     * If new email is already taken, {@link by.epam.cattery.service.exception.EmailAlreadyExistsException} is thrown
     *
     */
    @Override
    public void editPersonalInfo(User user) throws ServiceException {

        if (!Validator.getInstance().validateRegistrationInputData(user)) {
            throw new ValidationFailedException("User's data are invalid!");
        }

        try {
            if ((userDAO.emailAlreadyExists(user))
                    && (!user.getEmail().equals(userDAO.getEmail(user.getId())))) {
                throw new EmailAlreadyExistsException("Email already exists");
            }

            String securePass = PasswordEncrypter.getInstance().encryptPassword(user.getPassword());
            user.setPassword(securePass);

            userDAO.update(user);

        } catch (DAOException e) {
            throw new ServiceException("Registration failed", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public User takeSingleUser(int userId) throws ServiceException {

        try {
            return userDAO.getById(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while finding user info", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     * The list will be empty if no cats has been found
     *
     */
    @Override
    public List<User> takeAllUsers(int page, int itemsPerPage) throws ServiceException {
        List<User> users;

        try {
            users = userDAO.loadAll(page, itemsPerPage);

            if (users.isEmpty()) {
                return Collections.emptyList();
            }

        } catch (DAOException e) {
            throw new ServiceException("Exception while finding all users", e);
        }
        return users;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int getUsersPageCount(int itemsPerPage) throws ServiceException {
        int pageCount = 0;

        try {
            int totalCount = userDAO.getTotalCount();
            pageCount = PageCounter.getInstance().countPages(totalCount, itemsPerPage);

        } catch (DAOException e) {
            throw new ServiceException("Exception while getting users counted", e);
        }
        return pageCount;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void changeColourPreference(User user) throws ServiceException {

        try {
            userDAO.updateColourPreference(user);

        } catch (DAOException e) {
            throw new ServiceException("Exception while showing all users", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public String countStatistics() throws ServiceException {

        try {
            return userDAO.getColourPreferenceStatistics();

        } catch (DAOException e) {
            throw new ServiceException("Exception while showing statistics", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     * The implementation also deletes this user's reservations and offers
     *
     */
    @Override
    public void banUser(int userId) throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
            connectionProvider.startTransaction();

            userDAO.reverseUserBannedFlag(userId);
            catDAO.setCatsAvailableIfUserBanned(userId);
            reservationDAO.deleteNewReservationsOfBannedUser(userId);
            offerDAO.deleteAwaitingOffersOfBannedUser(userId);

            connectionProvider.commitTransaction();

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Exception while banning user and deleting his reservation and offers", e);

        } finally {
            connectionProvider.endTransaction();
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void unbanUser(int userId) throws ServiceException {

        try {
            userDAO.reverseUserBannedFlag(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while unbanning user", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void makeDiscount(User user) throws ServiceException {

        try {
            userDAO.updateDiscount(user);

        } catch (DAOException e) {
            throw new ServiceException("Exception while making discount", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void makeExpert(int userId) throws ServiceException {

        try {
            userDAO.reverseExpertRole(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while making user an expert", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void unmakeExpert(int userId) throws ServiceException {

        try {
            userDAO.reverseExpertRole(userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while unmaking user an expert", e);
        }
    }
}
