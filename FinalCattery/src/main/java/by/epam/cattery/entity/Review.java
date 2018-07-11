package by.epam.cattery.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

// дату добавить
public class Review extends Entity {
    private static final long serialVersionUID = -6959322140072797013L;
    private int userLeftId;
    private String userLeftLogin;
    private String text;

    public Review(int id, int userLeftId, String userLeftLogin, String text) {
        super(id);
        this.userLeftId = userLeftId;
        this.userLeftLogin = userLeftLogin;
        this.text = text;
    }

    public Review() {
    }

    public int getUserLeftId() {
        return userLeftId;
    }

    public void setUserLeftId(int userLeftId) {
        this.userLeftId = userLeftId;
    }

    public String getUserLeftLogin() {
        return userLeftLogin;
    }

    public void setUserLeftLogin(String userLeftLogin) {
        this.userLeftLogin = userLeftLogin;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || (getClass() != o.getClass())) return false;
        if (this == o) return true;

        Review review = (Review) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getUserLeftId(), review.getUserLeftId())
                .append(getUserLeftLogin(), review.getUserLeftLogin())
                .append(getText(), review.getText())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getUserLeftId())
                .append(getUserLeftLogin())
                .append(getText())
                .toHashCode();
    }
}
