package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.BaseDAO;
import by.epam.cattery.dao.UserDAO;

import by.epam.cattery.entity.CatBodyColour;
import by.epam.cattery.entity.Role;
import by.epam.cattery.entity.User;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.mindrot.jbcrypt.BCrypt;
import com.google.gson.Gson;

import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);

    private static final String CREATE_USER =
            "INSERT INTO user " +
                    "(login, password, name, lastname, email, phone) " +
                    "VALUES(?, ?, ?, ?, ?, ?);";

    private static final String UPDATE_USER =
            "UPDATE user " +
                    "SET login = ?, password = ?, name = ?, lastname = ?, email = ?, phone = ? " +
                    "WHERE user_id = ?;";
    private static final String UPDATE_DISCOUNT =
            "UPDATE user " +
                    "SET discount = ? " +
                    "WHERE user_id = ?;";
    private static final String UPDATE_COLOUR_PREFERENCE =
            "UPDATE user " +
                    "SET colour_preference = ? " +
                    "WHERE user_id = ?;";

    private static final String GET_ALL_USERS =
            "SELECT user_id, login, name, lastname, email, phone, colour_preference, role, discount, flag_banned, flag_review_left " +
                    "FROM user " +
                        "JOIN user_role ON (user.role_id = user_role.role_id)" +

                    "ORDER BY login LIMIT ? OFFSET ?;";

    private static final String GET_USER_BY_LOGIN =
            "SELECT user_id, login, password, role " +
                    "FROM user " +
                        "JOIN user_role ON (user.role_id = user_role.role_id) " +
                    "WHERE login = ?;";
    private static final String GET_USER_BY_ID =
            "SELECT user_id, login, name, lastname, email, phone, colour_preference, role, discount, flag_banned, flag_review_left " +
                    "FROM user " +
                        "JOIN user_role ON (user.role_id = user_role.role_id)" +
                    "WHERE user_id = ?;";

    private static final String GET_USERS_COUNT =
            "SELECT COUNT(*) " +
                    "FROM user;";

    private static final String REVERSE_BANNED_FLAG =
            "UPDATE user " +
                    "SET flag_banned = NOT flag_banned " +
                    "WHERE user_id = ?;";
    private static final String REVERSE_REVIEW_LEFT_FLAG =
            "UPDATE user " +
                    "SET flag_review_left= NOT flag_review_left " +
                    "WHERE user_id = ?;";
    private static final String REVERSE_EXPERT_ROLE_FOR_USER =
            "UPDATE user " +
                    "SET role_id = " +
                        "CASE " +
                            "WHEN role_id = 1 " +
                            "THEN 3 " +
                            "WHEN role_id = 3  " +
                            "THEN 1 " +
                        "END " +
                    "WHERE user_id = ?;";

    private static final String GET_COLOUR_STATISTICS =
            "SELECT I.colour_preference AS colour, " +
                    "FORMAT(COUNT(*) / T.total * 100,2) AS percent " +
                    "FROM cattery.user AS I,  " +
                        "(SELECT COUNT(*) AS total " +
                        "FROM cattery.user " +
                        "WHERE colour_preference IS NOT NULL) AS T " +
                    "WHERE colour_preference IS NOT NULL " +
                    "GROUP BY colour;";
    private static final String GET_DISCOUNT =
            "SELECT discount " +
                    "FROM user " +
                    "WHERE user_id = ?;";
    private static final String GET_EMAIL =
            "SELECT email " +
                    "FROM user " +
                    "WHERE user_id = ?;";

    private static final String CHECK_BANNED_FLAG =
            "SELECT flag_banned " +
                    "FROM user " +
                    "WHERE login =?;";
    private static final String CHECK_LOGIN_ALREADY_EXISTS =
            "SELECT EXISTS " +
                    "(SELECT 1 " +
                        "FROM user " +
                        "WHERE login = ?);";
    private static final String CHECK_EMAIL_ALREADY_EXISTS =
            "SELECT EXISTS " +
                    "(SELECT 1 " +
                        "FROM user " +
                        "WHERE email = ?);";
    private static final String CHECK_REVIEW_WAS_ADDED =
            "SELECT flag_review_left " +
                    "FROM user " +
                    "WHERE user_id = ?;";

    private final static String COLOUR_STATISTICS_PARAM_COLOUR = "label";
    private final static String COLOUR_STATISTICS_PARAM_PREFERENCE_PROCENTS = "y";

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public boolean loginExists(String login) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(CHECK_LOGIN_ALREADY_EXISTS);
            ps.setString(1, login);

            rs = ps.executeQuery();
            rs.next();

            return rs.getBoolean(1);

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during checking whether login already taken", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }


    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User user = null;

        try {
            con = connectionProvider.obtainConnection();
            ps = con.prepareStatement(GET_USER_BY_LOGIN);

            ps.setString(1, login);
            rs = ps.executeQuery();
            boolean loginExists = rs.next();

            if (loginExists) {
                boolean correctPass = BCrypt.checkpw(password, rs.getString(3)); //nvarchar(120) ?

                if (correctPass) {
                    user = new User();

                    user.setId(rs.getInt(1));
                    user.setLogin(rs.getString(2));
                    user.setRole(Role.valueOf(rs.getString(4).toUpperCase()));
                }
            }
            return user;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception while finding user in bd ", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }


    @Override
    public int getDiscount(int userId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(GET_DISCOUNT);
            ps.setInt(1, userId);

            rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during getting discount for user ", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }


    @Override
    public String getEmail(int userId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(GET_EMAIL);
            ps.setInt(1, userId);

            rs = ps.executeQuery();
            rs.next();
            return rs.getString(1);

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during getting email for user ", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }


    @Override
    public boolean emailAlreadyExists(User user) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean exists;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(CHECK_EMAIL_ALREADY_EXISTS);
            ps.setString(1, user.getEmail());

            rs = ps.executeQuery();
            rs.next();
            exists = rs.getBoolean(1);

            return exists;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during checking whether email already taken", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }


    @Override
    public String getColourPreferenceStatistics() throws DAOException {
        Gson gsonObj = new Gson();
        Map<Object, Object> map;
        List<Map<Object, Object>> list = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(GET_COLOUR_STATISTICS);
            ps.executeQuery();
            rs = ps.executeQuery();

            while (rs.next()) {
                map = new HashMap<>();

                map.put(COLOUR_STATISTICS_PARAM_COLOUR, rs.getString(1));
                map.put(COLOUR_STATISTICS_PARAM_PREFERENCE_PROCENTS, rs.getDouble(2));
                list.add(map);
            }
            return gsonObj.toJson(list);

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during getting statistics users", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }


    @Override
    public void updateColourPreference(User user) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();
            ps = con.prepareStatement(UPDATE_COLOUR_PREFERENCE);

            ps.setString(1, user.getColourPreference());
            ps.setInt(2, user.getId());

            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during updating user colour preference", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }


    @Override
    public void reverseUserBannedFlag(int userId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();
            ps = con.prepareStatement(REVERSE_BANNED_FLAG);

            ps.setInt(1, userId);

            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception while reversing user ban flag", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }

    @Override
    public void reverseUserReviewFlag(int userId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();
            ps = con.prepareStatement(REVERSE_REVIEW_LEFT_FLAG);

            ps.setInt(1, userId);

            int i = ps.executeUpdate();
            if (i != 1) {
                throw new DAOException("Something went wrong while reversing review left flag! ");
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception while reversing review left flag", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }


    @Override
    public void updateDiscount(User user) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();
            ps = con.prepareStatement(UPDATE_DISCOUNT);

            ps.setInt(1, user.getDiscount());
            ps.setInt(2, user.getId());

            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception while updating user's discount", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }


    @Override
    public void reverseExpertRole(int userId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionProvider.obtainConnection();
            ps = con.prepareStatement(REVERSE_EXPERT_ROLE_FOR_USER);

            ps.setInt(1, userId);

            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception while reversing expert role", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(ps);
        }
    }


    @Override
    public boolean userIsBanned(String login) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(CHECK_BANNED_FLAG);
            ps.setString(1, login);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBoolean(1);
            } else {
                throw new DAOException("No such user exists");
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during checking whether user is banned", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }


    @Override
    public boolean reviewWasAdded(int userId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionProvider.obtainConnection();

            ps = con.prepareStatement(CHECK_REVIEW_WAS_ADDED);
            ps.setInt(1, userId);

            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getBoolean(1);
            } else {
                throw new DAOException("No such user exists");
            }

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during checking whether review already added", e);

        } finally {
            connectionProvider.close(con);
            connectionProvider.closeResources(rs, ps);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void executeCreateQuery(PreparedStatement ps, User user) throws SQLException {

        ps.setString(1, user.getLogin());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());
        ps.setString(4, user.getLastname());
        ps.setString(5, user.getEmail());
        ps.setString(6, user.getPhone());
    }


    @Override
    public void executeUpdateQuery(PreparedStatement ps, User user) throws SQLException {

        ps.setString(1, user.getLogin());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getName());
        ps.setString(4, user.getLastname());
        ps.setString(5, user.getEmail());
        ps.setString(6, user.getPhone());
        ps.setInt(7, user.getId());
    }


    @Override
    public void executeUpdateStatusQuery(PreparedStatement ps, String status, int id) {
        logger.log(Level.WARN, "Getting all objects by ID is not implemented for User");
        throw new UnsupportedOperationException();
    }


    @Override
    public void executeDeleteQuery(PreparedStatement ps, int userId) {
        logger.log(Level.WARN, "Deleting is not implemented for User");
        throw new UnsupportedOperationException();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public String getCreateQuery() {
        return CREATE_USER;
    }


    @Override
    public String getUpdateQuery() {
        return UPDATE_USER;
    }


    @Override
    public String getUpdateStatusQuery() {
        logger.log(Level.WARN, "Updating status is not implemented for User");
        throw new UnsupportedOperationException();
    }


    @Override
    public String getUpdatePhotoQuery() {
        logger.log(Level.WARN, "Execute update photo is not implemented for User (sounds like a good idea, maybe later)");
        throw new UnsupportedOperationException();
    }


    @Override
    public String getDeleteQuery() {
        logger.log(Level.WARN, "Deleting is not implemented for User");
        throw new UnsupportedOperationException();
    }

    @Override
    public String getQueryForAllObjects() {
        return GET_ALL_USERS;
    }

    @Override
    public String getQueryForTotalCount() {
        return GET_USERS_COUNT;
    }

    @Override
    public String getQueryForAllObjectsByStatus() {
        logger.log(Level.WARN, "Taking all users by status is not implemented for User");
        throw new UnsupportedOperationException();
    }

    @Override
    public String getQueryForTotalCountByStatus() {
        logger.log(Level.WARN, "Counting all users by status is not implemented for User");
        throw new UnsupportedOperationException();
    }

    @Override
    public String getQueryForTotalCountById() {
        logger.log(Level.WARN, "Counting all users by id is not implemented for User");
        throw new UnsupportedOperationException();
    }

    @Override
    public String getQueryForAllObjectsById() {
        logger.log(Level.WARN, "Taking all users by id is not implemented for User");
        throw new UnsupportedOperationException();
    }

    @Override
    public String getQueryForSingleObject() {
        return GET_USER_BY_ID;
    }

    @Override
    public String getQueryForStatusCheck() {
        logger.log(Level.WARN, "Execute status check is not implemented for User");
        throw new UnsupportedOperationException();
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public User readResultSet(ResultSet rs) throws SQLException {
        User user = new User();

        user.setId(rs.getInt(1));
        user.setLogin(rs.getString(2));
        user.setName(rs.getString(3));
        user.setLastname(rs.getString(4));
        user.setEmail(rs.getString(5));
        user.setPhone(rs.getString(6));
        user.setColourPreference(rs.getString(7));
        user.setRole(Role.valueOf(rs.getString(8).toUpperCase()));
        user.setDiscount(rs.getInt(9));
        user.setBanned(rs.getBoolean(10));
        user.setReviewLeft(rs.getBoolean(11));

        return user;
    }
}
