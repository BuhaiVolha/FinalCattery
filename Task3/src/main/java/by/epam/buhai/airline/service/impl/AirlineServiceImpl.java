package by.epam.buhai.airline.service.impl;

import by.epam.buhai.airline.dao.AirlineDAO;
import by.epam.buhai.airline.dao.DAOFactory;
import by.epam.buhai.airline.entity.Plane;
import by.epam.buhai.airline.service.AirlineService;
import by.epam.buhai.airline.service.service_exception.PlaneListCreationFailedException;

import java.util.List;

public class AirlineServiceImpl implements AirlineService {

    @Override
    public List<Plane> createPlaneList() throws PlaneListCreationFailedException {

        DAOFactory factory = DAOFactory.getInstance();
        AirlineDAO goodsDAO = factory.getAirlineDAO();
        List<Plane> planes;
        try {
            planes = goodsDAO.createPlaneList();
        } catch (final Exception e) {
            throw new PlaneListCreationFailedException(e);
        }

        return planes;
    }
}
