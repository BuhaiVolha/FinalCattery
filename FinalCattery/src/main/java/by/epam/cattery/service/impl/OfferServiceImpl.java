package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.mysql.OfferDAO;

import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;

import by.epam.cattery.service.OfferService;
import by.epam.cattery.service.exception.ServiceException;

import java.util.List;

public class OfferServiceImpl implements OfferService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static OfferDAO offerDAO = daoFactory.getOfferDAO();


    @Override
    public int offerCat(Offer offer) throws ServiceException {

        try {
            return offerDAO.saveAndReturnId(offer);

        } catch (DAOException e) {
            throw new ServiceException("Offering kitten failed in Service", e);
        }
    }


    @Override
    public void addPhotoToOffer(int offerId, String photo) throws ServiceException {
        try {
            offerDAO.addPhoto(offerId, photo);

        } catch (DAOException e) {
            throw new ServiceException("Exception while adding photo for an offer", e);
        }
    }


    @Override
    public List<Offer> takeAllOffersByUserId(int id) throws ServiceException {

        try {
            return offerDAO.loadAllById(id);

        } catch (DAOException e) {
            throw new ServiceException("Showing all offers failed in Service", e);
        }
    }


    @Override
    public List<Offer> takeAllOffersByStatus(OfferStatus status) throws ServiceException {

        try {
            return offerDAO.loadAllByStatus(status.toString());

        } catch (DAOException e) {
            throw new ServiceException("Showing all offers by status failed", e);
        }
    }


    @Override
    public void answerToOffer(Offer offer, OfferStatus statusToCheck) throws ServiceException {

        try {
            if (offerDAO.checkStatus(offer.getId(), statusToCheck.toString())) {
                offerDAO.update(offer);
            }

        } catch (DAOException e) {
            throw new ServiceException("answering to offer failed", e);
        }
    }

    @Override
    public Offer takeSingleOffer(int id) throws ServiceException {

        try {
            return offerDAO.getById(id);

        } catch (DAOException e) {
            throw new ServiceException("Exception while takeSingleOffer", e);
        }
    }


    @Override
    public void deleteOffer(int offerId) throws ServiceException {

        try {
            offerDAO.delete(offerId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while takeSingleOffer", e);
        }
    }
}
