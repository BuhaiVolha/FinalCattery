package by.epam.training.entity;

public enum ApplianceType {

    LAPTOP("Laptop"),
    OVEN("Oven"),
    REFRIGERATOR("Refrigerator");

    private String description;

    // this method returns a String description
    // which is used to make sure
    // that the user's input is valid
    ApplianceType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
