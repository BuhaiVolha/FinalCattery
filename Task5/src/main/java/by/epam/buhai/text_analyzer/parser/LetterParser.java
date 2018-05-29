package by.epam.buhai.text_analyzer.parser;

import by.epam.buhai.text_analyzer.entity.LeafTextEntity;
import by.epam.buhai.text_analyzer.entity.TextComponent;
import by.epam.buhai.text_analyzer.entity.TextComponentType;
import by.epam.buhai.text_analyzer.entity.CompositeTextEntity;

public class LetterParser extends TextParser {

    @Override
    public TextComponent parse(String word) {
        TextComponent parsedWord = new CompositeTextEntity(TextComponentType.WORD);

        int length = word.length();

        for (int i = 0; i < length; i++) {
            parsedWord.addChildTextComponent(new LeafTextEntity(word.substring(i, i + 1), TextComponentType.LETTER));
        }
        return parsedWord;
    }
}
