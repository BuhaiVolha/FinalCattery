package by.epam.buhai.airline.service.impl;

import by.epam.buhai.airline.dao.AirlineDAO;
import by.epam.buhai.airline.dao.DAOFactory;
import by.epam.buhai.airline.entity.Plane;
import by.epam.buhai.airline.service.AirlineService;
import java.util.List;

public class AirlineServiceImpl implements AirlineService {

    @Override
    public List<Plane> createPlaneList() {

        DAOFactory factory = DAOFactory.getInstance();
        AirlineDAO goodsDAO = factory.getAirlineDAO();

        return goodsDAO.createPlaneList();
    }
}
