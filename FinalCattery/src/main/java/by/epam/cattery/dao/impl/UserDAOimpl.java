package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.UserDAO;
import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Role;
import by.epam.cattery.entity.User;
import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAOimpl implements UserDAO {
    private final ConnectionPool connectionPool;

    private static final String ADD_USER = "INSERT INTO user (login, password, name, lastname, email, phone) VALUES(?,?,?,?,?,?)";
    private static final String LOGIN_ALREADY_EXISTS = "SELECT EXISTS (SELECT 1 FROM user WHERE login=?)";
    private static final String UPDATE_COLOUR_PREFERENCE = "UPDATE user SET colour_preference=? WHERE user_id = ?;";
    private static final String SET_TOTAL = "SET @total=0;";
    private static final String COUNT_PERCENTAGE = "SELECT colour_preference, count(*) as Count, count(*) / @total * 100 AS Percent FROM (SELECT colour_preference, @total := @total + 1 FROM user WHERE colour_preference IS NOT NULL) temp GROUP BY colour_preference;";


    public UserDAOimpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public int addUser(User user) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int userId;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserLogin());
            ps.setString(2, user.getUserPass());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getUserLastname());
            ps.setString(5, user.getEmail());
            ps.setString(6, user.getPhone());

            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            //rs.next();
            userId = rs.getInt(1);

            return userId;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("error adding user in dao ", e);  // Отдельно каждый

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }


    @Override
    public boolean loginAlreadyExists(User user) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(LOGIN_ALREADY_EXISTS);
            ps.setString(1, user.getUserLogin());

            rs = ps.executeQuery();
            rs.next();
            exists = rs.getBoolean(1);

            return exists;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("error during checking whether login already exists", e);

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }

    // убрать лишнее
    @Override
    public User findUser(String login, String password) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User user = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement("SELECT user_id, login, password, role FROM user " +
                    "JOIN user_role ON (user.role_id = user_role.role_id) WHERE login= ?;");

            ps.setString(1, login);
            rs = ps.executeQuery();
            boolean loginExists = rs.next();

            if (loginExists) {
                boolean correctPass = BCrypt.checkpw(password, rs.getString(3)); //nvarchar(120) ?

                if (correctPass) {
                    user = new User();

                    user.setId(rs.getInt(1));
                    user.setUserLogin(rs.getString(2));
                    user.setUserRole(Role.valueOf(rs.getString(4).toUpperCase()));
                }
            }
            return user;

        } catch (ConnectionPoolException | SQLException | IllegalArgumentException e) {

            throw new DAOException("error while finding user in bd ", e);

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }


    @Override
    public User findUserInfo(int userId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User user;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement("SELECT user_id, login, password, role, name, lastname," +
                    " email, phone, colour_name, discount, flag_banned, flag_review_left FROM user " +
                    "JOIN user_role ON (user.role_id = user_role.role_id) " +
                    " LEFT JOIN cat_colour ON (colour_preference = EMS_code) WHERE user_id = ?;");
            ps.setInt(1, userId);
            rs = ps.executeQuery();

            user = createUser(rs);
            return user;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception while fetching user info from bd ", e);

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }


    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();

        while (rs.next()) {
            user.setId(rs.getInt(1));
            user.setUserLogin(rs.getString(2));
            user.setUserRole(Role.valueOf(rs.getString(4).toUpperCase()));
            user.setUserName(rs.getString(5));
            user.setUserLastname(rs.getString(6));
            user.setEmail(rs.getString(7));
            user.setPhone(rs.getString(8));
            user.setUserColorPreference(rs.getString(9));
            user.setDiscount(rs.getInt(10));
            user.setBanned(rs.getBoolean(11));
            user.setReviewLeft(rs.getBoolean(12));
        }

        return user;
    }


 // по фамилии ?
    @Override
    public List<User> findAllUsers() throws DAOException {
        List<User> users = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement("SELECT user_id, login, name, lastname, email, " +
                    "phone, colour_name, role, discount, flag_banned, flag_review_left " +
                    "FROM user JOIN user_role ON (user.role_id = user_role.role_id)" +
                    "LEFT JOIN cat_colour ON (colour_preference = EMS_code)");
            rs = ps.executeQuery();

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt(1));
                user.setUserLogin(rs.getString(2));
                user.setUserName(rs.getString(3));
                user.setUserLastname(rs.getString(4));
                user.setEmail(rs.getString(5));
                user.setPhone(rs.getString(6));
                user.setUserColorPreference(rs.getString(7));
                user.setUserRole(Role.valueOf(rs.getString(8).toUpperCase()));
                user.setDiscount(rs.getInt(9));        // отдельные константы числа
                user.setBanned(rs.getBoolean(10));
                user.setReviewLeft(rs.getBoolean(11));

                users.add(user);
            }
            return users;

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during gathering users", e);

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }


    @Override
    public void updateColourPreference(User user) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(UPDATE_COLOUR_PREFERENCE);

            ps.setString(1, user.getUserColorPreference());
            ps.setInt(2, user.getId());

            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);

        } catch (SQLException e) {
            throw new DAOException("Exception during updating user colour preference", e);

        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }


    @Override
    public String countStatistics() throws DAOException {
        Gson gsonObj = new Gson();
        Map<Object,Object> map = null;
        List<Map<Object, Object>> list = new ArrayList<>();


        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = connectionPool.takeConnection();
//            ps = con.prepareStatement("SET @total=0;");
//            ps.executeQuery(); // Это вообще так работает?
//            ps2 = con.prepareStatement("SELECT colour_preference, FORMAT(count(*) / @total * 100, 2) FROM (SELECT colour_preference, @total := @total + 1 FROM user WHERE colour_preference IS NOT NULL) temp GROUP BY colour_preference;");
//
            ps = con.prepareStatement("SELECT I.colour_preference AS colour, FORMAT(COUNT(*) / T.total * 100,2) AS percent\n" +
                    "FROM cattery.user AS I,\n" +
                    "     (SELECT COUNT(*) AS total FROM cattery.user WHERE colour_preference IS NOT NULL) AS T\n" +
                    "WHERE colour_preference IS NOT NULL\n" +
                    "GROUP BY colour;");
            ps.executeQuery();
            rs = ps.executeQuery();

            while (rs.next()) {

                map = new HashMap<>();
                map.put("y", rs.getDouble(2));
                map.put("label", rs.getString(1));

                list.add(map);

            }
            return gsonObj.toJson(list);

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("Exception during gathering users", e);

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
    }


    @Override
    public void reverseUserBannedFlag(String userId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement("UPDATE user SET flag_banned = NOT flag_banned WHERE user_id = ?");

            ps.setString(1, userId);

            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);

        } catch (SQLException e) {
            throw new DAOException("Exception while reversing user ban flag", e);

        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }


    @Override
    public void setDiscount(User user) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement("UPDATE user SET discount = ? WHERE user_id = ?");

            ps.setInt(1, user.getDiscount());
            ps.setInt(2, user.getId());

            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);

        } catch (SQLException e) {
            throw new DAOException("Exception while updating user's discount", e);

        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }


    @Override
    public void reverseExpertRole(int userId) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = connectionPool.takeConnection();
            ps = con.prepareStatement("UPDATE user SET role_id = CASE WHEN role_id = 1 THEN 3 WHEN role_id = 3  THEN 1 END WHERE user_id = ?");

            ps.setInt(1, userId);

            ps.executeUpdate();

        } catch (ConnectionPoolException e) {
            throw new DAOException("Exception while connecting via pool", e);

        } catch (SQLException e) {
            throw new DAOException("Exception while reversing expert role", e);

        } finally {
            connectionPool.closeConnection(con, ps);
        }
    }
}
