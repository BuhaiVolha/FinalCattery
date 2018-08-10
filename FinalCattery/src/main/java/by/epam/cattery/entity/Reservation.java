package by.epam.cattery.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.sql.Timestamp;

public class Reservation extends Entity {
    private static final long serialVersionUID = 4211597240404696270L;

    private int userMadeReservationId;
    private String userMadeReservationName;
    private String userMadeReservationLastname;
    private int catId;
    private String catName;
    private String catLastname;
    private String catPhoto;
    private String chequePhoto;
    private Timestamp dateOfReservation;
    private CatPedigreeType pedigreeType;
    private double totalCost;
    private boolean expired;
    private ReservationStatus status;

    public Reservation() {
    }

    public int getUserMadeReservationId() {
        return userMadeReservationId;
    }

    public void setUserMadeReservationId(int userMadeReservationId) {
        this.userMadeReservationId = userMadeReservationId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
    }

    public Timestamp getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(Timestamp dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }

    public CatPedigreeType getPedigreeType() {
        return pedigreeType;
    }

    public void setPedigreeType(CatPedigreeType pedigreeType) {
        this.pedigreeType = pedigreeType;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getUserMadeReservationName() {
        return userMadeReservationName;
    }

    public void setUserMadeReservationName(String userMadeReservationName) {
        this.userMadeReservationName = userMadeReservationName;
    }

    public String getUserMadeReservationLastname() {
        return userMadeReservationLastname;
    }

    public void setUserMadeReservationLastname(String userMadeReservationLastname) {
        this.userMadeReservationLastname = userMadeReservationLastname;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String getCatLastname() {
        return catLastname;
    }

    public void setCatLastname(String catLastname) {
        this.catLastname = catLastname;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public String getCatPhoto() {
        return catPhoto;
    }

    public void setCatPhoto(String catPhoto) {
        this.catPhoto = catPhoto;
    }

    public String getChequePhoto() {
        return chequePhoto;
    }

    public void setChequePhoto(String chequePhoto) {
        this.chequePhoto = chequePhoto;
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || (getClass() != o.getClass())) return false;
        if (this == o) return true;

        Reservation that = (Reservation) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getUserMadeReservationId(), that.getUserMadeReservationId())
                .append(getCatId(), that.getCatId())
                .append(getTotalCost(), that.getTotalCost())
                .append(getDateOfReservation(), that.getDateOfReservation())
                .append(getPedigreeType(), that.getPedigreeType())
                .append(getUserMadeReservationName(), that.getUserMadeReservationName())
                .append(getUserMadeReservationLastname(), that.getUserMadeReservationLastname())
                .append(getCatName(), that.getCatName())
                .append(getCatLastname(), that.getCatLastname())
                .append(getStatus(), that.getStatus())
                .append(isExpired(), that.isExpired())
                .append(getCatPhoto(), that.getCatPhoto())
                .append(getChequePhoto(), that.getChequePhoto())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getUserMadeReservationId())
                .append(getCatId())
                .append(getDateOfReservation())
                .append(getPedigreeType())
                .append(getTotalCost())
                .append(getCatName())
                .append(getUserMadeReservationName())
                .append(getUserMadeReservationLastname())
                .append(getCatLastname())
                .append(getStatus())
                .append(isExpired())
                .append(getCatPhoto())
                .append(getChequePhoto())
                .toHashCode();
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userMadeReservationId", userMadeReservationId)
                .append("userMadeReservationName", userMadeReservationName)
                .append("userMadeReservationLastname", userMadeReservationLastname)
                .append("catId", catId)
                .append("catName", catName)
                .append("catLastname", catLastname)
                .append("catPhoto", catPhoto)
                .append("chequePhoto", chequePhoto)
                .append("dateOfReservation", dateOfReservation)
                .append("pedigreeType", pedigreeType)
                .append("totalCost", totalCost)
                .append("expired", expired)
                .append("status", status)
                .toString();
    }
}
