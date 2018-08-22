package by.epam.cattery.service.validation;

import by.epam.cattery.entity.Review;
import by.epam.cattery.entity.User;

import by.epam.cattery.entity.dto.CatDetail;
import by.epam.cattery.entity.dto.LocalizedCat;

import org.apache.commons.validator.GenericValidator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * Contains methods for validation of user's input.
 */
public class Validator {
    private static final Logger logger = LogManager.getLogger(Validator.class);

    private final static Validator instance = new Validator();

    private static final String VALID_LOGIN_REGEX = "[0-9a-zA-Zа-яА-Я]{2,10}";
    private static final String VALID_NAME_AND_LASTNAME_REGEX = "[a-zA-Zа-яА-Я]{2,20}";
    private static final String VALID_PASSWORD_REGEX = ".{7,15}";
    private static final String VALID_PHONE_REGEX = "^[0-9]{2}\\s[0-9]{7}$";

    private static final String VALID_CAT_NAME_REGEX_RU = "[а-яА-Я]{2,20}";
    private static final String VALID_CAT_NAME_REGEX_EN = "[a-zA-Z]{2,20}";
    private static final String VALID_CAT_LASTNAME_REGEX_RU = "[а-яА-Я]{2,20}(\\s[а-яА-Я]{0,20})?";
    private static final String VALID_CAT_LASTNAME_REGEX_EN = "[a-zA-Z]{2,20}(\\s[a-zA-Z]{0,20})?";
    private static final String VALID_CAT_PARENT_REGEX_RU = "[а-яА-Я]{2,20}\\s[а-яА-Я]{0,20}(\\s[а-яА-Я]{0,20})?";
    private static final String VALID_CAT_PARENT_REGEX_EN = "[a-zA-Z]{2,20}\\s[a-zA-Z]{0,20}(\\s[a-zA-Z]{0,20})?";

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
                && GenericValidator.isEmail(user.getEmail())
                && !GenericValidator.isBlankOrNull(user.getPhone())
                && user.getPhone().matches(VALID_PHONE_REGEX);
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


    public boolean validateCatInputData(LocalizedCat cat) {

        return validateCatDetails(cat.getCatDetailsWithLocalization())
                && GenericValidator.isInRange(cat.getPrice(), MIN_CAT_PRICE, MAX_CAT_PRICE);
    }


    public boolean validateCatDetails(List<CatDetail> catDetails) {
        boolean validRu = false;
        boolean validEn = false;

        for (CatDetail catDetail : catDetails) {

            switch (catDetail.getLocaleLang()) {

                case RU:
                    validRu = validateCatDetailRu(catDetail);
                    break;
                case EN:
                    validEn = validateCatDetailEn(catDetail);
                    break;
            }
        }

        return validRu && validEn;
    }


    public boolean validateCatDetailRu(CatDetail catDetail) {
        return !GenericValidator.isBlankOrNull(catDetail.getName())
                && catDetail.getName().matches(VALID_CAT_NAME_REGEX_RU)
                && !GenericValidator.isBlankOrNull(catDetail.getLastname())
                && catDetail.getLastname().matches(VALID_CAT_LASTNAME_REGEX_RU)
                && !GenericValidator.isBlankOrNull(catDetail.getFemaleParent())
                && catDetail.getFemaleParent().matches(VALID_CAT_PARENT_REGEX_RU)
                && !GenericValidator.isBlankOrNull(catDetail.getMaleParent())
                && catDetail.getMaleParent().matches(VALID_CAT_PARENT_REGEX_RU)
                && !GenericValidator.isBlankOrNull(catDetail.getDescription());
    }

    public boolean validateCatDetailEn(CatDetail catDetail) {
        return !GenericValidator.isBlankOrNull(catDetail.getName())
                && catDetail.getName().matches(VALID_CAT_NAME_REGEX_EN)
                && !GenericValidator.isBlankOrNull(catDetail.getLastname())
                && catDetail.getLastname().matches(VALID_CAT_LASTNAME_REGEX_EN)
                && !GenericValidator.isBlankOrNull(catDetail.getFemaleParent())
                && catDetail.getFemaleParent().matches(VALID_CAT_PARENT_REGEX_EN)
                && !GenericValidator.isBlankOrNull(catDetail.getMaleParent())
                && catDetail.getMaleParent().matches(VALID_CAT_PARENT_REGEX_EN)
                && !GenericValidator.isBlankOrNull(catDetail.getDescription());
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
