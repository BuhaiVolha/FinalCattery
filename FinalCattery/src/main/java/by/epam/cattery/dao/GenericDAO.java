package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;

import java.util.List;


/**
 * Defines basic methods for all DAOs.
 *
 * @param <T> the type parameter
 *
 */
public interface GenericDAO<T> {

    /**
     * Saves new object to the database.
     *
     * @param obj the object
     * @throws DAOException the dao exception
     *
     */
    void save(T obj) throws DAOException;


    /**
     * Saves new object to the database and returns its id.
     *
     * @param obj the object
     * @return its id
     * @throws DAOException the dao exception
     *
     */
    int saveAndReturnId(T obj) throws DAOException;


    /**
     * Updates an object.
     *
     * If update wasn't made {@link DAOException} will be thrown
     *
     * @param obj the object
     * @throws DAOException the dao exception
     *
     */
    void update(T obj) throws DAOException;


    /**
     * Updates photo for object by id.
     *
     * If update wasn't made {@link DAOException} will be thrown
     *
     * @param id    the id
     * @param photo the photo's name
     * @throws DAOException the dao exception
     *
     */
    void updatePhoto(int id, String photo) throws DAOException;


    /**
     * Deletes the object from the database.
     *
     * If deleting wasn't made {@link DAOException} will be thrown
     *
     * @param id the object's id
     * @throws DAOException the dao exception
     *
     */
    void delete(int id) throws DAOException;


    /**
     * Loads all as a list taking into account pagination.
     *
     * @param page         the current page
     * @param itemsPerPage the items per page
     * @return the list of objects
     * @throws DAOException the dao exception
     *
     */
    List<T> loadAll(int page, int itemsPerPage) throws DAOException;


    /**
     * Loads all by status as a list taking into account pagination.
     *
     * @param status       the status by which objects will be loaded
     * @param page         the current page
     * @param itemsPerPage the items per page
     * @return the list of objects
     * @throws DAOException the dao exception
     *
     */
    List<T> loadAllByStatus(String status, int page, int itemsPerPage) throws DAOException;


    /**
     * Loads all by id as a list taking into account pagination.
     *
     * @param id           the id
     * @param page         the current page
     * @param itemsPerPage the items per page
     * @return the list of objects
     * @throws DAOException the dao exception
     *
     */
    List<T> loadAllById(int id, int page, int itemsPerPage) throws DAOException;


    /**
     * Counts all objects.
     *
     * @return the total count
     * @throws DAOException the dao exception
     *
     */
    int getTotalCount() throws DAOException;


    /**
     * Counts all objects by status.
     *
     * @param status the status
     * @return the total count by status
     * @throws DAOException the dao exception
     *
     */
    int getTotalCountByStatus(String status) throws DAOException;


    /**
     * Counts all objects by id.
     *
     * @param id the id
     * @return the total count by id
     * @throws DAOException the dao exception
     *
     */
    int getTotalCountById(int id) throws DAOException;


    /**
     * Updates status of object by its id.
     *
     * If update wasn't made {@link DAOException} will be thrown
     *
     * @param status the status to be set
     * @param id     the object's id
     * @throws DAOException the dao exception
     *
     */
    void updateStatusById(String status, int id) throws DAOException;


    /**
     * Gets single object by its id.
     *
     * @param id the id of the object
     * @return the object
     * @throws DAOException the dao exception
     *
     */
    T getById(int id) throws DAOException;


    /**
     * Check object's status.
     *
     * If checking has failed {@link DAOException} will be thrown
     *
     * @param objectId      the object's id
     * @param statusToCheck the status to check
     * @return the boolean
     * @throws DAOException the dao exception
     *
     */
    boolean checkStatus(int objectId, String statusToCheck) throws DAOException;
}
