package by.epam.cattery.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class User extends Entity {
    private static final long serialVersionUID = 2798330215955662152L;
    // phone number
    private String userLogin;
    private String userPass;
    private Role userRole;
    private String userName;
    private String userLastname;
    private String email;
    private String phone;
    private String userColorPreference;
    private int discount;
    private boolean banned;


    public User(int id, String userLogin, String userPass, Role userRole, String userName, String userLastname,
                String email, String phone,  String userColorPreference, int discount, boolean banned) {
        super(id);
        this.userLogin = userLogin;
        this.userPass = userPass;   // массив символов?
        this.userRole = userRole;
        this.userName = userName;
        this.userLastname = userLastname;
        this.email = email;
        this.phone = phone;
        this.userColorPreference = userColorPreference;
        this.discount = discount;
        this.banned = banned;
    }

    public User() {
    }


    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
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

    public String getUserColorPreference() {
        return userColorPreference;
    }

    public void setUserColorPreference(String userColorPreference) {
        this.userColorPreference = userColorPreference;
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

    @Override
    public boolean equals(Object o) {
        if ((o == null) || (getClass() != o.getClass())) return false;
        if (this == o) return true;

        User user = (User) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(isBanned(), user.isBanned())
                .append(getUserLogin(), user.getUserLogin())
                .append(getUserPass(), user.getUserPass())
                .append(getUserRole(), user.getUserRole())
                .append(getUserName(), user.getUserName())
                .append(getUserLastname(), user.getUserLastname())
                .append(getEmail(), user.getEmail())
                .append(getPhone(), user.getPhone())
                .append(getUserColorPreference(), user.getUserColorPreference())
                .append(getDiscount(), user.getDiscount())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getUserLogin())
                .append(getUserPass())
                .append(getUserRole())
                .append(getUserName())
                .append(getUserLastname())
                .append(getEmail())
                .append(getPhone())
                .append(getUserColorPreference())
                .append(getDiscount())
                .append(isBanned())
                .toHashCode();
    }
}
