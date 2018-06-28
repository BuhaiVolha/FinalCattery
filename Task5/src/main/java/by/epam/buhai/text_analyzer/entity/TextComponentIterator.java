package by.epam.buhai.text_analyzer.entity;

import java.util.*;

public class TextComponentIterator implements Iterator<TextComponent> {
    private Deque<TextComponent> stack = new ArrayDeque<>();


    TextComponentIterator(TextComponent component) {
        stack.add(component);
    }


    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }


    @Override
    public TextComponent next() {

        if(stack.isEmpty()){
            System.out.println("no such element excep");
        }
        TextComponent textComponent = stack.pop();

        if (textComponent != null) {

            if (textComponent instanceof CompositeTextEntity) {
                CompositeTextEntity ac = (CompositeTextEntity) textComponent;

                for (TextComponent acc : ac.getChildTextComponents()) {
                    Collections.addAll(stack, acc);
                }
            }
        }
        return textComponent;
    }
}
