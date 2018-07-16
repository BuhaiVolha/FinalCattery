package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Cat;
import java.util.List;

public interface CatDAO {
    List<Cat> findAllCats() throws DAOException;
    void addCat(Cat cat) throws DAOException;
    boolean catAlreadyAdded(int offerId) throws DAOException;
    Cat findSingleCat(int catId) throws DAOException;
}
