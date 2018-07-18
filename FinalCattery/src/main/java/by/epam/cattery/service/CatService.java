package by.epam.cattery.service;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;

import java.util.List;

public interface CatService {
    List<Cat> takeAllCats() throws ServiceException;
    void addCat(Cat cat) throws ServiceException;
    void catAlreadyAdded(int offerId) throws ServiceException, ValidationFailedException; // ?
    Cat takeSingleCatWithDiscount(int catId, int userId) throws ServiceException;
    Cat takeSingleCat(int catId) throws ServiceException;
}
