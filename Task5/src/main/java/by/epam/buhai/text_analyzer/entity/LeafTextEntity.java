package by.epam.buhai.text_analyzer.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import java.util.List;

public class LeafTextEntity extends TextComponent {
    private TextComponentType type;

    public LeafTextEntity(String content, TextComponentType type) {
        this.content = content;
        this.type = type;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public void setType(TextComponentType type) {
        this.type = type;
    }


    @Override
    public TextComponentType getType() {
        return type;
    }


    @Override
    public int size() {
        return 0;
    }


    @Override
    public boolean isLeafTextEntity() {
        return true;
    }


    @Override
    public void addChildTextComponent(TextComponent textComponent) {
        System.out.println("usupported");
    }


    @Override
    public void removeChildTextComponent(TextComponent textComponent) {
        System.out.println("usupported");
    }


    @Override
    public List<TextComponent> getChildTextComponents() {
        return null;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()){
            return false;
        }

        LeafTextEntity that = (LeafTextEntity) o;

        return new EqualsBuilder()
                .append(getType(), that.getType())
                .append(getContent(), that.getContent())
                .isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getType())
                .append(getContent())
                .toHashCode();
    }


    @Override
    public String toString() {
        return content;
    }
}
