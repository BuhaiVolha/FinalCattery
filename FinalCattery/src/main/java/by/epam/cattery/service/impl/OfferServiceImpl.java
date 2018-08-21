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


/**
 * {@inheritDoc}
 *
 */
public class OfferServiceImpl implements OfferService {
    private static DAOFactory daoFactory = DAOFactory.getInstance();
    private static OfferDAO offerDAO = daoFactory.getOfferDAO();


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int offerCat(Offer offer) throws ServiceException {

        try {
            return offerDAO.saveAndReturnId(offer);

        } catch (DAOException e) {
            throw new ServiceException("Offering cat failed", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public boolean offerBelongsToUser(int userId, int offerId) throws ServiceException {

        try {
            return offerDAO.checkOfferBelongsToUser(userId, offerId);

        } catch (DAOException e) {
            throw new ServiceException("Checking whether user can add photo to offer failed", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void addPhotoToOffer(int offerId, String photo) throws ServiceException {

        try {
            offerDAO.updatePhoto(offerId, photo);

        } catch (DAOException e) {
            throw new ServiceException("Exception while adding photo for an offer", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     * The list will be empty if no offers for this user has been found
     *
     */
    @Override
    public List<Offer> takeAllOffersForUser(int userId, int page, int itemsPerPage) throws ServiceException {
        List<Offer> offers;

        try {
            offers = offerDAO.loadAllById(userId, page, itemsPerPage);

            if (offers.isEmpty()) {
                return Collections.emptyList();
            }

        } catch (DAOException e) {
            throw new ServiceException("Taking all offers by Id failed", e);
        }
        return offers;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int getOffersPageCountByUserId(int userId, int itemsPerPage) throws ServiceException {
        int pageCount;

        try {
            int totalCount = offerDAO.getTotalCountById(userId);
            pageCount = PageCounter.getInstance().countPages(totalCount, itemsPerPage);

        } catch (DAOException e) {
            throw new ServiceException("Exception while getting offers counted by Id", e);
        }
        return pageCount;
    }


    /**
     * {@inheritDoc}
     *
     * The list will be empty if no offers by status has been found
     *
     */
    @Override
    public List<Offer> takeAllOffersByStatus(OfferStatus status, int page, int itemsPerPage) throws ServiceException {
        List<Offer> offers;

        try {
            offers = offerDAO.loadAllByStatus(status.toString(), page, itemsPerPage);

            if (offers.isEmpty()) {
                return Collections.emptyList();
            }

        } catch (DAOException e) {
            throw new ServiceException("Taking all offers by status failed", e);
        }
        return offers;
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public int getOffersPageCountByStatus(OfferStatus status, int itemsPerPage) throws ServiceException {
        int pageCount;

        try {
            int totalCount = offerDAO.getTotalCountByStatus(status.toString());
            pageCount = PageCounter.getInstance().countPages(totalCount, itemsPerPage);

        } catch (DAOException e) {
            throw new ServiceException("Exception while getting offers counted by status", e);
        }
        return pageCount;
    }


    /**
     * {@inheritDoc}
     *
     * The implementation checks if the offer has required status otherwise {@link DAOException} will be thrown
     *
     */
    @Override
    public void answerToOffer(Offer offer, OfferStatus statusToCheck) throws ServiceException {

        try {
            if (offerDAO.checkStatus(offer.getId(), statusToCheck.toString())) {
                offerDAO.update(offer);
            }

        } catch (DAOException e) {
            throw new ServiceException("Answering to offer failed", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public Offer takeSingleOffer(int id) throws ServiceException {

        try {
            return offerDAO.getById(id);

        } catch (DAOException e) {
            throw new ServiceException("Exception while taking single offer", e);
        }
    }


    /**
     * {@inheritDoc}
     *
     */
    @Override
    public void deleteOffer(int offerId) throws ServiceException {

        try {
            offerDAO.delete(offerId);

        } catch (DAOException e) {
            throw new ServiceException("Exception while deleting offer", e);
        }
    }
}
