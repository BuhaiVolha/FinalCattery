package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;

import by.epam.cattery.entity.User;


/**
 * Defines specific methods for User DAO.
 *
 */
public interface UserDAO extends GenericDAO<User> {

    /**
     * Fetches user's discount.
     *
     * @param userId the user's id
     * @return the discount
     * @throws DAOException the dao exception
     *
     */
    int getDiscount(int userId) throws DAOException;


    /**
     * Gets user by login and password. Returns {@code null} if no such user is found
     *
     * @param login    the login
     * @param password the password
     * @return {@link User}
     * @throws DAOException the dao exception
     *
     */
    User getUserByLoginAndPassword(String login, String password) throws DAOException;


    /**
     * Gets colour preference statistics.
     *
     * @return the colour preference statistics as string to be later represented as pie chart
     * @throws DAOException the dao exception
     *
     */
    String getColourPreferenceStatistics() throws DAOException;


    /**
     * Gets user's email.
     *
     * @param userId the user's id
     * @return the email
     * @throws DAOException the dao exception
     *
     */
    String getEmail(int userId) throws DAOException;


    /**
     * Updates colour preference for user.
     *
     * @param user {@link User}
     * @throws DAOException the dao exception
     *
     */
    void updateColourPreference(User user) throws DAOException;


    /**
     * Updates discount for user.
     *
     * @param user {@link User}
     * @throws DAOException the dao exception
     *
     */
    void updateDiscount(User user) throws DAOException;


    /**
     * Reverses user's banned flag.
     *
     * @param userId the user's id
     * @throws DAOException the dao exception
     *
     */
    void reverseUserBannedFlag(int userId) throws DAOException;


    /**
     * Reverses expert role, making expert an user and vice versa.
     *
     * @param userId the user's id
     * @throws DAOException the dao exception
     *
     */
    void reverseExpertRole(int userId) throws DAOException;


    /**
     * Reverses user's reviewLeft flag.
     *
     * If update wasn't made {@link DAOException} will be thrown
     *
     * @param userId the user's id
     * @throws DAOException the dao exception
     *
     */
    void reverseUserReviewFlag(int userId) throws DAOException;


    /**
     * Checks whether login exists.
     *
     * @param login the login
     * @return the boolean
     * @throws DAOException the dao exception
     *
     */
    boolean loginExists(String login) throws DAOException;


    /**
     * Checks whether email already exists.
     *
     * @param user {@link User}
     * @return the boolean
     * @throws DAOException the dao exception
     *
     */
    boolean emailAlreadyExists(User user) throws DAOException;


    /**
     * Checks whether user is banned by his login.
     *
     * If no such login is found then {@link DAOException} will be thrown
     *
     * @param login the login
     * @return the boolean
     * @throws DAOException the dao exception
     *
     */
    boolean userIsBanned(String login) throws DAOException;


    /**
     * Checks whether review was added by the user.
     *
     * If no such login is found then {@link DAOException} will be thrown
     *
     * @param userId the user's id
     * @return the boolean
     * @throws DAOException the dao exception
     *
     */
    boolean reviewWasAdded(int userId) throws DAOException;
}
