package by.epam.cattery.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class Cat extends Entity {
    private static final long serialVersionUID = 8277026855704434661L;
    private String name;
    private int userMadeOfferId;
    private String lastname;
    private Gender gender;
    private String age;
    //photo
    private String description;
    private String bodyColour;
    private String eyesColour;
    private String femaleParent;
    private String maleParent;
    private double price; // ???
    // еще два поля енамы походу


    //конструктор с парам

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

    public String getBodyColour() {
        return bodyColour;
    }

    public void setBodyColour(String bodyColour) {
        this.bodyColour = bodyColour;
    }

    public String getEyesColour() {
        return eyesColour;
    }

    public void setEyesColour(String eyesColour) {
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
                .toHashCode();
    }
}
