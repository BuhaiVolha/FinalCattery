package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.Newspaper;
import by.tc.task01.entity.criteria.Parameters;

import java.util.Map;

public class NewspaperCreator implements Creatable {
    @Override
    public Goods parameterize(Map<String, String> parameters) {
        Newspaper newspaper = new Newspaper();

        newspaper.setPaidOrFree(parameters.get(Parameters.GoodsType.Newspaper.PAID_OR_FREE.toString()));
        newspaper.setPeriodicity(parameters.get(Parameters.GoodsType.Newspaper.PERIODICITY.toString()));
        newspaper.setTitle(parameters.get(Parameters.GoodsType.Newspaper.TITLE.toString()));

        return newspaper;
    }
}
