package by.epam.cattery.service;

import by.epam.cattery.service.impl.*;

public class ServiceFactory { //final?
    private static final ServiceFactory instance = new ServiceFactory();
    private static final UserService userService = new UserServiceImpl();
    private static final CatService catService = new CatServiceImpl();
    private static final ReviewService reviewService = new ReviewServiceImpl();
    private static final OfferService offerService = new OfferServiceImpl();
    private static final ReservationService reservationService = new ReservationServiceImpl();

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

    public ReservationService getReservationService() {
        return reservationService;
    }
}
