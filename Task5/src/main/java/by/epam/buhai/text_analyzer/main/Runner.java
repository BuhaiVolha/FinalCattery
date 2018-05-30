package by.epam.buhai.text_analyzer.main;

import by.epam.buhai.text_analyzer.entity.*;
import by.epam.buhai.text_analyzer.parser.BaseParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Runner {
    private static final String CONSONANTS = "bcdfghjklmnpqrtsvwxz";
    public static void main(String[] args) {


        String text2 = "Infinity is something which is not yet perceivable to humans. It is beyond our " +
                "understanding and we are not able to define it, " +
                "hence we {sometimes} say it to be not defined. \nGiven so, something even beyond our " +
                "understanding is greater than infinity, maybe it is infinity for infinity and 90.55 infinity " +
                "for infinity is still 9 infinity for us. But as infinity for infinity is (just) different from " +
                "infinity, we can't say that infinity is equal to infinity. \nSo 2,23 times infinity is also " +
                "infinity for us, which is not true. Hence, infinity is not equal to infinity?!!";
        String s = "Эволюция снабдила нас интуицией в отношении повседневной физики, жизненно важной для наших далеких " +
                "предков; поэтому, как только мы выходим за рамки повседневности, мы вполне можем ожидать странностей." +
                "\nПростейшая и самая популярная космологическая модель предсказывает, что у нас есть двойник в галактике," +
                " удаленной на расстояние порядка 10 в степени 1028 метров. Расстояние столь велико, что находится за " +
                "пределами досягаемости астрономических наблюдений, но это не делает нашего двойника менее реальным. " +
                "\nПредположение основано на теории вероятности без привлечения представлений современной физики. " +
                "Это предложение с палиндромом - допотопный. Принимается лишь допущение, что пространство бесконечно и заполнено материей.";
        String sent = "i r.";

        TextComponent parsedText = BaseParser.parse(text2);
        //System.out.println(parsedText);
        Iterator<TextComponent> iterator = parsedText.iterator();
        while (iterator.hasNext()) {
            TextComponent c = iterator.next();
            if (c.getType() == TextComponentType.SENTENCE) {
                //if (!c.isLeafTextEntity()) {
                    System.out.println(c);
                //}
            }
        }


        while (iterator.hasNext()) {
            TextComponent c = iterator.next();
            if (c.getType() == TextComponentType.LETTER) {
                System.out.println(c);
            }
        }


        //System.out.println(swapWords(parsedText));
        //System.out.println(removeWords(parsedText, 2));
    }


    public static TextComponent swapWords(TextComponent text) { //если одно слово или предложение
        for (TextComponent p : text.getChildTextComponents()) {
            for (TextComponent s : p.getChildTextComponents()) {
                TextComponent temp = s.getChildTextComponents().get(findLastWordPosition(s));
                s.getChildTextComponents().set(findLastWordPosition(s), s.getChildTextComponents().get(0));
                s.getChildTextComponents().set(0, temp);
            }
        }
        return text;
    }

    private static int findLastWordPosition(TextComponent sent) {
        int lastPos = sent.getChildTextComponents().size() - 1;
        int index = 0;

        for (int i = lastPos; i >= 0; i--) {
            if (sent.getChildTextComponents().get(i).getType() == TextComponentType.WORD) {
                index = i;
                break;
            }
        }
        return index;
    }

//
//    public static List<TextComponent> getAllSentencPart(TextComponent comp) {
//        return getAllOfType(comp, TextComponentType.SENTENCE);
//    }
//
//    public static List<TextComponent> getAllWords(TextComponent comp) {
//        return getAllOfType(comp, TextComponentType.WORD);
//    }

//    public static List<TextComponent> getAllOfType(TextComponent textComponent,
//                                               TextComponentType type) {
//        List<TextComponent> resultList = new ArrayList<>();
//        if (textComponent.getType() == type) {
//            resultList.add(textComponent);
//        } else if (textComponent.getType() != TextComponentType.PUNCTUATION_CHAR
//                && textComponent.getType() != TextComponentType.LETTER
//                && textComponent.getType() != TextComponentType.DIGIT) {
//
//            CompositeTextEntity textEntity = (CompositeTextEntity)textComponent;
//            for (TextComponent child : textEntity) {
//                resultList.addAll(getAllOfType(child, type));
//            }
//        }
//        return resultList;
//    }


//    public static List<String> getAllOfType(TextComponent textComponent,
//                                                   TextComponentType type) {
//        List<String> resultList = new ArrayList<>();
//
//        if (textComponent.getType() == type) {
//            resultList.add(textComponent.build());
//
//        } else if (textComponent.getType() != TextComponentType.PUNCTUATION_CHAR
//                && textComponent.getType() != TextComponentType.LETTER
//                && textComponent.getType() != TextComponentType.DIGIT) {
//
//            CompositeTextEntity textEntity = (CompositeTextEntity)textComponent;
//            for (TextComponent child : textEntity) {
//                resultList.addAll(getAllOfType(child, type));
//            }
//        }
//        return resultList;
//    }

        public static List<TextComponent> getAllOfType(TextComponent textComponent,
                                                   TextComponentType type) {
        List<TextComponent> resultList = new ArrayList<>();

        if (textComponent.getType() == type) {
            resultList.add(textComponent);

        } else if (textComponent.getType() != TextComponentType.PUNCTUATION_CHAR
                && textComponent.getType() != TextComponentType.LETTER) {

            CompositeTextEntity compositeTextEntity = (CompositeTextEntity)textComponent;
            for (TextComponent child : compositeTextEntity) {
                resultList.addAll(getAllOfType(child, type));
            }
        }
        return resultList;
    }


    public static TextComponent removeWords(TextComponent text, int wordLength) {
        List<TextComponent> toRemove = new ArrayList<>();

        for (TextComponent p : text.getChildTextComponents()) {
                for (TextComponent sp : p.getChildTextComponents()){
                    for (TextComponent g : sp.getChildTextComponents()) {

                        if (g.getType() == TextComponentType.WORD && g.size() == wordLength - 1 &&
                                g.toString().startsWith("i")){
                            toRemove.add(g);
                        }
                    }
                    sp.getChildTextComponents().removeAll(toRemove);
                }
        }
        return text;
    }


    private static TextComponent findFirstLetter(TextComponent word) {
        return word.getChildTextComponents().get(0);
    }

    private static boolean isConsonant(TextComponent l){
        System.out.println(l);
        return CONSONANTS.contains(l.toString().toLowerCase());
    }


}
