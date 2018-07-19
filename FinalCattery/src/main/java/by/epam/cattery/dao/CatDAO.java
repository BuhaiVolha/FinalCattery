package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;

import java.util.List;

public interface CatDAO {
    List<Cat> findAllCats() throws DAOException;
    List<Cat> findAllCatsByStatus(CatStatus status) throws DAOException;
    List<Cat> findAllCatsWithDiscount(int userId) throws DAOException;
    List<Cat> findAllCatsByStatusWithDiscount(int userId, CatStatus status) throws DAOException;
    void addCat(Cat cat) throws DAOException;
    void updateCat(Cat cat) throws DAOException;
    void deleteCat(int catId) throws DAOException;
    boolean catAlreadyAdded(int offerId) throws DAOException;
    Cat findSingleCatWithDiscount(int catId, int userId) throws DAOException;
    Cat findSingleCat(int catId) throws DAOException;
}
