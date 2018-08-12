package by.epam.cattery.service.validation;

import by.epam.cattery.entity.Offer;
import by.epam.cattery.entity.Review;
import by.epam.cattery.entity.User;
import org.apache.commons.validator.GenericValidator;

public class Validator {
    private final static Validator instance = new Validator();

    private static final String VALID_LOGIN_REGEX = "[0-9a-zA-Zа-яА-Я]{2,10}";
    private static final String VALID_NAME_AND_LASTNAME_REGEX = "[a-zA-Zа-яА-Я]{2,20}";
    private static final String VALID_PASSWORD_REGEX = ".{7,15}";

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

        return GenericValidator.isInRange(review.getStarsCount(), 1, 5)
                && !GenericValidator.isBlankOrNull(review.getText());
    }



    public static Validator getInstance() {
        return instance;
    }
}
