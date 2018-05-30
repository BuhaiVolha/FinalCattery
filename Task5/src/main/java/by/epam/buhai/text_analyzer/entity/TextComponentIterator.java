package by.epam.buhai.text_analyzer.entity;

import java.util.*;

public class TextComponentIterator implements Iterator<TextComponent> {
    //private Deque<TextComponent> stack = new LinkedList<>();
    private Deque<TextComponent> stack = new ArrayDeque<>();


    public TextComponentIterator(TextComponent root) {
        stack.add(root);
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
        TextComponent node = stack.pop();

        if (node != null) { //only if Composite.children has null
            if (node instanceof CompositeTextEntity) {
                CompositeTextEntity ac = (CompositeTextEntity) node;
                for (TextComponent acc : ac.getChildTextComponents()) {
                    //Collections.addAll(stack, acc);
                    stack.add(acc);
                }
            }
        }
        return node;
    }
}
