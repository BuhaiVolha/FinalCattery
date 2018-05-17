package by.tc.task01.dao.creator;

import by.tc.task01.dao.dao_exception.GoodsCreationFailedException;
import by.tc.task01.entity.Goods;
import java.util.Map;

abstract public class Creator {

    public abstract Goods createGoodsAndParameterize(String type, Map<String, String> parameters) throws GoodsCreationFailedException;
}
