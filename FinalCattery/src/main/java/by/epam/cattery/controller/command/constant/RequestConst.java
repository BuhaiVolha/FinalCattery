package by.epam.cattery.controller.command.constant;

public final class RequestConst {
    public static final String USER = "user";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_NAME = "name";
    public static final String USER_LASTNAME = "lastname";
    public static final String USER_DISCOUNT = "discount";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE = "phone";
    public static final String USER_COLOUR_PREFERENCE = "colour";
    public static final String USERS_LIST = "users";

    public static final String CAT_ID = "catId";
    public static final String CAT_NAME = "name";
    public static final String CAT_LASTNAME = "lastname";
    public static final String CAT_PRICE = "price";
    public static final String CAT_GENDER = "gender";
    public static final String CAT_STATUS = "status";
    public static final String CAT_AGE = "age";
    public static final String CAT_BODY_COLOUR = "bodyColour";
    public static final String CAT_EYES_COLOUR = "eyesColour";
    public static final String CAT_DESCRIPTION = "description";
    public static final String CAT_FEMALE_PARENT = "femaleParent";
    public static final String CAT_MALE_PARENT = "maleParent";
    public static final String SINGLE_CAT = "singleCat";
    public static final String CATS_LIST = "cats";
    public static final String SEARCHED_CAT = "searchedCat";
    public static final String ADDED_CAT_PHOTO = "photo";

    public static final String OFFER = "offer";
    public static final String OFFER_ID = "offerId";
    public static final String OFFERS_LIST = "offers";
    public static final String OFFER_CAT_DESCRIPTION = "catDescription";
    public static final String OFFER_PRICE = "price";
    public static final String OFFER_EXPERT_MESSAGE_TO_USER = "expertMessage";
    public static final String OFFER_EXPERT_MESSAGE_TO_ADMIN = "expertMessageToAdmin";
    public static final String OFFER_MADE_USER_ID = "userMadeOfferId";

    public static final String REVIEW_ID = "reviewId";
    public static final String REVIEW = "review";
    public static final String REVIEWS_LIST = "approvedReviews";
    public static final String REVIEW_MESSAGE = "message";
    public static final String REVIEW_RATING = "rating";

    public static final String RESERVATION_ID = "reservationId";
    public static final String RESERVATIONS_LIST = "reservations";
    public static final String PEDIGREE_TYPE = "pedigreeType";
    public static final String TOTAL_COST = "total";

    public static final String PEDIGREE_STATISTICS = "pedigreeCount";
    public static final String COLOUR_PREFERENCE_STATISTICS = "colourStatistics";

    public static final String PAGINATION_PAGE = "page";

    public static final String OFFER_PHOTO_PARTNAME = "offer";
    public static final String CHEQUE_PHOTO_PARTNAME = "cheque";
    public static final String CAT_PHOTO_PARTNAME = "cat";


    public static final String REGISTRATION_FAILED_MESSAGE = "registrationFailedMessage";
    public static final String EDIT_USER_INFO_FAILED_MESSAGE = "editUserInfoFailedMessage";
    public static final String ANSWER_TO_OFFER_FAILED_MESSAGE = "answerToOfferFailedMessage";
    public static final String WRITE_REVIEW_FAILED_MESSAGE = "writeReviewFailedMessage";


    public static final String OPERATION = "operation";


    private RequestConst() {
        throw new AssertionError();
    }
}
