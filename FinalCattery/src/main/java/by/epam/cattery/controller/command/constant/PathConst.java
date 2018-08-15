package by.epam.cattery.controller.command.constant;

// писать АДМИН ЭКСПЕРТ как суффикс если только для этих ролей страница?
public final class PathConst {
    public final static String PATH_START = "path.page.";

    public final static String SUCCESS_PAGE = "path.page.success-page";
    public final static String ERROR_PAGE = "path.page.error";
    public final static String MAIN_PAGE = "path.page.main";

    public final static String REGISTRATION = "path.page.reg";
    public final static String ALL_USERS = "path.page.manage-users";

    public final static String AVAILABLE_CATS = "path.page.cats-available";
    public final static String ALL_CATS = "path.page.cats-all";
    public final static String FOUND_CATS = "path.page.cats-found";
    public final static String CAT_FORM_PAGE = "path.page.cat-form";

    public final static String REVIEWS = "path.page.reviews";
    public final static String WRITE_REVIEW = "path.page.edit-review";

    public final static String RESERVATIONS = "path.page.reservations";

    public final static String PEDIGREE_STATISTICS_PAGE = "path.page.pedigree";
    public final static String COLOUR_PREFERENCES_STATISTICS_PAGE = "path.page.statistics";

    public final static String ALL_OFFERS = "path.page.offers-all";
    public final static String AWAITING_OFFERS = "path.page.offers-awaiting";
    public final static String APPROVED_OFFERS = "path.page.offers-approved";

    public final static String UPLOAD_CAT_PHOTO_PAGE = "path.page.upload-cat-photo";
    public final static String OFFER_PHOTO_SAVE_PATH = "path.photo.offer";
    public final static String CHEQUE_PHOTO_SAVE_PATH = "path.photo.cheque";
    public final static String CAT_PHOTO_SAVE_PATH = "path.photo.cat";

    public final static String EDIT_USER_INFO_COMMAND = "path.command.edit-user-info";
    public final static String BARGAIN_COMMAND = "path.command.offer-bargain";
    public final static String EDIT_CAT_COMMAND = "path.command.edit-cat";


    private PathConst() {
        throw new AssertionError();
    }
}
