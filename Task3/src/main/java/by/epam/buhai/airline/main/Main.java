package by.epam.buhai.airline.main;

import by.epam.buhai.airline.entity.*;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Airline airline = new Airline("MyCompany");
        airline.buyPlanes();
        //airline.showPlanes();

        Comparator<Plane> comparatorByRange = Comparator.comparing(Plane::getRangeKm)
                .thenComparing((o1, o2) -> o1.getName().compareToIgnoreCase(o2.getName()));
        airline.sort(comparatorByRange);
        //airline.showPlanes();

//        List<Plane> planes = airline.findPlaneByFuelConsumption(7, 12000);
         PrintInfo.print(airline.sort(comparatorByRange));
    }
}
