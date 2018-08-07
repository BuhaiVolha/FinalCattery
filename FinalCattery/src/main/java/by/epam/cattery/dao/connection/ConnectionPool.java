package by.epam.cattery.dao.connection;

import by.epam.cattery.util.ConfigurationManager;

import java.sql.*;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// подумать !
public final class ConnectionPool {
    private static final ConnectionPool instance = new ConnectionPool();

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private ConnectionPool() {
    }


    public static ConnectionPool getInstance() {
        return instance;
    }

    public void initialize() throws ConnectionPoolException {
        initPoolData();

        try {
            Class.forName(driverName);

            givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);
            connectionQueue = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionQueue.add(new PooledConnection(connection));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionPoolException("Exception while trying to init ConnectionPool", e);
        }
    }

    private void initPoolData() {
        ConfigurationManager configurationManager = ConfigurationManager.getInstance();

        this.driverName = configurationManager.getDatabaseParameters(DBParameter.DB_DRIVER);
        this.url = configurationManager.getDatabaseParameters(DBParameter.DB_URL);
        this.user = configurationManager.getDatabaseParameters(DBParameter.DB_USER);
        this.password = configurationManager.getDatabaseParameters(DBParameter.DB_PASSWORD);

        try {
            this.poolSize = Integer.parseInt(configurationManager.getDatabaseParameters(DBParameter.DB_POOL_SIZE));

        } catch (NumberFormatException e) {
            poolSize = 5; // constant
        }
    }



    public Connection takeConnection() throws ConnectionPoolException  {
        Connection connection;

        try {
            connection = connectionQueue.take();
            givenAwayConQueue.add(connection);

        } catch (InterruptedException e) {
            throw new ConnectionPoolException("error connecting to data source", e);
        }
        return connection;
    }


    public void closeConnectionQueue() throws ConnectionPoolException {

        for (int i = 0; i < poolSize; i++) {

            try {
                Connection connection = connectionQueue.take();

                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                ((PooledConnection) connection).reallyClose();

            } catch (SQLException | InterruptedException e) {
                throw new ConnectionPoolException("Exception while closing conn pool.", e);
            }
        }
    }


    public void deregisterAllDrivers() throws ConnectionPoolException {

        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();

            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            throw new ConnectionPoolException("Exception while deregistering drivers", e);
        }
    }


    public BlockingQueue<Connection> getConnectionQueue() {
        return connectionQueue;
    }

    public BlockingQueue<Connection> getGivenAwayConQueue() {
        return givenAwayConQueue;
    }
}