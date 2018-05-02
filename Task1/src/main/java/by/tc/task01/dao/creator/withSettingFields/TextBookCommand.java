package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.TextBook;
import by.tc.task01.entity.criteria.Parameters;

import java.util.Map;

public class TextBookCommand extends Command {
    @Override
    public Goods createGoodsWith(Map<String, String> parameters) {
        TextBook textBook = new TextBook();

        textBook.setAuthor(parameters.get(Parameters.TextBook.AUTHOR.toString()));
        textBook.setNumberOfPages(Double.parseDouble(parameters.get(Parameters.TextBook.NUMBER_OF_PAGES.toString())));
        textBook.setSubject(parameters.get(Parameters.TextBook.SUBJECT.toString()));
        textBook.setTitle(parameters.get(Parameters.TextBook.TITLE.toString()));

        return textBook;
    }
}
