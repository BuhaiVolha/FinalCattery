package by.epam.cattery.service;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;

import java.util.List;

public interface CatService {
    List<Cat> takeAllCats() throws ServiceException;
    List<Cat> takeCatsByStatus(CatStatus status) throws ServiceException;
    List<Cat> takeAllCatsWithDiscount(int userId) throws ServiceException;
    List<Cat> takeCatsByStatusWithDiscount(int userId, CatStatus status) throws ServiceException;
    void addCat(Cat cat) throws ServiceException;
    Cat takeSingleCatWithDiscount(int catId, int userId) throws ServiceException;
    Cat takeSingleCat(int catId) throws ServiceException;
    void deleteCat(int catId) throws ServiceException;
    void editCat(Cat cat) throws ServiceException;
}
