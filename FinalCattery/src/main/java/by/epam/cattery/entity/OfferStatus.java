package by.epam.cattery.entity;

public enum OfferStatus {
    AWAITING("1"), APPROVED("2"), REJECTED("3"), SENT("4");

    OfferStatus(String dateBaseId) {
        this.dateBaseId = dateBaseId;
    }

    private String dateBaseId;

    public String getDateBaseId() {
        return dateBaseId;
    }
}
