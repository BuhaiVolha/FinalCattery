package by.epam.training.model;

import java.util.HashMap;
import java.util.Map;

public class SearchCriterion {
    private Map<String, String> searchCriterion;

    public SearchCriterion() {
        searchCriterion = new HashMap<>();
    }


    public SearchCriterion(String parameter, String value) {
        searchCriterion = new HashMap<>();
        searchCriterion.put(parameter, value);
    }


    public void addElements(String parameter, String value) {
        searchCriterion.put(parameter, value);
    }


    public Map<String, String> getSearchCriterionMap() {
        return searchCriterion;
    }


    public void clearCriterion() {
        searchCriterion.clear();
    }


    @Override
    public String toString() {
        return searchCriterion.keySet()
        + "="
        + searchCriterion.values();
    }
}
