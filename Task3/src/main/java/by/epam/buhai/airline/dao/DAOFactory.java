package by.epam.buhai.airline.dao;

import by.epam.buhai.airline.dao.impl.AirlineDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final AirlineDAO airlineDAO = new AirlineDAOImpl();

    private DAOFactory() {
    }

    public AirlineDAO getAirlineDAO() {
        return airlineDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}
