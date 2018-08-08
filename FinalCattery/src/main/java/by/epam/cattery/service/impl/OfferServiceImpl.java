package by.epam.cattery.service.impl;

import by.epam.cattery.dao.DAOFactory;
import by.epam.cattery.dao.exception.DAOException;
import by.epam.cattery.dao.OfferDAO;

import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.OfferStatus;

import by.epam.cattery.service.OfferService;
import by.epam.cattery.service.exception.ServiceException;
import by.epam.cattery.service.util.PageCounter;

import java.util.Collections;
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
            offerDAO.updatePhoto(offerId, photo);

        } catch (DAOException e) {
            throw new ServiceException("Exception while adding photo for an offer", e);
        }
    }


//    @Override
////    public List<Offer> takeAllOffersByUserId(int id) throws ServiceException {
////        List<Offer> offers;
////
////        try {
////            offers = offerDAO.loadAllById(id);
////
////            if (offers.isEmpty()) {
////                return Collections.emptyList();
////            }
////
////        } catch (DAOException e) {
////            throw new ServiceException("Showing all offers failed in Service", e);
////        }
////        return offers;
////    }


    @Override
    public List<Offer> takeAllOffersForUser(int userId, int page, int itemsPerPage) throws ServiceException {
        List<Offer> ofers;

        try {
            ofers = offerDAO.loadAllById(userId, page, itemsPerPage);

            if (ofers.isEmpty()) {
                return Collections.emptyList();
            }

        } catch (DAOException e) {
            throw new ServiceException("Showing all offers by Id failed", e);
        }
        return ofers;
    }


    @Override
    public int getOffersPageCountByUserId(int userId, int itemsPerPage) throws ServiceException {
        int pageCount = 0;

        try {
            int totalCount = offerDAO.getTotalCountById(userId);
            pageCount = PageCounter.getInstance().countPages(totalCount, itemsPerPage);

        } catch (DAOException e) {
            throw new ServiceException("Exception while getting offers counted by Id", e);
        }
        return pageCount;
    }


    @Override
    public List<Offer> takeAllOffersByStatus(OfferStatus status, int page, int itemsPerPage) throws ServiceException {
        List<Offer> ofers;

        try {
            ofers = offerDAO.loadAllByStatus(status.toString(), page, itemsPerPage);

            if (ofers.isEmpty()) {
                return Collections.emptyList();
            }

        } catch (DAOException e) {
            throw new ServiceException("Showing all offers by status failed", e);
        }
        return ofers;
    }


    @Override
    public int getOffersPageCountByStatus(OfferStatus status, int itemsPerPage) throws ServiceException {
        int pageCount = 0;

        try {
            int totalCount = offerDAO.getTotalCountByStatus(status.toString());
            pageCount = PageCounter.getInstance().countPages(totalCount, itemsPerPage);

        } catch (DAOException e) {
            throw new ServiceException("Exception while getting offers counted by status", e);
        }
        return pageCount;
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
