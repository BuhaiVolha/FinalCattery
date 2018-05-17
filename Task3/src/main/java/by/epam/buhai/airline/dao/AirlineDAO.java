package by.epam.buhai.airline.dao;

import by.epam.buhai.airline.dao.dao_exception.ReadingFileFailedException;
import by.epam.buhai.airline.entity.Plane;
import java.util.List;

public interface AirlineDAO {
    List<Plane> createPlaneList() throws ReadingFileFailedException;
}
