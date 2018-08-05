package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;

import java.util.List;

public interface GenericDAO<T> {
    void save(T obj) throws DAOException;
    int saveAndReturnId(T obj) throws DAOException;
    void update(T obj) throws DAOException;
    void delete(int id) throws DAOException;
    List<T> loadAll() throws DAOException;
    List<T> loadAllById(int id) throws DAOException;
    List<T> loadAllByStatus(String status) throws DAOException;
    void updateStatusById(String status, int id) throws DAOException;
    T getById(int id) throws DAOException;
    boolean checkStatus(int objectId, String statusToCheck) throws DAOException;
}
