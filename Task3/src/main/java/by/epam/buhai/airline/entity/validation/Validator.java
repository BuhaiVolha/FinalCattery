package by.epam.buhai.airline.entity.validation;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Validator {
    private static final Logger LOGGER = LogManager.getLogger(Validator.class);

    public static boolean checkDiapasonInvalid(int firstNum, int secondNum) {
        boolean valid = (firstNum > 0)
                && (secondNum > 0)
                && (secondNum > firstNum);
        if (!valid) {
            LOGGER.log(Level.WARN, "Fuel diapason is invalid");
        }
        return valid;
    }
}
