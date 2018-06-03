package by.epam.buhai.text_analyzer.entity;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.Iterator;
import java.util.List;

public class LeafTextEntity extends TextComponent {
    private TextComponentType type;
    private char value;

    public LeafTextEntity(char value, TextComponentType type) {
        this.value = value;
        this.type = type;
    }


    public String getContent() {
        return content;
    }

//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//
//    public void setType(TextComponentType type) {
//        this.type = type;
//    }


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
    public Iterator<TextComponent> iterator() {
        //return new TextComponentIterator(this);
        return null;
    }

    public void accept(TextComponentVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void addChildTextComponent(TextComponent textComponent) {
        System.out.println("usupported");
    }


    @Override
    public void setChildTextComponents(List<TextComponent> childTextComponents) {
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

        if (o == null || getClass() != o.getClass()) return false;

        LeafTextEntity that = (LeafTextEntity) o;

        return new EqualsBuilder()
                .append(value, that.value)
                .append(getType(), that.getType())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getType())
                .append(value)
                .toHashCode();
    }


//    @Override
//    public String toString() {
//        return content;
//    }

    @Override
    public String toString() {
        return Character.toString(value);
    }
}
