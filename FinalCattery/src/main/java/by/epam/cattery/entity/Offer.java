package by.epam.cattery.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Offer extends Entity {
    // phone?
    private static final long serialVersionUID = 7294045672541510139L;
    private int userMadeOfferId;
    private String userMadeOfferName;
    private String userMadeOfferLastname;
    private String userMadeOfferPhone;
    private String catDescription; //kittenDescription
    //photo String?
    private double price;  //priceDollars ??? //kittenPrice
    private OfferStatus status;
    private String expertMessage;
    private String expertMessageToAdmin;


    public Offer(int id, int userMadeOfferId, String userMadeOfferName, String userMadeOfferLastname,
                 String userMadeOfferPhone, String catDescription, double price, OfferStatus status,
                 String expertMessage, String expertMessageToAdmin) {
        super(id);
        this.userMadeOfferId = userMadeOfferId;
        this.userMadeOfferName = userMadeOfferName;
        this.userMadeOfferLastname = userMadeOfferLastname;
        this.userMadeOfferPhone = userMadeOfferPhone;
        this.catDescription = catDescription;
        this.price = price;
        this.status = status;
        this.expertMessage = expertMessage;
        this.expertMessageToAdmin = expertMessageToAdmin;
    }

    public Offer() {
    }

    public int getUserMadeOfferId() {
        return userMadeOfferId;
    }

    public void setUserMadeOfferId(int userMadeOfferId) {
        this.userMadeOfferId = userMadeOfferId;
    }

    public String getUserMadeOfferName() {
        return userMadeOfferName;
    }

    public void setUserMadeOfferName(String userMadeOfferName) {
        this.userMadeOfferName = userMadeOfferName;
    }

    public String getUserMadeOfferLastname() {
        return userMadeOfferLastname;
    }

    public void setUserMadeOfferLastname(String userMadeOfferLastname) {
        this.userMadeOfferLastname = userMadeOfferLastname;
    }

    public String getUserMadeOfferPhone() {
        return userMadeOfferPhone;
    }

    public void setUserMadeOfferPhone(String userMadeOfferPhone) {
        this.userMadeOfferPhone = userMadeOfferPhone;
    }

    public String getCatDescription() {
        return catDescription;
    }

    public void setCatDescription(String catDescription) {
        this.catDescription = catDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public OfferStatus getStatus() {
        return status;
    }

    public void setStatus(OfferStatus status) {
        this.status = status;
    }

    public String getExpertMessage() {
        return expertMessage;
    }

    public void setExpertMessage(String expertMessage) {
        this.expertMessage = expertMessage;
    }

    public String getExpertMessageToAdmin() {
        return expertMessageToAdmin;
    }

    public void setExpertMessageToAdmin(String expertMessageToAdmin) {
        this.expertMessageToAdmin = expertMessageToAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || (getClass() != o.getClass())) return false;
        if (this == o) return true;

        Offer offer = (Offer) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getUserMadeOfferId(), offer.getUserMadeOfferId())
                .append(getPrice(), offer.getPrice())
                .append(getUserMadeOfferName(), offer.getUserMadeOfferName())
                .append(getUserMadeOfferLastname(), offer.getUserMadeOfferLastname())
                .append(getUserMadeOfferPhone(), offer.getUserMadeOfferPhone())
                .append(getCatDescription(), offer.getCatDescription())
                .append(getStatus(), offer.getStatus())
                .append(getExpertMessage(), offer.getExpertMessage())
                .append(getExpertMessageToAdmin(), offer.getExpertMessageToAdmin())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getUserMadeOfferId())
                .append(getUserMadeOfferName())
                .append(getUserMadeOfferLastname())
                .append(getUserMadeOfferPhone())
                .append(getCatDescription())
                .append(getPrice())
                .append(getStatus())
                .append(getExpertMessage())
                .append(getExpertMessageToAdmin())
                .toHashCode();
    }
}
