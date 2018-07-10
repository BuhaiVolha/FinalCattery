package by.epam.cattery.service.impl;

import by.epam.cattery.dao.CatDAO;
import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Cat;
import by.epam.cattery.service.CatService;
import by.epam.cattery.service.exception.ServiceException;

import java.util.Collections;
import java.util.List;

public class CatServiceImpl implements CatService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static CatDAO catDAO = daoFactory.getCatDAO();

    @Override
    public List<Cat> showAllCats() throws ServiceException {
        List<Cat> cats;

        try {
            cats = catDAO.findAllCats();

            if (cats.isEmpty()) {
                return Collections.emptyList();
            }
        } catch (DAOException e) {
            System.out.println(e);
            throw new ServiceException("Error while showing cats", e);
        }
        return cats;
    }
}
