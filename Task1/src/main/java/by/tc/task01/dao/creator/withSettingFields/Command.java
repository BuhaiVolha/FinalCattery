package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Sellable;
import java.util.Map;

public abstract class Command {
    abstract Sellable createGoodsWith(Map<String, String> parameters);
}
