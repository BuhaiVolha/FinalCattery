package by.epam.cattery.dao.connection;

import by.epam.cattery.dao.exception.DAOException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Сделать интерфейс и на нем ИМПЛ?: ???????????
// throws DAOException???????
public class ConnectionProvider {
    private static final Logger logger = LogManager.getLogger(ConnectionProvider.class);

    private static ConnectionProvider instance;
    private Connection connection;
    private static final ThreadLocal<Connection> local = new ThreadLocal<>();
    private boolean transactional;


    public static ConnectionProvider getInstance() {

        if (instance == null) {
            instance = new ConnectionProvider();
            logger.log(Level.DEBUG, "Provider was null, new one created");
        }
        logger.log(Level.DEBUG, "There was a Provider, old one was returned");
        return instance;
    }


    public Connection obtainConnection() throws ConnectionPoolException {

        if (!transactional) {
            connection = ConnectionPool.getInstance().takeConnection();

            logger.log(Level.DEBUG, "Non-transactional connection was taken " + connection);
            return connection;
        }

        logger.log(Level.DEBUG, "Transactional connection was taken from threadLocal " + local.get());
        connection = local.get();
        return connection;
    }


    public void startTransaction() throws DAOException {

        try {
            transactional = true;
            connection = ConnectionPool.getInstance().takeConnection();

            logger.log(Level.DEBUG, "Transaction was started by " + connection);
            connection.setAutoCommit(false);
            local.set(connection);
            logger.log(Level.DEBUG, "Connection in threadLocal " + local.get());

        } catch (ConnectionPoolException | SQLException e) {
            logger.log(Level.ERROR, "Starting transaction failed");
            throw new DAOException(e);
        }
    }


    public void commitTransaction() throws DAOException {
        connection = local.get();

        if (connection != null) {
            logger.log(Level.DEBUG, "Connection is not null " + connection);

            try {
                connection.commit();
                logger.log(Level.DEBUG, "Committing is done. Connection " + connection + " closed");

            } catch (SQLException e) {
                logger.log(Level.ERROR, "Committing transaction failed");
                throw new DAOException(e);
            }
        }
    }


    public void abortTransaction() { //throws DAO EXCEPTION???????
        connection = local.get();

        if (connection != null) {

            logger.log(Level.DEBUG, "Connection is not null " + connection);

            try {
                connection.rollback();
                logger.log(Level.DEBUG, "Rollback was executed by " + connection);

            } catch (SQLException e) {
                logger.log(Level.ERROR, "Rollbacking transaction failed");
            }
        }
    }


    public void endTransaction() {

        try {
            if (connection != null) {

                connection.setAutoCommit(true);
                connection.close();
                local.remove();
                transactional = false;
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, "Closing transaction failed");
        }
    }


    public void close(Connection connection) {

        if (!transactional) {
            try {
                connection.close();
                logger.log(Level.DEBUG, "Non-trans connection" + connection + " was returned to the pool");
            } catch (SQLException e) {
                logger.log(Level.WARN, "Connection wasn't returned to the pool");
            }
//        } else {
//            local.set(connection);
        }
    }


    public void setTransactionIsolation(int lvl) {

        try {
            local.get().setTransactionIsolation(lvl);
            logger.log(Level.DEBUG, "Connection lvl was set to " + local.get().getTransactionIsolation());
        } catch (SQLException e) {
            logger.log(Level.WARN, "Setting transaction lvl failed");
        }
    }

    public void closeResources(ResultSet rs, Statement st) {

        if (rs != null) {
            try {
                rs.close();
                logger.log(Level.DEBUG, "ResultSet was closed");
            } catch (SQLException e) {
                logger.log(Level.WARN, "ResultSet wasn't closed");
            }
        }

        closeResources(st);
    }


    public void closeResources(Statement st) {

        if (st != null) {
            try {
                st.close();
                logger.log(Level.DEBUG, "Statement was closed");
            } catch (SQLException e) {
                logger.log(Level.WARN, "Statement wasn't closed");
            }
        }
    }


    public boolean isTransactional() {
        return transactional;
    }


    public void setTransactional(boolean transactional) {
        this.transactional = transactional;
    }
}
