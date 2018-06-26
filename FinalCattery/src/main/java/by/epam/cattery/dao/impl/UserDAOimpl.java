package by.epam.cattery.dao.impl;

import by.epam.cattery.dao.UserDAO;
import by.epam.cattery.dao.connection.ConnectionPool;
import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.entity.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDAOimpl implements UserDAO {
    private final ConnectionPool connectionPool; // local var???????????/////

    private static final String ADD_USER = "INSERT INTO user (login, password) VALUES(?,?)";
    private static final String LOGIN_ALREADY_EXISTS = "SELECT EXISTS(SELECT 1 FROM user WHERE `login`=?)";

    public UserDAOimpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public int addUser(User user) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int userId = -1;

        try {
            //con = ConnectionProvider.getCon();
            con = connectionPool.takeConnection();

            ps = con.prepareStatement(ADD_USER, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUserLogin());
            ps.setString(2, user.getUserPass());
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            rs.next();
            userId = rs.getInt(1);

        } catch (ConnectionPoolException | SQLException e) {
            System.err.println("error adding user in dao " + e);

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return userId;
    }


    @Override
    public boolean loginAlreadyExists(User user) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean exists = false;

        try {
            //con = ConnectionProvider.getCon();
            con = connectionPool.takeConnection();
            ps = con.prepareStatement(LOGIN_ALREADY_EXISTS);
            ps.setString(1, user.getUserLogin());

            rs = ps.executeQuery();
            rs.next();
            exists = rs.getBoolean(1);

        } catch (ConnectionPoolException | SQLException e) {
            System.err.println("error during checking whether login already exists");

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return exists;
    }


    @Override
    public User findUser(String login, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        try {
            //con = ConnectionProvider.getCon();
            con = connectionPool.takeConnection();
            ps = con.prepareStatement("SELECT `user_id`, `login`, `password` FROM user WHERE `login`= ?;");
            ps.setString(1, login);
            rs = ps.executeQuery();
            rs.next();

            boolean correctPass = BCrypt.checkpw(password, rs.getString(3));
            if (correctPass) {
                user = createUser(rs);
            }

        } catch (ConnectionPoolException | SQLException e) {
            System.err.println("error while finding user " + e);

        } finally {
            connectionPool.closeConnection(con, ps, rs);
        }
        return user;
    }


    private User createUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt(1));
        user.setUserLogin(rs.getString(2));
        return user;
    }
}
