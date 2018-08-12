package by.epam.cattery.service.validation;

import by.epam.cattery.entity.Cat;
import by.epam.cattery.entity.Review;
import by.epam.cattery.entity.User;

import org.apache.commons.validator.GenericValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Validator {
    private static final Logger logger = LogManager.getLogger(Validator.class);

    private final static Validator instance = new Validator();

    private static final String VALID_LOGIN_REGEX = "[0-9a-zA-Zа-яА-Я]{2,10}";
    private static final String VALID_NAME_AND_LASTNAME_REGEX = "[a-zA-Zа-яА-Я]{2,20}";
    private static final String VALID_PASSWORD_REGEX = ".{7,15}";

    private static final String VALID_CAT_NAME_REGEX = "[0-9a-zA-Zа-яА-Я]{2,20}";
    private static final String VALID_CAT_LASTNAME_REGEX = "[a-zA-Zа-яА-Я]{2,20}(\\s[a-zA-Zа-яА-Я]{0,20})?";
    private static final String VALID_CAT_PARENT_REGEX = "[a-zA-Zа-яА-Я]{2,20}\\s[a-zA-Zа-яА-Я]{0,20}(\\s[a-zA-Zа-яА-Я]{0,20})?";

    private static final String VALID_BIRTHDAY_DATE_FORMAT = "dd/MM/yyyy";

    private static final int MIN_STAR_COUNT = 1;
    private static final int MAX_STAR_COUNT = 5;

    private static final int MIN_CAT_PRICE = 10;
    private static final int MAX_CAT_PRICE = 2000;


    private Validator() {}

    public boolean validateRegistrationInputData(User user) {

        return !GenericValidator.isBlankOrNull(user.getLogin())
                && user.getLogin().matches(VALID_LOGIN_REGEX)
                && !GenericValidator.isBlankOrNull(user.getPassword())
                && user.getPassword().matches(VALID_PASSWORD_REGEX)
                && !GenericValidator.isBlankOrNull(user.getName())
                && user.getName().matches(VALID_NAME_AND_LASTNAME_REGEX)
                && !GenericValidator.isBlankOrNull(user.getLastname())
                && user.getLastname().matches(VALID_NAME_AND_LASTNAME_REGEX)
                && !GenericValidator.isBlankOrNull(user.getLastname())
                && GenericValidator.isEmail(user.getEmail());
    }

    public boolean validateLoginInputData(String login, String password) {

        return !GenericValidator.isBlankOrNull(login)
                && login.matches(VALID_LOGIN_REGEX)
                && !GenericValidator.isBlankOrNull(password)
                && password.matches(VALID_PASSWORD_REGEX);
    }


    public boolean validateReviewInputData(Review review) {

        return GenericValidator.isInRange(review.getStarsCount(), MIN_STAR_COUNT, MAX_STAR_COUNT)
                && !GenericValidator.isBlankOrNull(review.getText());
    }


    public boolean validateCatInputData(Cat cat) {

        return !GenericValidator.isBlankOrNull(cat.getName())
                && cat.getName().matches(VALID_CAT_NAME_REGEX)
                && !GenericValidator.isBlankOrNull(cat.getLastname())
                && cat.getLastname().matches(VALID_CAT_LASTNAME_REGEX)
                && !GenericValidator.isBlankOrNull(cat.getFemaleParent())
                && cat.getFemaleParent().matches(VALID_CAT_PARENT_REGEX)
                && !GenericValidator.isBlankOrNull(cat.getMaleParent())
                && cat.getMaleParent().matches(VALID_CAT_PARENT_REGEX)
                && GenericValidator.isInRange(cat.getPrice(), MIN_CAT_PRICE, MAX_CAT_PRICE);
    }


    public boolean validateCatBirthDate(String birthDate) {

        if (!GenericValidator.isBlankOrNull(birthDate)) {

            try {
                SimpleDateFormat formatter = new SimpleDateFormat(VALID_BIRTHDAY_DATE_FORMAT, Locale.getDefault());
                Date date = formatter.parse(birthDate);

                return GenericValidator.isDate(birthDate, VALID_BIRTHDAY_DATE_FORMAT, true)
                        && date.before(new Date());

            } catch (ParseException e) {
                logger.log(Level.WARN, "Parsing date of cat's birthday failed ", e);
            }
        }
        return false;
    }


    public static Validator getInstance() {
        return instance;
    }
}
