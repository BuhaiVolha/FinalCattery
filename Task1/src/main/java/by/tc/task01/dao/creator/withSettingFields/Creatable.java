package by.tc.task01.dao.creator.withSettingFields;

import by.tc.task01.entity.Goods;
import java.util.Map;

public interface Creatable {
    Goods parameterize(Map<String, String> parameters);
}
