package by.epam.cattery.service;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;

public interface CatService {
    List<Cat> showAllCats() throws ServiceException;
    void addUserCat(Cat cat, String id) throws ServiceException;
}
