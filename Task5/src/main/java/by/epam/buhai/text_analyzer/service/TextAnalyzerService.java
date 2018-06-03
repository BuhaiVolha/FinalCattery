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
    String sortWordsAlphabetically(); //using first letter, new line for each word
    List<String> sortWordsByVowelToTotalLengthRatio();
    List<String> sortVowelStartingWordsByFirstConsonant();
    List<String> sortWordsByCertainLetterRising(char letter); // if letter is the same - than alphabetically
//    TextComponent sortWordsFromListByAppearingDescending();  //how many times occurs
//    TextComponent deleteSubstringStartingAndEndingWith(char letter); //substring of max length
    TextComponent deleteCertainLengthWordsStartingWithConsonant(int length); //substring of max length
    List<String> sortWordsByCertainLetterDescending(char letter); //alphabetically - if equals
    String findMaxPalindromeSubstring(); // in words?? in sentence?
    TextComponent deleteFromWordsFirstLetterEverywhere(); //or last
    TextComponent replaceCertainLengthWordsWithSubstring(int sentenceNumber, int wordToReplaceLength, String substring);


    //111111111111111 return List<TextComponent>
}
