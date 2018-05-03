package by.epam.buhai.airline.main;

import by.epam.buhai.airline.entity.*;

import static by.epam.buhai.airline.entity.Specification.*;

public class Main {
    public static void main(String[] args) {


        Airline airline = new Airline("MyCompany");
        airline.buyPlanes();
        airline.showPlanes();

    }
}
