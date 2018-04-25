package by.tc.task01.service.validation;

import by.tc.task01.entity.criteria.Criteria;
import by.tc.task01.exception.*;
import java.util.Map;

public class Validator {
    private static final String STRING_VALUES = "OS,WAND_TYPE,FILTER_TYPE,BAG_TYPE," +
            "COLOR,FREQUENCY_RANGE,TITLE,AUTHOR,SUBJECT,PERIODICITY,PAID_OR_FREE";

    public static <E> void validateCriteria(Criteria<E> criteria) throws ValidationFailedException {

        if (criteria == null) {
            throw new NullCriteriaException("The criteria is null!");
        }
        Map<String, String> criteriaMap = criteria.getCriteria();

        for (Map.Entry<String, String> criterion : criteriaMap.entrySet()) {

            if (checkIfCriterionWithoutStringValues(criterion.getKey())) {
                double number = convertIntoNumber(criterion.getValue());

                checkIfNegative(number);
            }
        }
    }


    private static boolean checkIfCriterionWithoutStringValues(String parameter) {
        return !STRING_VALUES.contains(parameter);
    }


    private static double convertIntoNumber(String mustBeNumber) throws NotNumberException {
        try {
            return Double.parseDouble(mustBeNumber);

        } catch (NumberFormatException e) {
            throw new NotNumberException("The value "
                    + mustBeNumber
                    + " must be a number!");
        }
    }


    private static void checkIfNegative(double number) throws NegativeValueException {
        if (number < 0) {
            throw new NegativeValueException("The value "
                    + number
                    + " is negative!");
        }
    }
}