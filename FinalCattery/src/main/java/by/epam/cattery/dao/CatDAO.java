package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Cat;
import java.util.List;

public interface CatDAO {
    List<Cat> findAllCats() throws DAOException;
    void addUserCat(Cat cat, String offerId) throws DAOException;
}
