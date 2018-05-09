package by.epam.buhai.airline.entity.comparator;

import by.epam.buhai.airline.entity.Plane;
import java.util.Comparator;

public final class PlaneComparator implements Comparator<Plane> {
    private static final PlaneComparator planeComparator = new PlaneComparator();
    private static SortingParameters sortingParameter;

    private PlaneComparator() {}


    public static PlaneComparator getPlaneComparator(SortingParameters parameters) {
        sortingParameter = parameters;
        return planeComparator;
    }

    @Override
    public int compare(Plane plane1, Plane plane2) {
        int result = 0;

        switch (sortingParameter) {
            case RANGE:
                result = Integer.compare(plane1.getRangeKm(), plane2.getRangeKm());
                break;
        }
        return result;
    }
}
