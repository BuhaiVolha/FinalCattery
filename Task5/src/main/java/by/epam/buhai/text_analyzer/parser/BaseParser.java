package by.epam.buhai.text_analyzer.parser;

import by.epam.buhai.text_analyzer.entity.TextComponent;

public class BaseParser {
    private static ParagraphParser paragraphParser = new ParagraphParser();

    static {
        SentenceParser sp = new SentenceParser();
        SentencePartsParser spp = new SentencePartsParser();
        LetterParser lp = new LetterParser();

        paragraphParser.setNextParser(sp);
        sp.setNextParser(spp);
        spp.setNextParser(lp);
    }

    public static TextComponent parse(String text) {
        return paragraphParser.parse(text);
    }
}