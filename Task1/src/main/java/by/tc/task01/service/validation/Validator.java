package by.tc.task01.service.validation;

import by.tc.task01.entity.criteria.Criteria;
import java.util.Map;

public class Validator {
    private static final String STRING_VALUES = "OS,WAND_TYPE,FILTER_TYPE,BAG_TYPE," +
            "COLOR,FREQUENCY_RANGE,TITLE,AUTHOR,SUBJECT,PERIODICITY,PAID_OR_FREE";

    public static <E> boolean validateCriteria(Criteria<E> criteria) {

        if (criteria == null) {
            return false;
        }
        Map<String, String> criteriaMap = criteria.getCriteria();

        for (Map.Entry<String, String> criterion : criteriaMap.entrySet()) {

            if (checkIfValueIsEmpty(criterion.getValue())) {
                return false;
            }

            if (checkIfValueMustBeNumber(criterion.getKey())) {
                double mustBeNumber;

                try {
                    mustBeNumber = Double.parseDouble(criterion.getValue());
                } catch (NumberFormatException e) {
                    return false;
                }

                if (checkIfNegative(mustBeNumber)) {
                    return false;
                }
            }
        }
        return true;
    }


    private static boolean checkIfValueIsEmpty(String value) {
        return value.isEmpty();
    }


    private static boolean checkIfValueMustBeNumber(String parameter) {
        return !STRING_VALUES.contains(parameter);
    }


    private static boolean checkIfNegative(double number) {
        return number < 0;
    }
}