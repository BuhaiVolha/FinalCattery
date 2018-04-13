package by.epam.training.entity;

import java.util.HashMap;
import java.util.Map;

public class SearchCriterion {
    private Map<String, String> searchCriterion;

    public SearchCriterion(String parameter, String value) {
        searchCriterion = new HashMap<>();
        searchCriterion.put(parameter.toUpperCase(), value);
    }

    // this method returns a string in a special format
    // like DEPTH=30 in order to make possible
    // for a parser to find it in a line from text
    public String getSearchCriterionString() {
        return searchCriterion.toString().replace("{", "").replace("}", "");
    }

    public void setSearchCriterion(Map<String, String> searchCriterion) {
        this.searchCriterion = searchCriterion;
    }

    public String getKey() {
        return searchCriterion.keySet().toString().replace("[", "").replace("]", "");
    }

    @Override
    public String toString() {
        return searchCriterion.keySet()
        + "="
        + searchCriterion.values();
    }
}
