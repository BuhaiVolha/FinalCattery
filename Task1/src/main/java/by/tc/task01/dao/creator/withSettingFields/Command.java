package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Goods;
import java.util.Map;

public abstract class Command {
    abstract Goods createGoodsWith(Map<String, String> parameters);
}
