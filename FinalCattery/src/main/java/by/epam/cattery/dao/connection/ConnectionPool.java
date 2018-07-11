package by.epam.cattery.dao.connection;

import java.sql.*;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
// подумать !
public class ConnectionPool {
    private static final ConnectionPool instance = new ConnectionPool();

    private BlockingQueue<Connection> connectionQueue;
    private BlockingQueue<Connection> givenAwayConQueue;

    private String driverName;
    private String url;
    private String user;
    private String password;
    private int poolSize;

//    private ConnectionPool() {
//        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
//        this.driverName = dbResourceManager.getParameter(DBParameter.DB_DRIVER);
//        this.url = dbResourceManager.getParameter(DBParameter.DB_URL);
//        this.user = dbResourceManager.getParameter(DBParameter.DB_USER);
//        this.password = dbResourceManager.getParameter(DBParameter.DB_PASSWORD);
//
//        try {
//            this.poolSize = Integer.parseInt(dbResourceManager.getParameter(DBParameter.DB_POOL_SIZE));
//
//        } catch (NumberFormatException e) {
//            poolSize = 5; // constant
//        }
//    }

    private ConnectionPool() {
    }


//    public static ConnectionPool getInstance() {
//        try {
//            instance.initPoolData();
//
//        } catch (ConnectionPoolException e) {
//            System.err.println("error getting instance of conn pool");
//        }
//        return instance;
//    }

//    public void initPoolData() throws ConnectionPoolException {
//
//        try {
//            Class.forName(driverName);
//            givenAwayConQueue = new ArrayBlockingQueue<>(poolSize);
//            connectionQueue = new ArrayBlockingQueue<>(poolSize);
//
//            for (int i = 0; i < poolSize; i++) {
//                Connection connection = DriverManager.getConnection(url, user, password); //user
//                connectionQueue.add(new PooledConnection(connection));
//            }
//        } catch (ClassNotFoundException | SQLException e) {
//            throw new ConnectionPoolException("Exception while trying to init ConnectionPool", e);
//        }
//    }


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
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();
        this.driverName = dbResourceManager.getParameter(DBParameter.DB_DRIVER);
        this.url = dbResourceManager.getParameter(DBParameter.DB_URL);
        this.user = dbResourceManager.getParameter(DBParameter.DB_USER);
        this.password = dbResourceManager.getParameter(DBParameter.DB_PASSWORD);

        try {
            this.poolSize = Integer.parseInt(dbResourceManager.getParameter(DBParameter.DB_POOL_SIZE));

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


    public void closeConnection(Connection con, Statement st, ResultSet rs) {
        try {
            if (con != null) {
                con.close();
            }

        } catch (SQLException e) {
            System.err.println("closing connection failed " + e);
            // logger error
        }

        try {
            if (st != null) {
                st.close();
            }

        } catch (SQLException e) {
            System.err.println("closing statement failed " + e);
            // logger error
        }
        try {
            if (rs != null) {
                rs.close();
            }

        } catch (SQLException e) {
            System.err.println("closing resultset failed " + e);
            // logger error
        }
    }


    public void closeConnection(Connection con, Statement st) {
        try {
            if (con != null) {
                con.close();
            }

        } catch (SQLException e) {
            System.err.println("closing connection failed " + e);
            // logger error
        }

        try {
            if (st != null) {
                st.close();
            }

        } catch (SQLException e) {
            System.err.println("closing statement failed " + e);
            // logger error
        }
    }


//    private void closeConnectionsQueue(BlockingQueue<Connection> queue) throws SQLException {
//        Connection connection;
//
//        while ((connection = queue.poll()) != null) {
//            if (!connection.getAutoCommit()) {
//                connection.commit();
//            }
//            ((PooledConnection) connection).reallyClose();
//        }
//    }


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

    public BlockingQueue<Connection> getConnectionQueue() {
        return connectionQueue;
    }

    public BlockingQueue<Connection> getGivenAwayConQueue() {
        return givenAwayConQueue;
    }
}