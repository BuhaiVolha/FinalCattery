package by.epam.cattery.service;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.LocaleLang;
import by.epam.cattery.entity.dto.CatDetail;
import by.epam.cattery.entity.dto.LocalizedCat;
import by.epam.cattery.entity.dto.SearchCatTO;

import by.epam.cattery.service.exception.ServiceException;

import java.util.List;


/**
 * Defines methods to work with cat data.
 *
 */
public interface CatService {


    /**
     * Finds all cats taking into account pagination and locale.
     *
     * @param localeLang   the locale - {@link LocaleLang}
     * @param page         the current page
     * @param itemsPerPage the number of items per page
     * @return {@link Cat} objects as a list
     * @throws ServiceException if exception in DAO occurred
     *
     */
    List<Cat> takeAllCats(LocaleLang localeLang, int page, int itemsPerPage) throws ServiceException;


    /**
     * Counts pages for cats.
     *
     * @param itemsPerPage the items per page
     * @return the total page count
     * @throws ServiceException if exception in DAO occurred
     *
     */
    int getCatsPageCount(int itemsPerPage) throws ServiceException;


    /**
     * Gathers all cats by status into a list.
     *
     * @param localeLang   the locale - {@link LocaleLang}
     * @param status       the {@link CatStatus}
     * @param page         the current page
     * @param itemsPerPage the items per page
     * @return the list of {@link Cat} by their status
     * @throws ServiceException if exception in DAO occurred
     *
     */
    List<Cat> takeAllCatsByStatus(LocaleLang localeLang, CatStatus status, int page, int itemsPerPage) throws ServiceException;


    /**
     * Counts pages for cats by their status.
     *
     * @param status       the {@link CatStatus}
     * @param itemsPerPage the items per page
     * @return the total page count
     * @throws ServiceException if exception in DAO occurred
     *
     */
    int getCatsPageCountByStatus(CatStatus status, int itemsPerPage) throws ServiceException;


    /**
     * Adds the cat and returns it's id from the database.
     *
     * @param cat the cat
     * @return the id from the database
     * @throws ServiceException if exception in DAO occurred or the data input was invalid
     *
     */
    int addCat(LocalizedCat cat) throws ServiceException;


    /**
     * Adds offered cat.
     *
     * @param cat     the object which contains data from the approved offer
     * @param offerId the offer which cat is being added
     * @throws ServiceException if exception in DAO occurred or the data input was invalid
     *
     */
    void addOfferedCat(LocalizedCat cat, int offerId) throws ServiceException;


    /**
     * Takes single cat by {@code catId}.
     *
     * @param catId      the cat's id
     * @param localeLang the locale - {@link LocaleLang}
     * @return {@link Cat}
     * @throws ServiceException if exception in DAO occurred
     *
     */
    Cat takeSingleCat(int catId, LocaleLang localeLang) throws ServiceException;


    /**
     * Gets cat data that require localization.
     *
     * @param catId      the cat's id
     * @param localeLang the locale - {@link LocaleLang}
     * @return {@link CatDetail} - an object that contains data that require localization
     * @throws ServiceException if exception in DAO occurred
     *
     */
    CatDetail getCatDetailsWithLocalization(int catId, LocaleLang localeLang) throws ServiceException;


    /**
     * Deletes the cat by {@code catId}.
     *
     * @param catId the cat's id
     * @throws ServiceException if exception in DAO occurred
     *
     */

    void deleteCat(int catId) throws ServiceException;


    /**
     * Edits the cat.
     *
     * @param cat the cat
     * @throws ServiceException if exception in DAO occurred or the data input was invalid
     *
     */
    void editCat(LocalizedCat cat) throws ServiceException;


    /**
     * Allows to perform search with parameters, which can be left empty.
     *
     * @param searchCatTO - {@link SearchCatTO}, an object that is used when searching cats
     * @param page        the current page
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void searchForCat(SearchCatTO searchCatTO, int page) throws ServiceException;


    /**
     * Gets page count to set it in SearchCatTO - special object for found cats that will be used for pagination.
     *
     * @param searchCatTO - {@link SearchCatTO}, an object that is used when searching cats
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void getPageCountForFoundCats(SearchCatTO searchCatTO) throws ServiceException;


    /**
     * Adds photo for the cat.
     *
     * @param catId the cat's id
     * @param photo the photo's name
     * @throws ServiceException if exception in DAO occurred
     *
     */
    void addCatPhoto(int catId, String photo) throws ServiceException;
}
