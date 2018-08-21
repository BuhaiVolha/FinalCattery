package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.connection.ConnectionProvider;
import by.epam.cattery.dao.CatDAO;
import by.epam.cattery.dao.OfferDAO;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.LocaleLang;
import by.epam.cattery.entity.OfferStatus;

import by.epam.cattery.entity.dto.CatDetail;
import by.epam.cattery.entity.dto.LocalizedCat;
import by.epam.cattery.entity.dto.SearchCatTO;

import by.epam.cattery.service.CatService;
import by.epam.cattery.service.exception.InvalidDateException;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;
import by.epam.cattery.service.util.PageCounter;
import by.epam.cattery.service.validation.Validator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;


/**
 * {@inheritDoc}
 *
 */
public class CatServiceImpl implements CatService {
    private static final Logger logger = LogManager.getLogger(CatServiceImpl.class);

    private static DAOFactory daoFactory = DAOFactory.getInstance();

    private static CatDAO catDAO = daoFactory.getCatDAO();
    private static OfferDAO offerDAO = daoFactory.getOfferDAO();

    /**
     * {@inheritDoc}
     *
     * The list will be empty if no cats has been found
     *
     */
    @Override
    public List<Cat> takeAllCats(LocaleLang localeLang, int page, int itemsPerPage) throws ServiceException {
        List<Cat> cats;

        try {
            cats = catDAO.loadAllCats(localeLang, page, itemsPerPage);

            if (cats.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception while finding all cats", e);
        }
        return cats;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int getCatsPageCount(int itemsPerPage) throws ServiceException {
        int pageCount;

        try {
            int totalCount = catDAO.getTotalCount();
            pageCount = PageCounter.getInstance().countPages(totalCount, itemsPerPage);

        } catch (DAOException e) {
            throw new ServiceException("Exception while getting all cats counted", e);
        }
        return pageCount;
    }


    /**
     * {@inheritDoc}
     *
     * The list will be empty if no cats has been found
     *
     */
    @Override
    public List<Cat> takeAllCatsByStatus(LocaleLang localeLang, CatStatus status, int page, int itemsPerPage) throws ServiceException {
        List<Cat> cats;

        try {
            cats = catDAO.loadAllCatsByStatus(localeLang, status, page, itemsPerPage);

            if (cats.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception while finding all cats by status", e);
        }
        return cats;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int getCatsPageCountByStatus(CatStatus status, int itemsPerPage) throws ServiceException {
        int pageCount;

        try {
            int totalCount = catDAO.getTotalCountByStatus(status.toString());
            pageCount = PageCounter.getInstance().countPages(totalCount, itemsPerPage);

        } catch (DAOException e) {
            throw new ServiceException("Exception while getting cats counted by status", e);
        }
        return pageCount;
    }


    /**
     * {@inheritDoc}
     *
     * The implementation checks if cat's birth date is valid otherwise {@link InvalidDateException} will be thrown
     *
     * The implementation checks if input data are valid otherwise {@link ValidationFailedException} will be thrown
     *
     */
    @Override
    public int addCat(LocalizedCat cat) throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        if (!Validator.getInstance().validateCatBirthDate(cat.getAge())) {
            throw new InvalidDateException("Cat birth date is invalid!");
        }

        if (!Validator.getInstance().validateCatInputData(cat)) {
            throw new ValidationFailedException("User's data are invalid!");
        }

        try {
            connectionProvider.startTransaction();

            int catId = catDAO.saveLocalizedCat(cat);
            catDAO.saveLocalizedCatDetails(catId, cat.getCatDetailsWithLocalization());

            connectionProvider.commitTransaction();

            return catId;

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Exception while adding a cat", e);

        } finally {
            connectionProvider.endTransaction();
        }
    }


    /**
     * {@inheritDoc}
     *
     * The implementation checks if cat's birth date is valid otherwise {@link InvalidDateException} will be thrown
     *
     * The implementation checks if input data are valid otherwise {@link ValidationFailedException} will be thrown
     *
     */
    @Override
    public void editCat(LocalizedCat cat) throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        if (!Validator.getInstance().validateCatBirthDate(cat.getAge())) {
            throw new InvalidDateException("Cat birth date is invalid!");
        }

        if (!Validator.getInstance().validateCatInputData(cat)) {
            throw new ValidationFailedException("User's data are invalid!");
        }

        try {
            connectionProvider.startTransaction();

            catDAO.updateLocalizedCat(cat);
            catDAO.updateLocalizedCatDetails(cat.getId(), cat.getCatDetailsWithLocalization());

            connectionProvider.commitTransaction();

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Exception while editing a cat", e);

        } finally {
            connectionProvider.endTransaction();
        }
    }


    /**
     * {@inheritDoc}
     *
     * The implementation checks if cat's birth date is valid otherwise {@link InvalidDateException} will be thrown
     *
     * The implementation checks if input data are valid otherwise {@link ValidationFailedException} will be thrown
     *
     * The implementation also checks if the offer was approved otherwise {@link DAOException} will be thrown
     *
     */
    @Override
    public void addOfferedCat(LocalizedCat cat, int offerId) throws ServiceException {

        if (!Validator.getInstance().validateCatBirthDate(cat.getAge())) {
            throw new InvalidDateException("Cat birth date is invalid!");
        }

        if (!Validator.getInstance().validateCatInputData(cat)) {
            throw new ValidationFailedException("User's data are invalid!");
        }

        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
            connectionProvider.startTransaction();

            int catId = catDAO.saveLocalizedCat(cat);
            catDAO.saveLocalizedCatDetails(catId, cat.getCatDetailsWithLocalization());
            logger.log(Level.DEBUG, "cat added");

            if (offerDAO.checkStatus(offerId, OfferStatus.APRVD.toString())) {
                offerDAO.updateStatusById(OfferStatus.SENT.toString(), offerId);
                logger.log(Level.DEBUG, "offer's status changed");
            }

            connectionProvider.commitTransaction();

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Exception while adding a cat", e);

        } finally {
            connectionProvider.endTransaction();
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void searchForCat(SearchCatTO searchCatTO, int page) throws ServiceException {

        try {
            catDAO.searchForCat(searchCatTO, page);

        } catch (DAOException e) {
            throw new ServiceException("Exception while searching for a cat", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void getPageCountForFoundCats(SearchCatTO searchCatTO) throws ServiceException {

        try {
            catDAO.getTotalCountForFoundCats(searchCatTO);

        } catch (DAOException e) {
            throw new ServiceException("Exception while counting found cats", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Cat takeSingleCat(int catId, LocaleLang localeLang) throws ServiceException {

        try {
            return catDAO.getCatById(catId, localeLang);

        } catch (DAOException e) {
            throw new ServiceException("Exception while finding single cat", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public CatDetail getCatDetailsWithLocalization(int catId, LocaleLang localeLang) throws ServiceException {

        try {
            return catDAO.getCatDetail(catId, localeLang);

        } catch (DAOException e) {
            throw new ServiceException("Exception while finding cat's details", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void deleteCat(int catId) throws ServiceException {

        try {
            catDAO.delete(catId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while deleting cat", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void addCatPhoto(int catId, String photo) throws ServiceException {

        try {
            catDAO.updatePhoto(catId, photo);

        } catch (DAOException e) {
            throw new ServiceException("Exception while adding photo for a cat", e);
        }
    }
}
