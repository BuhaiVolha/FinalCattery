package by.epam.cattery.service.impl;

import by.epam.cattery.dao.CatDAO;
import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.CatStatus;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.exception.ValidationFailedException;

import java.util.Collections;
import java.util.List;

public class CatServiceImpl implements CatService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static CatDAO catDAO = daoFactory.getCatDAO();

    @Override
    public List<Cat> takeAllCats() throws ServiceException {
        List<Cat> cats;

        try {
            cats = catDAO.findAllCats();

            if (cats.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            throw new ServiceException("Error while showing cats", e);
        }
        return cats;
    }


    @Override
    public List<Cat> takeCatsByStatus(CatStatus status) throws ServiceException {
        List<Cat> cats;

        try {
            cats = catDAO.findAllCatsByStatus(status);

            if (cats.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception while taking cats by status", e);
        }
        return cats;
    }

    @Override
    public List<Cat> takeAllCatsWithDiscount(int userId) throws ServiceException {
        List<Cat> cats;

        try {
            cats = catDAO.findAllCatsWithDiscount(userId);

            if (cats.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception while taking cats with discount", e);
        }
        return cats;
    }


    @Override
    public List<Cat> takeCatsByStatusWithDiscount(int userId, CatStatus status) throws ServiceException {
        List<Cat> cats;

        try {
            cats = catDAO.findAllCatsByStatusWithDiscount(userId, status);

            if (cats.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception while taking cats with discount by status", e);
        }
        return cats;
    }


    @Override
    public void addCat(Cat cat) throws ServiceException {

        try {
            catDAO.addCat(cat);

        } catch (DAOException e) {
            throw new ServiceException("Exception while adding cats", e);
        }
    }


    @Override
    public Cat takeSingleCatWithDiscount(int catId, int userId) throws ServiceException {

        try {
            return catDAO.findSingleCatWithDiscount(catId, userId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while showing single cat with discount", e);
        }
    }


    @Override
    public Cat takeSingleCat(int catId) throws ServiceException {

        try {
            return catDAO.findSingleCat(catId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while showing single cat", e);
        }
    }


    @Override
    public void deleteCat(int catId) throws ServiceException {
        try {
            catDAO.deleteCat(catId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while deleting cat", e);
        }
    }


    @Override
    public void editCat(Cat cat) throws ServiceException {
        try {
            catDAO.updateCat(cat);

        } catch (DAOException e) {
            throw new ServiceException("Exception while editing cat", e);
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


    @Override
    public List<Cat> searchForCatWithDiscount(Cat cat, int userId) throws ServiceException {
        List<Cat> cats;

        try {
            cats = catDAO.searchForCatWithDiscount(cat, userId);

            if (cats.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            throw new ServiceException("Exception while searching for cat with discount", e);
        }
        return cats;
    }
}
