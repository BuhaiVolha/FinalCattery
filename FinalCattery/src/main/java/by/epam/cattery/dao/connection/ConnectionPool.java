package by.epam.cattery.dao.connection;

import by.epam.cattery.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public final class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static final Lock lock = new ReentrantLock();
    private BlockingQueue<Connection> freeConnections;
    private BlockingQueue<Connection> takenConnections;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private static final int DEFAULT_POOLSIZE = 5;

    private ConnectionPool() {
    }


    public static ConnectionPool getInstance() {

        if (!isInitialized.get()) {
            lock.lock();

            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    isInitialized.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }


    public void initialize() throws ConnectionPoolException {
        initPoolData();

        try {
            Class.forName(driverName);

            takenConnections = new ArrayBlockingQueue<>(poolSize);
            freeConnections = new ArrayBlockingQueue<>(poolSize);

            for (int i = 0; i < poolSize; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                freeConnections.add(new PooledConnection(connection));
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new ConnectionPoolException("Exception while trying to init connection pool", e);
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
            poolSize = DEFAULT_POOLSIZE;
        }
    }


    Connection takeConnection() throws ConnectionPoolException  {
        Connection connection;

        try {
            connection = freeConnections.take();
            takenConnections.add(connection);

        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Exception while taking connection", e);
        }
        return connection;
    }


    public void closeConnectionQueue() throws ConnectionPoolException {

        for (int i = 0; i < poolSize; i++) {

            try {
                Connection connection = freeConnections.take();

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


    BlockingQueue<Connection> getFreeConnections() {
        return freeConnections;
    }

    BlockingQueue<Connection> getTakenConnections() {
        return takenConnections;
    }
}