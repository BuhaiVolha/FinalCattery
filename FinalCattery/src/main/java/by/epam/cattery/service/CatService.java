package by.epam.cattery.service;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;

import java.util.List;

public interface CatService {
    List<Cat> takeAllCats() throws ServiceException;
    List<Cat> takeCatsByStatus(CatStatus status) throws ServiceException;
    void addCat(Cat cat) throws ServiceException;
    void addOfferedCat(Cat cat, int offerId) throws ServiceException;
    Cat takeSingleCat(int catId) throws ServiceException;
    void deleteCat(int catId) throws ServiceException;
    void editCat(Cat cat) throws ServiceException;
    List<Cat> searchForCat(Cat cat) throws ServiceException;
}
