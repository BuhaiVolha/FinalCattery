package by.epam.cattery.dao;

import by.epam.cattery.dao.connection.ConnectionPoolException;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.connection.ConnectionProvider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * {@inheritDoc}
 *
 * @param <T> the type parameter
 */
public abstract class BaseDAO<T> implements GenericDAO<T> {
    private static final Logger logger = LogManager.getLogger(BaseDAO.class);

    /**
     * The Connection provider.
     */
    protected final ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void save(T obj) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getCreateQuery());

            executeCreateQuery(ps, obj);
            ps.executeUpdate();

        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Saving object to the database failed", e);
            throw new DAOException(e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int saveAndReturnId(T obj) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getCreateQuery(), Statement.RETURN_GENERATED_KEYS);

            executeCreateQuery(ps, obj);
            ps.executeUpdate();

            rs = ps.getGeneratedKeys();
            rs.next();

            return rs.getInt(1);

        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Saving object to the database failed", e);
            throw new DAOException(e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(rs, ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void update(T obj) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getUpdateQuery());

            executeUpdateQuery(ps, obj);

            int i = ps.executeUpdate();

            if (i != 1) {
                throw new DAOException("Couldn't update object from the database");
            }

        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Updating object failed", e);
            throw new DAOException(e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void updateStatusById(String status, int id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getUpdateStatusQuery());

            executeUpdateStatusQuery(ps, status, id);

            int i = ps.executeUpdate();

            if (i != 1) {
                throw new DAOException("Couldn't update status of object from the database");
            }

        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Updating status failed", e);
            throw new DAOException(e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void updatePhoto(int id, String photo) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getUpdatePhotoQuery());

            ps.setString(1, photo);
            ps.setInt(2, id);

            int i = ps.executeUpdate();

            if (i != 1) {
                throw new DAOException("Couldn't upload photo into database");
            }

        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Uploading photo failed", e);
            throw new DAOException(e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void delete(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getDeleteQuery());

            executeDeleteQuery(ps, id);

            int i = ps.executeUpdate();

            if (i != 1) {
                throw new DAOException("Couldn't update while deleting object from the database");
            }

        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Deleting object from the database failed", e);
            throw new DAOException(e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public List<T> loadAll(int page, int itemsPerPage) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<T> allObjects = new ArrayList<>();

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getQueryForAllObjects());

            ps.setInt(1, itemsPerPage);
            int startIndex = (page - 1) * itemsPerPage;
            ps.setInt(2, startIndex);

            rs = ps.executeQuery();

            while (rs.next()) {
                allObjects.add(readResultSet(rs));
            }

        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Loading all objects from the database to list failed", e);
            throw new DAOException(e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(rs, ps);
        }

        return allObjects;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public List<T> loadAllByStatus(String status, int page, int itemsPerPage) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<T> allObjects = new ArrayList<>();

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getQueryForAllObjectsByStatus());

            ps.setString(1, status);
            ps.setInt(2, itemsPerPage);
            int startIndex = (page - 1) * itemsPerPage;
            ps.setInt(3, startIndex);

            rs = ps.executeQuery();

            while (rs.next()) {
                allObjects.add(readResultSet(rs));
            }

        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Loading all objects by status from the database to list failed", e);
            throw new DAOException(e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(rs, ps);
        }

        return allObjects;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public List<T> loadAllById(int id, int page, int itemsPerPage) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        List<T> allObjects = new ArrayList<>();

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getQueryForAllObjectsById());

            ps.setInt(1, id);
            ps.setInt(2, itemsPerPage);
            int startIndex = (page - 1) * itemsPerPage;
            ps.setInt(3, startIndex);

            rs = ps.executeQuery();

            while (rs.next()) {
                allObjects.add(readResultSet(rs));
            }

        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Loading all objects by ID from the database to list failed", e);
            throw new DAOException(e);

        }  finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(rs, ps);
        }

        return allObjects;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int getTotalCount() throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getQueryForTotalCount());

            rs = ps.executeQuery();

            rs.next();
            return rs.getInt(1);

        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Counting all objects failed", e);
            throw new DAOException(e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(rs, ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int getTotalCountByStatus(String status) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getQueryForTotalCountByStatus());

            ps.setString(1, status);

            rs = ps.executeQuery();

            rs.next();
            return rs.getInt(1);

        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Counting all objects by status failed", e);
            throw new DAOException(e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(rs, ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int getTotalCountById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getQueryForTotalCountById());

            ps.setInt(1, id);

            rs = ps.executeQuery();

            rs.next();
            return rs.getInt(1);

        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Counting all objects by id failed", e);
            throw new DAOException(e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(rs, ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public T getById(int id) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getQueryForSingleObject());

            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();

            return readResultSet(rs);


        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Loading object from the database by ID failed", e);
            throw new DAOException(e);

        }  finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(rs, ps);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean checkStatus(int objectId, String statusToCheck) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        boolean statusMatches;

        try {
            connection = connectionProvider.obtainConnection();
            ps = connection.prepareStatement(getQueryForStatusCheck());

            ps.setInt(1, objectId);
            ps.setString(2, statusToCheck);
            rs = ps.executeQuery();

            rs.next();
            statusMatches = rs.getBoolean(1);

            if (!statusMatches) {
                throw new DAOException("Status wasn't right for the operation");
            }
            return statusMatches;

        } catch (ConnectionPoolException | SQLException e) {
            logger.error("Exception during checking status", e);
            throw new DAOException(e);

        } finally {
            connectionProvider.close(connection);
            connectionProvider.closeResources(rs, ps);
        }
    }


    /**
     * Executes create query.
     *
     * @param ps     the {@link PreparedStatement}
     * @param object the object
     * @throws SQLException the sql exception
     *
     */
    public abstract void executeCreateQuery(PreparedStatement ps, T object) throws SQLException;


    /**
     * Executes update query.
     *
     * @param ps     the {@link PreparedStatement}
     * @param object the object
     * @throws SQLException the sql exception
     *
     */
    public abstract void executeUpdateQuery(PreparedStatement ps, T object) throws SQLException;


    /**
     * Executes update status query.
     *
     * @param ps     the {@link PreparedStatement}
     * @param status the status to be set
     * @param id     the id of object
     * @throws SQLException the sql exception
     *
     */
    public abstract void executeUpdateStatusQuery(PreparedStatement ps, String status, int id) throws SQLException;


    /**
     * Executes delete query.
     *
     * @param ps the {@link PreparedStatement}
     * @param id the id of object
     * @throws SQLException the sql exception
     *
     */
    public abstract void executeDeleteQuery(PreparedStatement ps, int id) throws SQLException;


    /**
     * Gets create query.
     *
     * @return the create query string
     *
     */
    public abstract String getCreateQuery();


    /**
     * Gets update query.
     *
     * @return the update query string
     *
     */
    public abstract String getUpdateQuery();


    /**
     * Gets update status query.
     *
     * @return the update status query string
     *
     */
    public abstract String getUpdateStatusQuery();


    /**
     * Gets update photo query.
     *
     * @return the update photo query string
     *
     */
    public abstract String getUpdatePhotoQuery();


    /**
     * Gets delete query.
     *
     * @return the delete query string
     *
     */
    public abstract String getDeleteQuery();


    /**
     * Gets query for loading all objects.
     *
     * @return the query for loading all objects
     *
     */
    public abstract String getQueryForAllObjects();


    /**
     * Gets query for counting all objects.
     *
     * @return the query for counting all objects
     *
     */
    public abstract String getQueryForTotalCount();


    /**
     * Gets query for loading all objects by status.
     *
     * @return the query for loading all objects by status
     *
     */
    public abstract String getQueryForAllObjectsByStatus();


    /**
     * Gets query for loading all objects by id.
     *
     * @return the query for loading all objects by id
     *
     */
    public abstract String getQueryForAllObjectsById();


    /**
     * Gets query for counting all objects by status.
     *
     * @return the query for counting all objects by status
     *
     */
    public abstract String getQueryForTotalCountByStatus();


    /**
     * Gets query for counting all objects by id.
     *
     * @return the query for counting all objects by id
     *
     */
    public abstract String getQueryForTotalCountById();


    /**
     * Gets query for status check.
     *
     * @return the query for status check
     *
     */
    public abstract String getQueryForStatusCheck();


    /**
     * Gets query for single object.
     *
     * @return the query for single object
     *
     */
    public abstract String getQueryForSingleObject();


    /**
     * Returns an object created from {@link ResultSet}
     *
     * @param rs the {@link ResultSet}
     * @return the object
     * @throws SQLException the sql exception
     *
     */
    public abstract T readResultSet(ResultSet rs) throws SQLException;
}
