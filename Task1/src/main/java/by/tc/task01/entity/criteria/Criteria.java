package by.tc.task01.entity.criteria;

import by.tc.task01.exception.NullValueException;

import java.util.HashMap;
import java.util.Map;

public class Criteria<E> {
	private Class<E> goodsType;
	private Map<String, String> criteria = new HashMap<>();

	public Criteria(Class<E> goodsType) {
		this.goodsType = goodsType;
	}
	
	public Class<E> getGoodsTypeClass(){
		return goodsType;
	}

    public String getGoodsTypeString(){
	    if (goodsType.getSimpleName().equals("Any")) {
	        return "";
        }
        return goodsType.getSimpleName();
    }

	public void add(E criterion, Object value) throws NullValueException {
	    if (value == null) {
	        throw new NullValueException("The input value is null");
        }
		criteria.put(criterion.toString(), value.toString());
	}

    public Map<String, String> getCriteria() {
        return criteria;
    }
}
