package by.epam.cattery.entity.dto;

import by.epam.cattery.entity.Entity;
import by.epam.cattery.entity.LocaleLang;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class CatDetail extends Entity {
    private static final long serialVersionUID = 6458657199789011198L;
    private String name;
    private String lastname;
    private String description;
    private String femaleParent;
    private String maleParent;
    private LocaleLang localeLang;

    public CatDetail(int id, String name, String lastname, String description, String femaleParent, String maleParent, LocaleLang localeLang) {
        super(id);
        this.name = name;
        this.lastname = lastname;
        this.description = description;
        this.femaleParent = femaleParent;
        this.maleParent = maleParent;
        this.localeLang = localeLang;
    }

    public CatDetail() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public LocaleLang getLocaleLang() {
        return localeLang;
    }

    public void setLocaleLang(LocaleLang localeLang) {
        this.localeLang = localeLang;
    }

    @Override
    public boolean equals(Object o) {
        if ((o == null) || (getClass() != o.getClass())) return false;
        if (this == o) return true;

        CatDetail catDetail = (CatDetail) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(getName(), catDetail.getName())
                .append(getLastname(), catDetail.getLastname())
                .append(getDescription(), catDetail.getDescription())
                .append(getFemaleParent(), catDetail.getFemaleParent())
                .append(getMaleParent(), catDetail.getMaleParent())
                .append(getLocaleLang(), catDetail.getLocaleLang())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(getName())
                .append(getLastname())
                .append(getDescription())
                .append(getFemaleParent())
                .append(getMaleParent())
                .append(getLocaleLang())
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(super.toString())
                .append("name", name)
                .append("lastname", lastname)
                .append("description", description)
                .append("femaleParent", femaleParent)
                .append("maleParent", maleParent)
                .append("localeLang", localeLang)
                .toString();
    }
}
