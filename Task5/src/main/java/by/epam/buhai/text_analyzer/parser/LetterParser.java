package by.epam.buhai.text_analyzer.parser;

import by.epam.buhai.text_analyzer.entity.LeafTextEntity;
import by.epam.buhai.text_analyzer.entity.TextComponent;
import by.epam.buhai.text_analyzer.entity.TextComponentType;
import by.epam.buhai.text_analyzer.entity.CompositeTextEntity;

public class LetterParser extends TextParser {
    private final static String WORD_REGEXP = "[a-zA-Zа-яА-Я'-]+";

    @Override
    public TextComponent parse(String word) {
        TextComponent parsedWord;

        if (word.matches(WORD_REGEXP)) {
            parsedWord = new CompositeTextEntity(TextComponentType.WORD);
        } else {
            parsedWord = new CompositeTextEntity(TextComponentType.NUMBER);
        }

        int length = word.length();

        for (int i = 0; i < length; i++) {
            parsedWord.addChildTextComponent(new LeafTextEntity(word.charAt(i), TextComponentType.LETTER));
        }
        return parsedWord;
    }
}
