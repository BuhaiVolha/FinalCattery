package by.epam.buhai.airline.dao;

import by.epam.buhai.airline.dao.dto.DTO;
import by.epam.buhai.airline.entity.Plane;
import java.util.Map;

public abstract class Creator {
    public abstract Plane createPlaneAndParameterize(String type, DTO parameters);
    public abstract DTO createDTO(String planeType, Map<String, String> parameters);
}
