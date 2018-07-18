package by.epam.cattery.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class User extends Entity {
    private static final long serialVersionUID = 2798330215955662152L;

    private String login;
    private String password;
    private Role role;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String colourPreference;
    private int discount;
    private boolean banned;
    private boolean reviewLeft;


    public User(int id, String login, String password, Role role, String name, String lastName,
                String email, String phone, String colourPreference, int discount, boolean banned, boolean reviewLeft) {
        super(id);
        this.login = login;
        this.password = password;   // массив символов?
        this.role = role;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.colourPreference = colourPreference;
        this.discount = discount;
        this.banned = banned;
        this.reviewLeft = reviewLeft;
    }

    public User() {
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String userLogin) {
        this.login = userLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getColourPreference() {
        return colourPreference;
    }

    public void setColourPreference(String colourPreference) {
        this.colourPreference = colourPreference;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public boolean isReviewLeft() {
        return reviewLeft;
    }

    public void setReviewLeft(boolean reviewLeft) {
        this.reviewLeft = reviewLeft;
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || (getClass() != o.getClass())) return false;
        if (this == o) return true;

        User user = (User) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(isBanned(), user.isBanned())
                .append(getLogin(), user.getLogin())
                .append(getPassword(), user.getPassword())
                .append(getRole(), user.getRole())
                .append(getName(), user.getName())
                .append(getLastName(), user.getLastName())
                .append(getEmail(), user.getEmail())
                .append(getPhone(), user.getPhone())
                .append(getColourPreference(), user.getColourPreference())
                .append(getDiscount(), user.getDiscount())
                .append(isReviewLeft(), user.isReviewLeft())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getLogin())
                .append(getPassword())
                .append(getRole())
                .append(getName())
                .append(getLastName())
                .append(getEmail())
                .append(getPhone())
                .append(getColourPreference())
                .append(getDiscount())
                .append(isBanned())
                .append(isReviewLeft())
                .toHashCode();
    }
}
