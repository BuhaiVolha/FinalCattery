package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.connection.ConnectionProvider;
import by.epam.cattery.dao.CatDAO;
import by.epam.cattery.dao.OfferDAO;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.entity.OfferStatus;

import by.epam.cattery.service.CatService;
import by.epam.cattery.service.exception.ServiceException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.List;

public class CatServiceImpl implements CatService {
    private static final Logger logger = LogManager.getLogger(CatServiceImpl.class);

    private static DAOFactory daoFactory = DAOFactory.getInstance();

    private static CatDAO catDAO = daoFactory.getCatDAO();
    private static OfferDAO offerDAO = daoFactory.getOfferDAO();


    @Override
    public List<Cat> takeAllCats() throws ServiceException {
        List<Cat> cats;

        try {
            cats = catDAO.loadAll();

            if (cats.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception while finding all cats", e);
        }
        return cats;
    }


    @Override
    public List<Cat> takeCatsByStatus(CatStatus status) throws ServiceException {
        List<Cat> cats;

        try {
            cats = catDAO.loadAllByStatus(status.toString());

            if (cats.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception while finding all cats by status", e);
        }
        return cats;
    }


    @Override
    public void addCat(Cat cat) throws ServiceException {

        try {
            catDAO.save(cat);

        } catch (DAOException e) {
            throw new ServiceException("Exception while adding a cat", e);
        }
    }


    @Override
    public void addOfferedCat(Cat cat, int offerId) throws ServiceException {
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();

        try {
            connectionProvider.startTransaction();

            catDAO.save(cat);
            logger.log(Level.DEBUG, "cat added");

            if (offerDAO.checkStatus(offerId, OfferStatus.APRVD.toString())) {
                offerDAO.updateStatusById(OfferStatus.SENT.toString(), offerId);
                logger.log(Level.DEBUG, "offer's status changed");
            }

            connectionProvider.commitTransaction();

        } catch (DAOException e) {
            connectionProvider.abortTransaction();
            throw new ServiceException("Exception while adding a cat", e);

        }  finally {
            connectionProvider.endTransaction();
        }
    }


    @Override
    public Cat takeSingleCat(int catId) throws ServiceException {

        try {
            return catDAO.getById(catId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while finding single cat", e);
        }
    }


    @Override
    public void deleteCat(int catId) throws ServiceException {
        try {
            catDAO.delete(catId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while deleting cat", e);
        }
    }


    @Override
    public void editCat(Cat cat) throws ServiceException {
        try {
            catDAO.update(cat);

        } catch (DAOException e) {
            throw new ServiceException("Exception while editing cat", e);
        }
    }


    @Override
    public void addCatPhoto(int catId, String photo) throws ServiceException {
        try {
            catDAO.updatePhoto(catId, photo);

        } catch (DAOException e) {
            throw new ServiceException("Exception while adding photo for a cat", e);
        }
    }


    @Override
    public List<Cat> searchForCat(Cat cat) throws ServiceException {
        List<Cat> cats;

        try {
            cats = catDAO.searchForCat(cat);

            if (cats.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception while searching for cat", e);
        }
        return cats;
    }
}
