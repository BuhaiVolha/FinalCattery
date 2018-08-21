package by.epam.cattery.dao;

import by.epam.cattery.dao.exception.DAOException;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.LocaleLang;
import by.epam.cattery.entity.dto.CatDetail;
import by.epam.cattery.entity.dto.LocalizedCat;
import by.epam.cattery.entity.dto.SearchCatTO;

import java.util.List;


/**
 * Defines specific methods for Cat DAO.
 *
 */
public interface CatDAO extends GenericDAO<Cat> {

    /**
     * Searches for cat, creates query using parameters from {@code SearchCatTO} taking into account pagination.
     *
     * @param searchCatTO {@link SearchCatTO}
     * @param page        the current page
     * @throws DAOException the dao exception
     *
     */
    void searchForCat(SearchCatTO searchCatTO, int page) throws DAOException;


    /**
     * Counts all found cats which matches the parameters from {@code SearchCatTO}.
     *
     * @param searchCatTO {@link SearchCatTO}
     * @throws DAOException the dao exception
     *
     */
    void getTotalCountForFoundCats(SearchCatTO searchCatTO) throws DAOException;


    /**
     * Sets cats available if their reservations expired.
     *
     * @throws DAOException the dao exception
     *
     */
    void setCatsAvailableIfReservationsExpired() throws DAOException;


    /**
     * Gets cat's id by its reservation's id.
     *
     * @param reservationId the reservation's id
     * @return the cat's id
     * @throws DAOException the dao exception
     *
     */
    int getCatIdByReservationId(int reservationId) throws DAOException;


    /**
     * Sets reserved cats available if user has been banned.
     *
     * @param userId the user's id
     * @throws DAOException the dao exception
     *
     */
    void setCatsAvailableIfUserBanned(int userId) throws DAOException;


    /**
     * Updates localized cat.
     *
     * @param cat {@link LocalizedCat} - contains same for all localizations data
     * @throws DAOException the dao exception
     *
     */
    void updateLocalizedCat(LocalizedCat cat) throws DAOException;


    /**
     * Updates {@link CatDetail} with all localizations for certain cat.
     *
     * If update wasn't made {@link DAOException} will be thrown
     *
     * @param catId      the cat's id
     * @param catDetails {@link CatDetail} - contains translated data for localizations
     * @throws DAOException the dao exception
     *
     */
    void updateLocalizedCatDetails(int catId, List<CatDetail> catDetails) throws DAOException;


    /**
     * Saves localized cat to the database and returns its id.
     *
     * @param cat {@link LocalizedCat} - contains same for all localizations data
     * @return cat's id
     * @throws DAOException the dao exception
     *
     */
    int saveLocalizedCat(LocalizedCat cat) throws DAOException;


    /**
     * Saves {@link CatDetail} with all localizations for certain cat.
     *
     * @param catId      the cat's id
     * @param catDetails {@link CatDetail} - contains translated data for localizations
     * @throws DAOException the dao exception
     *
     */
    void saveLocalizedCatDetails(int catId, List<CatDetail> catDetails) throws DAOException;


    /**
     * Loads all cats as a list taking into account pagination and locale.
     *
     * @param localeLang   {@link LocaleLang} locale
     * @param page         the current page
     * @param itemsPerPage the items per page
     * @return the list of {@link Cat} objects
     * @throws DAOException the dao exception
     *
     */
    List<Cat> loadAllCats(LocaleLang localeLang, int page, int itemsPerPage) throws DAOException;


    /**
     * Loads all cats as a list by status taking into account pagination and locale.
     *
     * @param localeLang   {@link LocaleLang} locale
     * @param catStatus    the {@link CatStatus} by which cats will be fetched from the database
     * @param page         the current page
     * @param itemsPerPage the items per page
     * @return the list of {@link Cat} objects by status
     * @throws DAOException the dao exception
     *
     */
    List<Cat> loadAllCatsByStatus(LocaleLang localeLang, CatStatus catStatus, int page, int itemsPerPage) throws DAOException;


    /**
     * Gets cat by its id and locale.
     *
     * @param catId      the cat's id
     * @param localeLang {@link LocaleLang} locale
     * @return the {@link Cat}
     * @throws DAOException the dao exception
     *
     */
    Cat getCatById(int catId, LocaleLang localeLang) throws DAOException;


    /**
     * Gets certain cat's detail for certain locale.
     *
     * @param catId      the cat's id
     * @param localeLang {@link LocaleLang} locale
     * @return {@link CatDetail} - contains translated data for this locale
     * @throws DAOException the dao exception
     *
     */
    CatDetail getCatDetail(int catId, LocaleLang localeLang) throws DAOException;
}
