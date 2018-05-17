package by.tc.task01.entity.criteria;

import by.tc.task01.service.service_exception.CriteriaOutOfBoundsException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Criteria<E> implements Iterable {
    private Class<E> goodsType;
    private Map<String, String> criteria = new HashMap<>();


    public Criteria(Class<E> goodsType) {
        this.goodsType = goodsType;
    }


    public String getGoodsType() {
        if (goodsType.getSimpleName().equals("Any")) {
            return "";
        }
        return goodsType.getSimpleName();
    }


    public void add(E criterion, Object value) {
        if (value == null) {
            value = "";
        }
        criteria.put(criterion.toString(), value.toString());
    }


    public Map<String, String> getCriteria() {
        return criteria;
    }


    public String getParameterByIndex(int index) throws CriteriaOutOfBoundsException {
        Object[] array = makeKeyArray();
        if ((index >= array.length)
                || (index < 0)) {
            throw new CriteriaOutOfBoundsException("Index " + index + " is out of bounds");
        }
        return array[index].toString();
    }


    private Object[] makeKeyArray() {
        return criteria.keySet().toArray();
    }


    public String getValueByIndex(int index) throws CriteriaOutOfBoundsException {
        Object key = getParameterByIndex(index);
        return criteria.get(key.toString());
    }


    @Override
    public Iterator iterator() {
        return criteria.entrySet().iterator();
    }
}
