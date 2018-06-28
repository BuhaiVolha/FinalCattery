package by.epam.buhai.text_analyzer.service;

import by.epam.buhai.text_analyzer.entity.TextComponent;

import java.util.List;
import java.util.Set;

public interface TextAnalyzerService {
    void setText(TextComponent text);

    TextComponent findSentencesWithSameWords(); // what about "a", "to", "and" etc
    TextComponent sortSentencesByWordsNumberRising();
    Set<String> findFirstSentenceUniqueWord();
    Set<TextComponent> findCertainLengthWordsInQuestionSentences(int length);
    TextComponent replaceFirstAndLastWord();
    String sortWordsAlphabetically();
    List<String> sortWordsByVowelToTotalLengthRatio();
    List<String> sortVowelStartingWordsByFirstConsonant();
    List<String> sortWordsByCertainLetterRising(char letter);
    String sortWordsFromListByAppearingDescending(List<String> wordsList);
    String deleteSubstringStartingAndEndingWith(char startSymbol, char endSymbol);
    TextComponent deleteCertainLengthWordsStartingWithConsonant(int length);
    List<String> sortWordsByCertainLetterDescending(char letter);
    String findMaxPalindromeSubstring(); // in words?? in sentence?
    TextComponent deleteFromWordsFirstLetterEverywhere(); //or last
    TextComponent replaceCertainLengthWordsWithSubstring(int sentenceNumber, int wordToReplaceLength, String substring);


    //111111111111111 return List<TextComponent>
}
