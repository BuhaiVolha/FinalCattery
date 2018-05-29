package by.epam.buhai.text_analyzer.parser;

import by.epam.buhai.text_analyzer.entity.TextComponent;
import by.epam.buhai.text_analyzer.entity.TextComponentType;
import by.epam.buhai.text_analyzer.entity.CompositeTextEntity;

public class SentenceParser extends TextParser {
    private final static String SENTENCE_REGEXP = "(?<=[.!?:])\\s";

    @Override
    public TextComponent parse(String paragraph) {
        TextComponent parsedText = new CompositeTextEntity(TextComponentType.PARAGRAPH);
        String[] sentences = paragraph.split(SENTENCE_REGEXP);

        for (String sentence : sentences) {
            parsedText.addChildTextComponent(nextParser.parse(sentence));
        }
        return parsedText;
    }
}
