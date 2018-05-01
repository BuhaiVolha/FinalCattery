package by.epam.buhai.airline.service;

import by.epam.buhai.airline.service.impl.AirlineServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final AirlineService airlineService = new AirlineServiceImpl();

    private ServiceFactory() {
    }

    public AirlineService getAirlineService() {
        return airlineService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

}
