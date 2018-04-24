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
	    Map<String, String> parameters = criteria.getCriteria();

		for (Map.Entry<String, String> parameter : parameters.entrySet()) {

		    if (!STRING_VALUES.contains(parameter.getKey())) {
                try {
                    double numberValue = Double.parseDouble(parameter.getValue());

                    if (numberValue < 0) {
                        throw new NegativeValueException("The value "
                                + numberValue
                                + " is negative!");
                    }
                } catch (NumberFormatException e) {
                    throw new NotNumberException("The value "
                            + parameter.getValue()
                            + " must be a number!");
                }
            }
        }
	}
}