package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;

import java.util.List;

public interface GenericDAO<T> {
    void save(T obj) throws DAOException;
    int saveAndReturnId(T obj) throws DAOException;
    void update(T obj) throws DAOException;
    void updatePhoto(int id, String photo) throws DAOException;
    void delete(int id) throws DAOException;
    List<T> loadAll(int page, int itemsPerPage) throws DAOException;
    List<T> loadAllByStatus(String status, int page, int itemsPerPage) throws DAOException;
    List<T> loadAllById(int id, int page, int itemsPerPage) throws DAOException;
    int getTotalCount() throws DAOException;
    int getTotalCountByStatus(String status) throws DAOException;
    int getTotalCountById(int id) throws DAOException;
    void updateStatusById(String status, int id) throws DAOException;
    T getById(int id) throws DAOException;
    boolean checkStatus(int objectId, String statusToCheck) throws DAOException;
}
