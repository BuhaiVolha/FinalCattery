package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.UserDAO;
import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Role;
import by.epam.cattery.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDAOimpl implements UserDAO {
    private final ConnectionPool connectionPool;

    private static final String ADD_USER = "INSERT INTO user (login, password, name, lastname, email, phone) VALUES(?,?,?,?,?,?)";
    private static final String LOGIN_ALREADY_EXISTS = "SELECT EXISTS(SELECT 1 FROM user WHERE `login`=?)";

    public UserDAOimpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }


    @Override
    public boolean addUser(User user) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //int userId = -1;

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
//            //rs.next();
//            userId = rs.getInt(1); если ид нужно будет возвращать

            return rs.next();

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("error adding user in dao ", e);  // Отдельно каждый

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        //return userId;
    }


    @Override
    public boolean loginAlreadyExists(User user) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(LOGIN_ALREADY_EXISTS);
            ps.setString(1, user.getUserLogin());

            rs = ps.executeQuery();
            rs.next();
            exists = rs.getBoolean(1);

        } catch (ConnectionPoolException | SQLException e) {
            throw new DAOException("error during checking whether login already exists", e);

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return exists;
    }


    @Override
    public User findUser(String login, String password) throws DAOException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        User user = null;

        try {
            con = connectionPool.takeConnection();

            ps = con.prepareStatement("SELECT `user_id`, `login`, `password`, `role`, `name`, `lastname`," +
                    " `email`, `phone`, `colour_preference`, `discount`, `flag_banned` FROM user " +
                    "JOIN user_role ON (user.role_id = user_role.role_id) WHERE `login`= ?;");
            ps.setString(1, login);
            rs = ps.executeQuery();
            boolean loginExists = rs.next();

            if (loginExists) {
                boolean correctPass = BCrypt.checkpw(password, rs.getString(3)); //nvarchar(120) ?

                if (correctPass) {
                    user = createUser(rs);
                }
            }

        } catch (ConnectionPoolException | SQLException | IllegalArgumentException e) {

            throw new DAOException("error while finding user in bd ", e);

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return user;
    }


    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();

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

        return user;
    }
}
