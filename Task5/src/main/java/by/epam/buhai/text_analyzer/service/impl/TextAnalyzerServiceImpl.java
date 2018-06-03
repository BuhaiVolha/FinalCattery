package by.epam.buhai.text_analyzer.service.impl;

import by.epam.buhai.text_analyzer.entity.LeafTextEntity;
import by.epam.buhai.text_analyzer.entity.TextComponent;
import by.epam.buhai.text_analyzer.entity.TextComponentType;
import by.epam.buhai.text_analyzer.main.Runner;
import by.epam.buhai.text_analyzer.service.TextAnalyzerService;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextAnalyzerServiceImpl implements TextAnalyzerService {  // конструктор кула сетится текст
    private TextComponent text;

    private static final String CONSONANTS = "([^aioueyаяоэиеёыу])";
    private static final String VOWELS = "[aioueyаяоэиеёыу]";
    private static final String STARTING_WITH_VOWEL_WORD = "\\b[aioueyаяоэиеёыу].+";

    private static final String PALINDROME = "(?x)|(?:(.)add) + chk"
            .replace("add", assertEntirety(".*? (\\1 \\2?)"))
            .replace("chk", assertEntirety("\\2"));
    private static final int MIN_PALINDROME_LENGTH = 3;
    private static final String PUNCTUATION_AND_SPACES = "[\\p{Punct}\\s]";
    private static final String EMPTY = "";

    private static final String QUESTION_SENTENCE = "[.?=\\d+\\s\\wа-яА-Я-,:(){}]+[!?]{1,3}$";
    private static final String NEW_LINE = "\n";
    private static final String SPACE = " ";


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
        Set<TextComponent> allWords = new HashSet<>(getAllOfType(text, TextComponentType.WORD));

        Set<String> firstSentenceWords = new TreeSet<>(String::compareToIgnoreCase); // приходится сет стрингов делать Ловеркэйс
        for (TextComponent word : allSentences.get(0).getChildTextComponents()) {

            if (word.getType() == TextComponentType.WORD) {
                firstSentenceWords.add(word.toString());
            }
        }
        text.getChildTextComponents().get(0).getChildTextComponents().remove(0);

        Set<String> allStringedWords = new TreeSet<>(String::compareToIgnoreCase);
        for (TextComponent word : allWords) {
            allStringedWords.add(word.toString());
        }
        firstSentenceWords.removeAll(allStringedWords);

        return firstSentenceWords;
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

        for (int i = 1; i < allWords.size(); i++) {  //ИЗМЕНИТЬ ЧТОТОООООООООООООООООООО!!!!!!!!!!!!

            if (allWords.get(i - 1).getChildTextComponents().get(0).toString().
                    equalsIgnoreCase(allWords.get(i).getChildTextComponents().get(0).toString())) {
                result.append(allWords.get(i).toString());
            } else {
                result.append(NEW_LINE).append(allWords.get(i).toString());
            }
            result.append(SPACE); //КОНСТАНТЫ
        }
        return new String(result);
    }


    // 7

    public List<String> sortWordsByVowelToTotalLengthRatio() {
        List<TextComponent> allWords = getAllOfType(text, TextComponentType.WORD);
        List<String> allStringedWords = new ArrayList<>();

        for (TextComponent word : allWords) {
            allStringedWords.add(word.toString().toLowerCase());
        }
        allStringedWords.sort(Comparator.comparingDouble(this::countRatio).thenComparing(String::toString));

        return allStringedWords;
    }


    private double countRatio(String word) {
        double count = 0;
        Pattern p = Pattern.compile(VOWELS);
        Matcher m = p.matcher(word);

        while (m.find()) {
            count++;
        }
        return count / word.length();
    }


    // 8

    public List<String> sortVowelStartingWordsByFirstConsonant() {
        List<TextComponent> allWords = getAllOfType(text, TextComponentType.WORD);
        List<String> allStringedWords = new ArrayList<>();

        for (TextComponent word : allWords) {
            allStringedWords.add(word.toString().toLowerCase());
        }

        allStringedWords.sort(Comparator.comparing(this::findFirstConsonant));

        return allStringedWords;
    }


    private String findFirstConsonant(String word) {
        String first = "";
        if (word.matches(STARTING_WITH_VOWEL_WORD)) {
            Pattern p = Pattern.compile(CONSONANTS);
            Matcher m = p.matcher(word);

            if (m.find()) {
                first = m.group(1);
            }
        }
        return first;
    }


    // 9

    public List<String> sortWordsByCertainLetterRising(char symbol) { //может один метод но разные компараторы ??
        List<TextComponent> allWords = getAllOfType(text, TextComponentType.WORD); // приходится лист стрингов делать Ловеркэйс
        List<String> allStringedWords = new ArrayList<>();

        for (TextComponent word : allWords) {
            allStringedWords.add(word.toString().toLowerCase());
        }

        allStringedWords.sort(Comparator.comparingInt(o -> countSymbol(o.toString(), symbol))
                .thenComparing(Object::toString));

//            allWords.sort(Comparator.comparingInt(o -> -countSymbol(o.toString(), symbol))
//                    .thenComparing(Object::toString));

        return allStringedWords;
    }


    // 12

    public TextComponent deleteCertainLengthWordsStartingWithConsonant(int wordLength) {
        List<TextComponent> toBeDeleted = new ArrayList<>();
        List<TextComponent> allSentences = getAllOfType(text, TextComponentType.SENTENCE);

        for (TextComponent sentence : allSentences) {

            for (TextComponent word : sentence.getChildTextComponents()) {

                if ((word.getType() == TextComponentType.WORD)
                        && (word.size() == wordLength)
                        && (!word.toString().startsWith(CONSONANTS))) {

                    toBeDeleted.add(word);
                }
            }
            sentence.getChildTextComponents().removeAll(toBeDeleted);
        }
        return text;
    }

    // 13

    public List<String> sortWordsByCertainLetterDescending(char symbol) { //может один метод но разные компараторы ??
        List<TextComponent> allWords = getAllOfType(text, TextComponentType.WORD); // приходится лист стрингов делать Ловеркэйс
        List<String> allStringedWords = new ArrayList<>();

        for (TextComponent word : allWords) {
            allStringedWords.add(word.toString().toLowerCase());
        }

        allStringedWords.sort(Comparator.comparingInt(o -> -countSymbol(o.toString(), symbol))
                .thenComparing(Object::toString));

//            allWords.sort(Comparator.comparingInt(o -> -countSymbol(o.toString(), symbol))
//                    .thenComparing(Object::toString));

        return allStringedWords;
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
                    //word.addChildTextComponent(new LeafTextEntity(Character.toString(letter), TextComponentType.LETTER));
                    word.addChildTextComponent(new LeafTextEntity(letter, TextComponentType.LETTER));
                }
            }
        }
        return text;
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

}
