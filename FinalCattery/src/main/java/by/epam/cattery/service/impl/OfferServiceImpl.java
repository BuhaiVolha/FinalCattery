package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.OfferDAO;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.entity.Offer;
import by.epam.cattery.service.OfferService;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;

public class OfferServiceImpl implements OfferService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static OfferDAO offerDAO = daoFactory.getOfferDAO();

    @Override
    public boolean offerKitten(Offer offer) throws ServiceException {

        try {
            return offerDAO.makeOffer(offer);

        } catch (DAOException e) {
            System.out.println(e);
            throw new ServiceException("Offering kitten failed in Service", e);
        }
    }


    @Override
    public List<Offer> showAllOffersByUserId(String id) throws ServiceException {
        try {
            return offerDAO.findAllOffersByUserId(id);

        } catch (DAOException e) {
            System.out.println(e);
            throw new ServiceException("Showing all offers failed in Service", e);
        }
    }


    @Override
    public List<Offer> showAllOffersByStatus(String status) throws ServiceException {
        try {
            return offerDAO.findAllOffersByStatus(status);

        } catch (DAOException e) {
            System.out.println(e);
            throw new ServiceException("Showing all offers by status failed in Service", e);
        }
    }

    @Override
    public void declineOffer(String id, String expertMessage, String status) throws ServiceException  {
        try {
            offerDAO.declineOffer(id, expertMessage, status); // Отдельный объект

        } catch (DAOException e) {
            System.out.println(e);
            throw new ServiceException("declineOffer failed in Service", e);
        }
    }
}
