package by.epam.buhai.text_analyzer.service.impl;

import by.epam.buhai.text_analyzer.entity.LeafTextEntity;
import by.epam.buhai.text_analyzer.entity.TextComponent;
import by.epam.buhai.text_analyzer.entity.TextComponentType;
import by.epam.buhai.text_analyzer.service.TextAnalyzerService;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyzerServiceImpl implements TextAnalyzerService {  // конструктор кула сетится текст
    private TextComponent text;

    private static final String CONSONANT = "([^aioueyаяоэиеёыу])";
    private static final String VOWEL = "[aioueyаяоэиеёыу]";
    private static final String STARTING_WITH_VOWEL_WORD = "\\b[aioueyаяоэиеёыу].+";

    private static final String PALINDROME = "(?x)|(?:(.)add) + chk"
            .replace("add", assertEntirety(".*? (\\1 \\2?)"))
            .replace("chk", assertEntirety("\\2"));
    private static final int MIN_PALINDROME_LENGTH = 3;
    private static final String PUNCTUATION_AND_SPACES = "[\\p{Punct}\\s]";

    private static final String QUESTION_SENTENCE = "[.?=\\d+\\s\\wа-яА-Я-,:(){}]+[!?]{1,3}$";
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";

    private static final String OPENING_SQUARE_BRACKETS = "[";
    private static final String CLOSING_SQUARE_BRACKETS = "]";
    private static final String ANYTHING = ".*";
    private static final String EMPTY = "";


    @Override
    public void setText(TextComponent text) {
        this.text = text;
    }


    // 1

    public TextComponent findSentencesWithSameWords() {
        List<TextComponent> allWords;
        Set<TextComponent> allWordsWithoutRepetitions;
        List<TextComponent> toBeDeleted = new ArrayList<>();

        for (TextComponent paragraph : text.getChildTextComponents()) {

            for (TextComponent sentence : paragraph.getChildTextComponents()) {
                allWords = getAllOfType(sentence, TextComponentType.WORD);
                allWordsWithoutRepetitions = new HashSet<>(allWords);

                if (allWordsWithoutRepetitions.size() == allWords.size()) {
                    toBeDeleted.add(sentence);
                }
            }
            paragraph.getChildTextComponents().removeAll(toBeDeleted);
        }
        return text;
    }


    // 2

    public TextComponent sortSentencesByWordsNumberRising() {
        List<TextComponent> allSentences = getAllOfType(text, TextComponentType.SENTENCE);
        allSentences.sort(Comparator.comparingInt(o -> o.getChildTextComponents().size()));

        text.setChildTextComponents(allSentences);
        return text;
    }


    // 3

    public Set<String> findFirstSentenceUniqueWord() {
        List<TextComponent> allSentences = getAllOfType(text, TextComponentType.SENTENCE);
        Set<String> firstSentenceWords
                = new HashSet<>(getAllStringsOfType(allSentences.get(0), TextComponentType.WORD));

        deleteFirstSentence();

        Set<String> allStringedWords = new HashSet<>(getAllStringsOfType(text, TextComponentType.WORD));
        firstSentenceWords.removeAll(allStringedWords);

        return firstSentenceWords;
    }


    private void deleteFirstSentence() {
        text.getChildTextComponents().get(0).getChildTextComponents().remove(0);
    }


    // 4

    public Set<TextComponent> findCertainLengthWordsInQuestionSentences(int wordLength) {
        List<TextComponent> allSentences = getAllOfType(text, TextComponentType.SENTENCE);
        Set<TextComponent> results = new HashSet<>();

        for (TextComponent sentence : allSentences) {

            if (sentence.toString().matches(QUESTION_SENTENCE)) {

                for (TextComponent word : sentence.getChildTextComponents()) {

                    if ((word.getType() == TextComponentType.WORD)
                            && (word.size() == wordLength)) {
                        results.add(word);
                    }
                }
            }
        }
        return results;
    }


    // 5

    public TextComponent replaceFirstAndLastWord() {

        for (TextComponent paragraph : text.getChildTextComponents()) {
            for (TextComponent sentence : paragraph.getChildTextComponents()) {
                TextComponent lastWord = sentence
                        .getChildTextComponents()
                        .get(findLastWordPosition(sentence));

                sentence.getChildTextComponents()
                        .set(findLastWordPosition(sentence), sentence.getChildTextComponents().get(0));

                sentence.getChildTextComponents()
                        .set(0, lastWord);
            }
        }
        return text;
    }


    private int findLastWordPosition(TextComponent sentence) {
        int lastWordPosition = sentence.getChildTextComponents().size() - 1;
        int index = -1;

        for (int i = lastWordPosition; i >= 0; i--) {
            if (sentence.getChildTextComponents().get(i).getType() == TextComponentType.WORD) {
                index = i;
                break;
            }
        }
        return index;
    }


    // 6

    public String sortWordsAlphabetically() {
        List<TextComponent> allWords = getAllOfType(text, TextComponentType.WORD);
        allWords.sort(Comparator.comparing(TextComponent -> TextComponent.toString().toLowerCase()));
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < allWords.size(); i++) {

            if (findFirstLetter(allWords.get(i - 1)).
                    equalsIgnoreCase(findFirstLetter(allWords.get(i)))) {
                result.append(allWords.get(i).toString());
            } else {
                result.append(NEW_LINE).append(allWords.get(i).toString());
            }
            result.append(SPACE);
        }

//        List<String> allWords = getAllStringsOfType(text, TextComponentType.WORD);
//        allWords.sort(Comparator.naturalOrder());
//        StringBuilder result = new StringBuilder();
//
//        for (int i = 1; i < allWords.size(); i++) {  // ГЛЯНУТЬ ПОЗЖЕ РЕФАКТОРИНГ !1111
//
//            if (allWords.get(i - 1).charAt(0) == allWords.get(i).charAt(0)) {
//                result.append(allWords.get(i));
//            } else {
//                result.append(NEW_LINE).append(allWords.get(i));
//            }
//            result.append(SPACE);
//        }
        return new String(result);
    }


    private String findFirstLetter(TextComponent word) {
        return word.getChildTextComponents().get(0).toString();
    }


    // 7

    public List<String> sortWordsByVowelToTotalLengthRatio() {

        return getSortedWords(Comparator.comparingDouble(this::countRatio).thenComparing(String::toString));
    }


    private double countRatio(String word) {
        double count = 0;
        Pattern p = Pattern.compile(VOWEL);
        Matcher m = p.matcher(word);

        while (m.find()) {
            count++;
        }
        return count / word.length();
    }


    // 8

    public List<String> sortVowelStartingWordsByFirstConsonant() {

        return getSortedWords(Comparator.comparing(this::findFirstConsonant));
    }


    private String findFirstConsonant(String word) {
        String first = EMPTY;
        if (word.matches(STARTING_WITH_VOWEL_WORD)) {
            Pattern p = Pattern.compile(CONSONANT);
            Matcher m = p.matcher(word);

            if (m.find()) {
                first = m.group(1);
            }
        }
        return first;
    }


    // 9

    public List<String> sortWordsByCertainLetterRising(char symbol) { //может один метод но разные компараторы ??

        return getSortedWords(Comparator.comparingInt((String o) -> countSymbol(o, symbol))
                .thenComparing(String::toString));
    }


    // 11

    public String deleteSubstringStartingAndEndingWith(char startSymbol, char endSymbol) {
        List<TextComponent> allSentences = getAllOfType(text, TextComponentType.SENTENCE);
        StringBuilder textWithoutSubstrings = new StringBuilder();
        String stringedSentence;

        for (TextComponent sentence : allSentences) {
            stringedSentence = sentence.toString();
            stringedSentence = stringedSentence
                    .replaceAll(OPENING_SQUARE_BRACKETS
                            + startSymbol
                            + Character.toUpperCase(startSymbol)
                            + CLOSING_SQUARE_BRACKETS
                            + ANYTHING
                            + OPENING_SQUARE_BRACKETS
                            + endSymbol
                            + Character.toUpperCase(endSymbol)
                            + CLOSING_SQUARE_BRACKETS, EMPTY);
            textWithoutSubstrings.append(stringedSentence);
        }
        return new String(textWithoutSubstrings);
    }


    // 12

    public TextComponent deleteCertainLengthWordsStartingWithConsonant(int wordLength) {
        List<TextComponent> toBeDeleted = new ArrayList<>();
        List<TextComponent> allSentences = getAllOfType(text, TextComponentType.SENTENCE);

        for (TextComponent sentence : allSentences) {

            for (TextComponent word : sentence.getChildTextComponents()) {

                if ((word.getType() == TextComponentType.WORD)
                        && (word.size() == wordLength)
                        && (!word.toString().startsWith(CONSONANT))) {

                    toBeDeleted.add(word);
                }
            }
            sentence.getChildTextComponents().removeAll(toBeDeleted);
        }
        return text;
    }

    // 13

    public List<String> sortWordsByCertainLetterDescending(char symbol) {

        return getSortedWords(Comparator.comparingInt((String o) -> -countSymbol(o, symbol))
                .thenComparing(String::toString));
    }


    private int countSymbol(String word, char symbol) {
        int count = 0;

        for (char letter : word.toCharArray()) {
            if (letter == symbol) {
                count++;
            }
        }
        return count;
    }


    // 14

    public String findMaxPalindromeSubstring() {
        List<TextComponent> allSentences = getAllOfType(text, TextComponentType.SENTENCE);
        String maxLengthPalindrome = "";

        for (TextComponent sentence : allSentences) {
            String stringedSentence = sentence.toString().replaceAll(PUNCTUATION_AND_SPACES, EMPTY).trim().toLowerCase();
            String foundPalindrome = findLargestSubstringPalindrome(stringedSentence);

            if (foundPalindrome.length() > maxLengthPalindrome.length()) {
                maxLengthPalindrome = foundPalindrome;
            }
        }
        return maxLengthPalindrome;
    }


    private String findLargestSubstringPalindrome(String wordToCheck) {
        int top = wordToCheck.length();
        String palindrome = "";

        for (int i = 0; i < top; i++) {

            for (int j = top; j > i + 1; j--) {

                String maybePalindromeOne = wordToCheck.substring(i, j);
                String maybePalindromeTwo = wordToCheck.substring(top - j, top);

                if (checkIfPalindrome(maybePalindromeOne)
                        && maybePalindromeOne.length() >= palindrome.length()) {
                    palindrome = maybePalindromeOne;
                }

                if (checkIfPalindrome(maybePalindromeTwo)
                        && maybePalindromeTwo.length() >= palindrome.length()) {
                    palindrome = maybePalindromeTwo;
                }
            }
        }
        return palindrome;
    }


    private boolean checkIfPalindrome(String maybePalindrome) {
        return (maybePalindrome.length() >= MIN_PALINDROME_LENGTH)
                && (maybePalindrome.matches(PALINDROME));
    }


    private static String assertEntirety(String pattern) {
        return "(?<=(?=^pattern$).*)".replace("pattern", pattern);
    }


    // 15

    public TextComponent deleteFromWordsFirstLetterEverywhere() {
        List<TextComponent> toBeDeleted = new ArrayList<>();
        List<TextComponent> allWords = getAllOfType(text, TextComponentType.WORD);

        for (TextComponent word : allWords) {

            if (word.getType() == TextComponentType.WORD) {
                String firstLetter = word.getChildTextComponents().get(0).toString();

                for (TextComponent letter : word.getChildTextComponents()) {

                    if (letter.toString().equalsIgnoreCase(firstLetter)) {
                        toBeDeleted.add(letter);
                    }
                }
                word.getChildTextComponents().removeAll(toBeDeleted);
                toBeDeleted.clear();
            }
        }
        return text;
    }


    // 16

    public TextComponent replaceCertainLengthWordsWithSubstring(int sentenceNumber, int wordToReplaceLength, String substring) { // THROWS LOGIC EXCEPTION
        TextComponent theSent = getAllOfType(text, TextComponentType.SENTENCE).get(sentenceNumber);

        for (TextComponent word : theSent.getChildTextComponents()) {

            if ((word.getType() == TextComponentType.WORD)
                    && (word.toString().length() == wordToReplaceLength)) {
                word.getChildTextComponents().clear();

                for (char letter : substring.toCharArray()) {
                    word.addChildTextComponent(new LeafTextEntity(letter, TextComponentType.LETTER));
                }
            }
        }
        return text;
    }


    // вынести в отдельный класс,, !!

    private List<String> getSortedWords(Comparator<String> comparator) {
        List<String> allWords = getAllStringsOfType(text, TextComponentType.WORD);

        allWords.sort(comparator);
        return allWords;
    }


    private List<TextComponent> getAllOfType(TextComponent textComponent, TextComponentType type) {
        List<TextComponent> resultList = new ArrayList<>();

        if (textComponent.getType() == type) {
            resultList.add(textComponent);

        } else if ((textComponent.getType() != TextComponentType.PUNCTUATION_CHAR)
                && (textComponent.getType() != TextComponentType.LETTER)) {

            for (TextComponent child : textComponent) {
                resultList.addAll(getAllOfType(child, type));
            }
        }
        return resultList;
    }


    private List<String> getAllStringsOfType(TextComponent textComponent, TextComponentType type) {
        List<TextComponent> foundTextComponents = getAllOfType(textComponent, type);
        List<String> resultList = new ArrayList<>();

        for (TextComponent component : foundTextComponents) {
            resultList.add(component.toString().toLowerCase());
        }
        return resultList;
    }

}
