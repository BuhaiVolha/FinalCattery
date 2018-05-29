package by.epam.buhai.text_analyzer.parser;

import by.epam.buhai.text_analyzer.entity.TextComponent;

public abstract class TextParser {
    TextParser nextParser;

    public void setNextParser(TextParser nextParser) {
        this.nextParser = nextParser;
    }

    public abstract TextComponent parse(String text);
}
