package by.epam.buhai.text_analyzer.parser;

import by.epam.buhai.text_analyzer.entity.CompositeTextEntity;
import by.epam.buhai.text_analyzer.entity.LeafTextEntity;
import by.epam.buhai.text_analyzer.entity.TextComponent;
import by.epam.buhai.text_analyzer.entity.TextComponentType;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentencePartsParser extends TextParser {
    private final static String SENTENCE_PARTS_REGEXP =
            "[А-Яа-яA-Za-z-']+|\\p{Punct}(?=\\s|\\p{Punct}|$|\\D)|[0-9.,]+";
    private final static String PUNCT_REGEXP = "[\\p{Punct}]+";


    @Override
    public TextComponent parse(String sentence) {
        TextComponent parsedSentence = new CompositeTextEntity(TextComponentType.SENTENCE);
        List<String> sentenceParts = new ArrayList<>();
        Matcher m = Pattern.compile(SENTENCE_PARTS_REGEXP).matcher(sentence);

        while (m.find()) {
            sentenceParts.add(m.group());
        }

        for (String sentencePart : sentenceParts) {
            if (!sentencePart.matches(PUNCT_REGEXP)) {
                parsedSentence.addChildTextComponent(nextParser.parse(sentencePart));
            } else {
                parsedSentence.addChildTextComponent(new LeafTextEntity(sentencePart.charAt(0), TextComponentType.PUNCTUATION_CHAR));
            }
        }
        return parsedSentence;
    }
}
