package by.epam.cattery.service;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.LocaleLang;
import by.epam.cattery.entity.Review;
import by.epam.cattery.entity.dto.CatDetail;
import by.epam.cattery.entity.dto.LocalizedCat;
import by.epam.cattery.entity.dto.SearchCatTO;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;

import java.util.List;

public interface CatService {
    List<Cat> takeAllCats(LocaleLang localeLang, int page, int itemsPerPage) throws ServiceException;
    int getCatsPageCount(int itemsPerPage) throws ServiceException;
    List<Cat> takeAllCatsByStatus(LocaleLang localeLang, CatStatus status, int page, int itemsPerPage) throws ServiceException;
    int getCatsPageCountByStatus(CatStatus status, int itemsPerPage) throws ServiceException;
    int addCat(LocalizedCat cat) throws ServiceException;
    void addOfferedCat(LocalizedCat cat, int offerId) throws ServiceException;
    Cat takeSingleCat(int catId, LocaleLang localeLang) throws ServiceException;
    CatDetail getCatDetailsWithLocalization(int catId, LocaleLang localeLang) throws ServiceException;
    void deleteCat(int catId) throws ServiceException;
    void editCat(LocalizedCat cat) throws ServiceException;
    void searchForCat(SearchCatTO searchCatTO, int page) throws ServiceException;
    void getPageCountForFoundCats(SearchCatTO searchCatTO) throws ServiceException;
    void addCatPhoto(int catId, String photo) throws ServiceException;
}
