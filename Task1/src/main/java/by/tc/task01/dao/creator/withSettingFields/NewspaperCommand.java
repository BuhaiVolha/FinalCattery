package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Newspaper;
import by.tc.task01.entity.Sellable;
import by.tc.task01.entity.criteria.Parameters;

import java.util.Map;

public class NewspaperCommand extends Command {
    @Override
    public Sellable createGoodsWith(Map<String, String> parameters) {
        Newspaper newspaper = new Newspaper();

        newspaper.setPaidOrFree(parameters.get(Parameters.GoodsType.Newspaper.PAID_OR_FREE.toString()));
        newspaper.setPeriodicity(parameters.get(Parameters.GoodsType.Newspaper.PERIODICITY.toString()));
        newspaper.setTitle(parameters.get(Parameters.GoodsType.Newspaper.TITLE.toString()));

        return newspaper;
    }
}
