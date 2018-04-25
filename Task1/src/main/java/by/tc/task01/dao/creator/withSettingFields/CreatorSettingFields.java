package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.dao.creator.Creator;
import by.tc.task01.entity.Goods;
import java.util.Map;

public class CreatorSettingFields extends Creator {
    private CommandProvider provider = new CommandProvider();
    Command command;

    public CreatorSettingFields() {
    }

    public Goods createGoodsAndParameterize(String goodsType, Map<String, String> parameters) {
        command = provider.getCommandFor(goodsType);
        return command.createGoodsWith(parameters);
    }
}
