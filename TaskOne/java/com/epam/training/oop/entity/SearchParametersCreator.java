package com.epam.training.oop.entity;

import com.epam.training.oop.entity.criteria.CriteriaMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchParametersCreator {

    public static <T> List<String> createSearchParameters(CriteriaMap criteria) {
        Map<T, Object> parametersMap = criteria.getCriteria();
        List<String> criteriaList = new ArrayList<>();

        criteriaList.add(criteria.getGoodsType());

        for (Map.Entry<T, Object> parameters : parametersMap.entrySet()) {
            String type = parameters.getKey().toString();
            String value = parameters.getValue().toString();
            StringBuilder regExpBuilder = new StringBuilder();
            regExpBuilder
                    .append(".*\\s")
                    .append(type)
                    .append("=")
                    .append(value.toUpperCase())
                    .append("\\s.*");
            String searchParameter = new String(regExpBuilder);

            criteriaList.add(searchParameter);
        }
        return criteriaList;
    }
}
