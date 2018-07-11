package by.epam.cattery.service;

import by.epam.cattery.dao.ReviewDAO;
import by.epam.cattery.dao.impl.ReviewDAOimpl;
import by.epam.cattery.service.impl.CatServiceImpl;
import by.epam.cattery.service.impl.OfferServiceImpl;
import by.epam.cattery.service.impl.ReviewServiceImpl;
import by.epam.cattery.service.impl.UserServiceImpl;

public class ServiceFactory { //final?
    private static final ServiceFactory instance = new ServiceFactory();
    private static final UserService userService = new UserServiceImpl();
    private static final CatService catService = new CatServiceImpl();
    private static final ReviewService reviewService = new ReviewServiceImpl();
    private static final OfferService offerService = new OfferServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public CatService getCatService() {
        return catService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public OfferService getOfferService() {
        return offerService;
    }
}
