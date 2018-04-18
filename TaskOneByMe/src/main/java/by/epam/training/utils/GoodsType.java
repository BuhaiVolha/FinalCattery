package by.epam.training.utils;

public enum GoodsType {

    LAPTOP("Laptop"),
    OVEN("Oven"),
    REFRIGERATOR("Refrigerator"),
    TABLET_PC("TabletPC"),
    VACUUM_CLEANER("VacuumCleaner"),
    SPEAKERS("Speakers"),
    TEXTBOOK("TextBook"),
    NEWSPAPER("Newspaper");

    private String description;

    // this method returns a String description
    // which is used to make sure
    // that the user's input is valid

    GoodsType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
