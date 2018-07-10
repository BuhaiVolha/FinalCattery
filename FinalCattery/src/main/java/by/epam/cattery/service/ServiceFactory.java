package by.epam.cattery.service;

import by.epam.cattery.service.impl.CatServiceImpl;
import by.epam.cattery.service.impl.UserServiceImpl;

public class ServiceFactory { //final?
    private static final ServiceFactory instance = new ServiceFactory();
    private static final UserService userService = new UserServiceImpl();
    private static final CatService catService = new CatServiceImpl();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public CatService getCatService() {
        return catService;
    }
}
