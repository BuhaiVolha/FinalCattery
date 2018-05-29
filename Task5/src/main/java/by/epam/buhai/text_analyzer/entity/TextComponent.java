package by.epam.buhai.text_analyzer.entity;

import java.util.List;

public abstract class TextComponent {
    protected String content;

    public abstract TextComponentType getType();
    public abstract boolean isLeafTextEntity();
    public abstract void addChildTextComponent(TextComponent textComponent);
    public abstract void removeChildTextComponent(TextComponent textComponent);
    public abstract List<TextComponent> getChildTextComponents();
    public abstract int size();
    public abstract void setType(TextComponentType type);


    @Override
    public String toString() {
        return content;
    }
}
