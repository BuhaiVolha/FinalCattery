package by.epam.buhai.text_analyzer.main;

import by.epam.buhai.text_analyzer.entity.*;
import by.epam.buhai.text_analyzer.parser.BaseParser;
import by.epam.buhai.text_analyzer.service.ServiceFactory;
import by.epam.buhai.text_analyzer.service.TextAnalyzerService;


public class Runner {

    public static void main(String[] args) {

        String text2 = "\tInfinity is something which is not kit-kat (yet) perceivable to humans? It is beyond our " +
                "understanding and we are not able to define it, " +
                "hence we {sometimes} say it to be not defined. \nGiven so, something even beyond our " +
                "understanding is greater than infinity, maybe it is infinity for infinity and infinity " +
                "for infinity is still 9 infinity for us. But as infinity for infinity is (just) different from " +
                "infinity, we can't say that infinity is equal to infinity. \nSo 2,23 times infinity is also " +
                "infinity for us, which is not true. Hence, infinity is not 10.55 equal - to infinity?!" +
                " Sums are not set as a test on Erasmus or are they.";
        String s = "Эволюция снабдила нас интуицией в отношении повседневной физики, жизненно важной для наших далеких " +
                "предков; поэтому, как только мы выходим за рамки повседневности, мы вполне можем ожидать странностей." +
                "\nПростейшая и самая популярная космологическая модель предсказывает, что у нас есть двойник в галактике," +
                " удаленной на расстояние порядка 10 в степени 1028 метров? Расстояние столь велико, что находится за " +
                "пределами досягаемости астрономических наблюдений, но это не делает нашего двойника менее реальным. " +
                "\nПредположение основано на теории вероятности без привлечения представлений современной физики. " +
                "Это предложение с палиндромом - допотопный. Принимается только допущение, что пространство бесконечно и заполнено материей.";
        String sent = "Great cats live in flats.";

        TextComponent parsedText = BaseParser.parse(text2);
        System.out.println(parsedText);

        ServiceFactory service = ServiceFactory.getServiceFactory();
        TextAnalyzerService analyzer = service.getTextAnalyzerService();

        analyzer.setText(parsedText);
        //System.out.println(analyzer.deleteSubstringStartingAndEndingWith('а', 'а'));


//        TextComponentVisitor visitor = new ReportVisitor();
//        parsedText.accept(visitor);
//        System.out.println(visitor.getReport());
//
//        Iterator<TextComponent> iterator = parsedText.iterator();
//        while (iterator.hasNext()) {
//            TextComponent c = iterator.next();
//            c.accept(visitor);
//        }


//        Iterator<TextComponent> iterator = parsedText.iterator();
//        while (iterator.hasNext()) {
//            TextComponent c = iterator.next();
//            if (c.getType() == TextComponentType.WORD) {
//                //if (!c.isLeafTextEntity()) {
//                    System.out.println(c);
//                //}
//            }
//        }
    }
}
