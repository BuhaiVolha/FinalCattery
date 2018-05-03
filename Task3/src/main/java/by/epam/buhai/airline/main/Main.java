package by.epam.buhai.airline.main;

import by.epam.buhai.airline.entity.Airplane;
import by.epam.buhai.airline.entity.CommercialSpaceplane;
import by.epam.buhai.airline.entity.Plane;

import static by.epam.buhai.airline.entity.Specification.*;

public class Main {
    public static void main(String[] args) {
        Plane spaceplane = new Airplane("Boeing", Manufacturers.BOEING, 2, 8000, 300, 8, 15, AirplaneTypes.LONG_HAUL,
                AirplaneBodyTypes.NARROW_BODY, AirplaneClassCabinsNumber.FOUR_CABIN_CLASSES);

        System.out.println(spaceplane);
    }
}
