package com.epam.training.oop.entity.criteria;

import java.util.HashMap;
import java.util.Map;

public class CriteriaMap<T extends SearchCriteria> {
    private String GoodsType;
    private Map<T, String> criteria = new HashMap<>();

    public boolean add(T searchCriteria, Object value) {
        boolean isAdded = false;

        if (value != null) {
            criteria.put(searchCriteria, value.toString());
            isAdded = true;
        }
        return isAdded;
    }

    public String getGoodsType() {
        return GoodsType;
    }

    public void setGoodsType(String goodsType) {
        this.GoodsType = goodsType;
    }

    public Map<T, String> getCriteria() {
        return criteria;
    }
}
