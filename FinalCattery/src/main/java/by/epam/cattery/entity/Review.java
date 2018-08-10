package by.epam.cattery.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.sql.Date;

public class Review extends Entity {
    private static final long serialVersionUID = -6959322140072797013L;
    private int userLeftId;
    private String userLeftLogin;
    private String text;
    private int starsCount;
    private Date date;


    public Review(int id, int userLeftId, String userLeftLogin, String text, int starsCount, Date date) {
        super(id);
        this.userLeftId = userLeftId;
        this.userLeftLogin = userLeftLogin;
        this.text = text;
        this.starsCount = starsCount;
        this.date = date;
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

    public int getStarsCount() {
        return starsCount;
    }

    public void setStarsCount(int starsCount) {
        this.starsCount = starsCount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                .append(getStarsCount(), review.getStarsCount())
                .append(getDate(), review.getDate())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getUserLeftId())
                .append(getUserLeftLogin())
                .append(getText())
                .append(getStarsCount())
                .append(getDate())
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userLeftId", userLeftId)
                .append("userLeftLogin", userLeftLogin)
                .append("text", text)
                .append("starsCount", starsCount)
                .append("date", date)
                .toString();
    }
}
