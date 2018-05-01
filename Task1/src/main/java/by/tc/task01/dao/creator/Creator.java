package by.tc.task01.dao.creator;

import by.tc.task01.entity.Sellable;
import by.tc.task01.exception.TaskException;

import java.util.Map;

abstract public class Creator {

    public abstract Sellable createGoodsAndParameterize(String type, Map<String, String> parameters) throws TaskException;
}
