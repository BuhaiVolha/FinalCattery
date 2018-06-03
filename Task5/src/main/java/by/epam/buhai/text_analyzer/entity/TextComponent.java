package by.epam.buhai.text_analyzer.entity;

import java.util.Iterator;
import java.util.List;

public abstract class TextComponent implements Iterable<TextComponent> {
    String content;

    public abstract TextComponentType getType();
    public abstract boolean isLeafTextEntity();
    public abstract void addChildTextComponent(TextComponent textComponent);
    public abstract void removeChildTextComponent(TextComponent textComponent);
    public abstract List<TextComponent> getChildTextComponents();
    public abstract int size();
    public abstract String getContent();
    public abstract void setChildTextComponents(List<TextComponent> childTextComponents);

//    @Override
//    public Iterator<TextComponent> iterator() {
//        //return childTextComponents.iterator();
//        //return new TextComponentIterator(this);
//    }


    public void accept(TextComponentVisitor visitor) {
        visitor.visit(this);
    }


    @Override
    public String toString() {
        return content;
    }
}
