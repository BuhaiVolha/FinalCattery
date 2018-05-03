package by.epam.buhai.airline.entity;

public abstract class Specification {
    public enum PlaneTypes {
        BUSINESS_JET, FREIGHTER, COMMERCIAL_SPACEPLANE, AIRPLANE
    }

    public enum Manufacturers {
        AIRBUS, ANTONOV, ATR, BOEING, COMAC, EMBRAER, DOUGLAS,  THE_SPACESHIP_COMPANY
    }

    public enum SpaceplaneTypes {
        ROCKET_POWERED, VERTICAL_TAKEOFF_HORIZONTAL_LANDING
    }

    public enum AirplaneTypes {
        LONG_HAUL, SHORT_HAUL
    }

    public enum AirplaneBodyTypes {
        WIDE_BODY, NARROW_BODY
    }

    public enum AirplaneClassCabinsNumber {
        ONE_CABIN_CLASS, TWO_CABIN_CLASSES, THREE_CABIN_CLASSES, FOUR_CABIN_CLASSES
    }

    public enum BusinessJetTypes {
        COMPACT_LIGHT, SMALL_CABIN, SUPER_LIGHT, MIDSIZE_CABIN, SUPER_MIDSIZE_CABIN,
        LARGE_CABIN, BIZLINER
    }

    public enum CargoPlaneTypes {
        PASSENGER_AIRPLANES_DERIVATIVE, DEDICATED_CIVILIAN, JOINT_CIVIL_MILITARY
    }

}
