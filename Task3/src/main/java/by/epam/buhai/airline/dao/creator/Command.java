package by.epam.buhai.airline.dao.creator;

import by.epam.buhai.airline.dao.dto.DTO;
import by.epam.buhai.airline.entity.Plane;

import java.util.Optional;

public abstract class Command {
    public abstract Plane createPlaneWith(DTO parameters);
}
