package by.epam.cattery.service;

import by.epam.cattery.entity.User;

import by.epam.cattery.service.exception.ServiceException;

import java.util.List;


/**
 * Defines methods to work with user data.
 *
 */
public interface UserService {

    /**
     * Registers new user and returns his id.
     *
     * If login already exists, {@link by.epam.cattery.service.exception.LoginAlreadyExistsException} is thrown
     *
     * If email is already taken, {@link by.epam.cattery.service.exception.EmailAlreadyExistsException} is thrown
     *
     * @param user {@link User}
     * @return user's id from the database
     * @throws ServiceException if exception in DAO occurred
     *
     */
    int register(User user) throws ServiceException;


    /**
     * Gets discount for certain user.
     *
     * @param userId the user's id
     * @return the user's discount in percents
     * @throws ServiceException if exception in DAO occurred
     *
     */
    int getDiscount(int userId) throws ServiceException;


    /**
     * Logs in the user with his login and password.
     *
     * If the user is banned, {@link by.epam.cattery.service.exception.UserIsBannedException} is thrown
     *
     * If the password is wrong or no user with such login exists,
     * {@link by.epam.cattery.service.exception.NoSuchUserException} is thrown
     *
     * @param login    user's login
     * @param password user's password
     * @return {@link User}
     * @throws ServiceException if exception in DAO occurred
     *
     */
    User logIn(String login, String password) throws ServiceException;


    /**
     * Takes single user by id.
     *
     * @param userId the user's id
     * @return {@link User}
     * @throws ServiceException if exception in DAO occurred
     *
     */
    User takeSingleUser(int userId) throws ServiceException;


    /**
     * Takes all users from the database taking into account pagination.
     *
     * @param page         the current page
     * @param itemsPerPage the items per page
     * @return the list of {@link User} objects
     * @throws ServiceException if exception in DAO occurred
     *
     */
    List<User> takeAllUsers(int page, int itemsPerPage) throws ServiceException;


    /**
     * Counts pages for users.
     *
     * @param itemsPerPage the items per page
     * @return the total page count
     * @throws ServiceException if exception in DAO occurred
     *
     */
    int getUsersPageCount(int itemsPerPage) throws ServiceException;


    /**
     * Counts statistics of user's colour preferences.
     *
     * @return the string that will be represented as pie chart in jsp
     * @throws ServiceException if exception in DAO occurred
     *
     */
    String countStatistics() throws ServiceException;


    /**
     * Changes user's colour preference.
     *
     * @param user {@link User}
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void changeColourPreference(User user) throws ServiceException;


    /**
     * Bans the user.
     *
     * @param userId the user's id
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void banUser(int userId) throws ServiceException;


    /**
     * Unbans the user.
     *
     * @param userId the user's id
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void unbanUser(int userId) throws ServiceException;


    /**
     * Makes discount for certain user.
     *
     * @param user {@link User}
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void makeDiscount(User user) throws ServiceException;


    /**
     * Makes user an expert.
     *
     * @param userId the user's id
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void makeExpert(int userId) throws ServiceException;


    /**
     * Makes expert an user.
     *
     * @param userId the user's id
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void unmakeExpert(int userId) throws ServiceException;


    /**
     * Edits user's personal info.
     *
     * If new email is already taken, {@link by.epam.cattery.service.exception.EmailAlreadyExistsException} is thrown
     *
     * @param user {@link User}
     * @throws ServiceException if exception in DAO occurred or the data input was invalid
     *
     */
    void editPersonalInfo(User user) throws ServiceException;
}
