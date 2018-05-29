package by.epam.buhai.text_analyzer.parser;

import by.epam.buhai.text_analyzer.entity.CompositeTextEntity;
import by.epam.buhai.text_analyzer.entity.TextComponent;
import by.epam.buhai.text_analyzer.entity.TextComponentType;

public class ParagraphParser extends TextParser {
    private final static String PARAGRAPH_REGEXP = "(\\n)";

    @Override
    public TextComponent parse(String text) {
        String[] paragraphs = text.split(PARAGRAPH_REGEXP);
        CompositeTextEntity parsedText = new CompositeTextEntity(TextComponentType.TEXT);

        for (String paragraph : paragraphs) {
            parsedText.addChildTextComponent(nextParser.parse(paragraph));

        }
        return parsedText;
    }
}
