package by.epam.buhai.airline.service;

import by.epam.buhai.airline.entity.Plane;
import by.epam.buhai.airline.service.service_exception.PlaneListCreationFailedException;

import java.util.List;

public interface AirlineService {
    List<Plane> createPlaneList() throws PlaneListCreationFailedException;
}
