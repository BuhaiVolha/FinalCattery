package by.epam.training.model;

import by.epam.training.entity.Appliance;
import by.epam.training.entity.ApplianceType;
import by.epam.training.entity.SearchCriterion;
import by.epam.training.exception.ItemNotFoundException;
import by.epam.training.exception.NoSuchTypeException;
import by.epam.training.exception.TaskException;
import java.util.List;
import java.util.Map;

public class Searcher {
    private ApplianceType applianceType;
    private List<Appliance> appliances;
    private SearchCriterion searchCriterion;
    private Parser parser;

    public Searcher(List<String> textFromFile) {
        parser = new Parser(textFromFile);
    }

    // method finds all the records that match a certain search criterion
    public void findAppliance(String type, String parameter, String value) throws TaskException {
        try {
            applianceType = ApplianceType.valueOf(type.toUpperCase());
            searchCriterion = new SearchCriterion(parameter, value);

        } catch (Exception e) {
            throw new NoSuchTypeException("Wrong type of appliance");
        }
        appliances = parser.makeApplianceList(applianceType, searchCriterion);
    }

    // an overloaded method to provide an opportunity
    // to find all the records for a specific type
    public void findAppliance(String type) throws TaskException {
        try {
            applianceType = ApplianceType.valueOf(type.toUpperCase());
            searchCriterion = new SearchCriterion("", "");
        } catch (Exception e) {
            throw new NoSuchTypeException("Wrong type of appliance");
        }
        appliances = parser.makeApplianceList(applianceType, searchCriterion);
    }

    public List<Appliance> showResults() throws ItemNotFoundException {
        if (appliances.size() == 0) {
            throw new ItemNotFoundException("No such item");
        }
        return appliances;
    }

    public SearchCriterion getSearchCriterion() {
        return searchCriterion;
    }

    public void setSearchCriterion(String parameter, String value) {
        searchCriterion = new SearchCriterion(parameter, value);
    }

    public ApplianceType getApplianceType() {
        return applianceType;
    }

    public Map<String, String> getMap() {
        return parser.getParametersList();
    }
}

