package by.epam.cattery.service;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.Review;
import by.epam.cattery.entity.dto.SearchCatTO;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;

import java.util.List;

public interface CatService {
    List<Cat> takeAllCats(int page, int itemsPerPage) throws ServiceException;
    int getCatsPageCount(int itemsPerPage) throws ServiceException;
    List<Cat> takeAllCatsByStatus(CatStatus status, int page, int itemsPerPage) throws ServiceException;
    int getCatsPageCountByStatus(CatStatus status, int itemsPerPage) throws ServiceException;
    void addCat(Cat cat) throws ServiceException;
    void addOfferedCat(Cat cat, int offerId) throws ServiceException;
    Cat takeSingleCat(int catId) throws ServiceException;
    void deleteCat(int catId) throws ServiceException;
    void editCat(Cat cat) throws ServiceException;
    void searchForCat(SearchCatTO searchCatTO, int page) throws ServiceException;
    void getPageCountForFoundCats(SearchCatTO searchCatTO) throws ServiceException;
    void addCatPhoto(int catId, String photo) throws ServiceException;
}
