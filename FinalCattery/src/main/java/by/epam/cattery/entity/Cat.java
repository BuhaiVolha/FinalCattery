package by.epam.cattery.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Cat extends Entity {
    private static final long serialVersionUID = 8277026855704434661L;
    private String name;
    private int userMadeOfferId;
    private int offerMadeId;
    private String lastname;
    private Gender gender;
    private String age;
    private String photo;
    private String description;
    private CatBodyColour bodyColour;
    private CatEyesColour eyesColour;
    private String femaleParent;
    private String maleParent;
    private double price;
    private Double priceWithDiscount;
    private CatStatus status;


    public Cat(int id, String name, int userMadeOfferId, int offerMadeId, String lastname, Gender gender, String age,
               String photo, String description, CatBodyColour bodyColour, CatEyesColour eyesColour, String femaleParent,
               String maleParent, double price, Double priceWithDiscount, CatStatus status) {
        super(id);
        this.name = name;
        this.userMadeOfferId = userMadeOfferId;
        this.offerMadeId = offerMadeId;
        this.lastname = lastname;
        this.gender = gender;
        this.age = age;
        this.photo = photo;
        this.description = description;
        this.bodyColour = bodyColour;
        this.eyesColour = eyesColour;
        this.femaleParent = femaleParent;
        this.maleParent = maleParent;
        this.price = price;
        this.priceWithDiscount = priceWithDiscount;
        this.status = status;
    }

    public Cat() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getFemaleParent() {
        return femaleParent;
    }

    public void setFemaleParent(String femaleParent) {
        this.femaleParent = femaleParent;
    }

    public String getMaleParent() {
        return maleParent;
    }

    public void setMaleParent(String maleParent) {
        this.maleParent = maleParent;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
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

    public CatStatus getStatus() {
        return status;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setStatus(CatStatus status) {
        this.status = status;
    }

    public Double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(Double priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }


    @Override
    public boolean equals(Object o) {
        if ((o == null) || (getClass() != o.getClass())) return false;
        if (this == o) return true;

        Cat cat = (Cat) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getPrice(), cat.getPrice())
                .append(getName(), cat.getName())
                .append(getLastname(), cat.getLastname())
                .append(getGender(), cat.getGender())
                .append(getDescription(), cat.getDescription())
                .append(getBodyColour(), cat.getBodyColour())
                .append(getEyesColour(), cat.getEyesColour())
                .append(getFemaleParent(), cat.getFemaleParent())
                .append(getMaleParent(), cat.getMaleParent())
                .append(getAge(), cat.getAge())
                .append(getUserMadeOfferId(), cat.getUserMadeOfferId())
                .append(getOfferMadeId(), cat.getOfferMadeId())
                .append(getStatus(), cat.getStatus())
                .append(getPriceWithDiscount(), cat.getPriceWithDiscount())
                .append(getPhoto(), cat.getPhoto())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getLastname())
                .append(getGender())
                .append(getDescription())
                .append(getBodyColour())
                .append(getEyesColour())
                .append(getFemaleParent())
                .append(getMaleParent())
                .append(getPrice())
                .append(getAge())
                .append(getUserMadeOfferId())
                .append(getOfferMadeId())
                .append(getStatus())
                .append(getPriceWithDiscount())
                .append(getPhoto())
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", name)
                .append("userMadeOfferId", userMadeOfferId)
                .append("offerMadeId", offerMadeId)
                .append("lastname", lastname)
                .append("gender", gender)
                .append("age", age)
                .append("photo", photo)
                .append("description", description)
                .append("bodyColour", bodyColour)
                .append("eyesColour", eyesColour)
                .append("femaleParent", femaleParent)
                .append("maleParent", maleParent)
                .append("price", price)
                .append("priceWithDiscount", priceWithDiscount)
                .append("status", status)
                .toString();
    }
}
