package by.epam.buhai.airline.service;

import by.epam.buhai.airline.entity.Plane;

import java.util.List;

public interface AirlineService {
    //<E> List<Goods> find(Criteria<E> criteria) throws TaskException;
    List<Plane> createPlaneList();
}
