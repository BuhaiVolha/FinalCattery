package by.epam.cattery.entity.dto;

import by.epam.cattery.entity.*;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.List;


public class LocalizedCat extends Entity {
    private static final long serialVersionUID = -1193733651410908090L;
    private int userMadeOfferId;
    private int offerMadeId;
    private Gender gender;
    private String age;
    private String photo;
    private CatBodyColour bodyColour;
    private CatEyesColour eyesColour;
    private double price;
    private Double priceWithDiscount;
    private CatStatus status;
    private List<CatDetail> catDetailsWithLocalization;


    public LocalizedCat(int id, int userMadeOfferId, int offerMadeId, Gender gender, String age, String photo,
                        CatBodyColour bodyColour, CatEyesColour eyesColour, double price, Double priceWithDiscount,
                        CatStatus status, List<CatDetail> catDetailsWithLocalization) {
        super(id);
        this.userMadeOfferId = userMadeOfferId;
        this.offerMadeId = offerMadeId;
        this.gender = gender;
        this.age = age;
        this.photo = photo;
        this.bodyColour = bodyColour;
        this.eyesColour = eyesColour;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
        this.status = status;
        this.catDetailsWithLocalization = catDetailsWithLocalization;
    }

    public LocalizedCat() {
    }

    public int getUserMadeOfferId() {
        return userMadeOfferId;
    }

    public void setUserMadeOfferId(int userMadeOfferId) {
        this.userMadeOfferId = userMadeOfferId;
    }

    public int getOfferMadeId() {
        return offerMadeId;
    }

    public void setOfferMadeId(int offerMadeId) {
        this.offerMadeId = offerMadeId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public CatBodyColour getBodyColour() {
        return bodyColour;
    }

    public void setBodyColour(CatBodyColour bodyColour) {
        this.bodyColour = bodyColour;
    }

    public CatEyesColour getEyesColour() {
        return eyesColour;
    }

    public void setEyesColour(CatEyesColour eyesColour) {
        this.eyesColour = eyesColour;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(Double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public CatStatus getStatus() {
        return status;
    }

    public void setStatus(CatStatus status) {
        this.status = status;
    }

    public List<CatDetail> getCatDetailsWithLocalization() {
        return catDetailsWithLocalization;
    }

    public void setCatDetailsWithLocalization(List<CatDetail> catDetailsWithLocalization) {
        this.catDetailsWithLocalization = catDetailsWithLocalization;
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || (getClass() != o.getClass())) return false;
        if (this == o) return true;

        LocalizedCat that = (LocalizedCat) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getUserMadeOfferId(), that.getUserMadeOfferId())
                .append(getOfferMadeId(), that.getOfferMadeId())
                .append(getPrice(), that.getPrice())
                .append(getGender(), that.getGender())
                .append(getAge(), that.getAge())
                .append(getPhoto(), that.getPhoto())
                .append(getBodyColour(), that.getBodyColour())
                .append(getEyesColour(), that.getEyesColour())
                .append(getPriceWithDiscount(), that.getPriceWithDiscount())
                .append(getStatus(), that.getStatus())
                .append(getCatDetailsWithLocalization(), that.getCatDetailsWithLocalization())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getUserMadeOfferId())
                .append(getOfferMadeId())
                .append(getGender())
                .append(getAge())
                .append(getPhoto())
                .append(getBodyColour())
                .append(getEyesColour())
                .append(getPrice())
                .append(getPriceWithDiscount())
                .append(getStatus())
                .append(getCatDetailsWithLocalization())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(super.toString())
                .append("userMadeOfferId", userMadeOfferId)
                .append("offerMadeId", offerMadeId)
                .append("gender", gender)
                .append("age", age)
                .append("photo", photo)
                .append("bodyColour", bodyColour)
                .append("eyesColour", eyesColour)
                .append("price", price)
                .append("priceWithDiscount", priceWithDiscount)
                .append("status", status)
                .append("catDetailsWithLocalization", catDetailsWithLocalization)
                .toString();
    }
}
