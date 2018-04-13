package by.epam.training.model;

import by.epam.training.entity.Appliance;
import by.epam.training.entity.ApplianceCreator;
import by.epam.training.entity.ApplianceType;
import by.epam.training.entity.SearchCriterion;
import by.epam.training.exception.ItemCreationFailedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    private List<String> textFromFile;
    private Map<String, String> parametersList;
    private List<String> keys;
    private List<String> values;

    Parser(List<String> textFromFile) {
        this.textFromFile = textFromFile;
    }

    // this method creates a list with appliances
    // that match a search criterion
    // or, if there is none, returns a list
    // with all appliances of a given type
    public List<Appliance> makeApplianceList(ApplianceType applianceType, SearchCriterion criterion) throws ItemCreationFailedException {
        List<Appliance> appliances = new ArrayList<>();
        ApplianceCreator applianceCreator = ApplianceCreator.getInstance();

        for (String string : textFromFile) {
            makeKeysList(string);

            // in order to find lines with a particular type of appliance
            if (string.startsWith(applianceType.getDescription())) {

                if (string.contains(criterion.getSearchCriterionString())) {
                    makeValuesList(string);
                    parametersList = makeParametersList();
                    appliances.add(applianceCreator.createAppliance(applianceType, parametersList));
                }
            }
        }
        return appliances;
    }

    // this method creates a list of values
    // by creating substrings from = to , or ;
    private void makeValuesList(String string) {
        values = new ArrayList<>();
        int top = string.length();

        for (int i = 0; i < top; i++) {
            if (string.charAt(i) == '=') {
                int firstIndex = i + 1;
                int endIndex = 0;

                for (int j = i; j < top; j++) {
                    if ((string.charAt(j) == ',')
                            || (string.charAt(j) == ';')) {
                        endIndex = j;
                        break;
                    }
                }
                values.add(string.substring(firstIndex, endIndex).replaceAll("\\s+", ""));
            }
        }
    }


    // this method creates a list of keys
    // by creating substrings from : and , to = ;
    private void makeKeysList(String string) {
        keys = new ArrayList<>();
        int top = string.length();

        for (int i = 0; i < top; i++) {
            if ((string.charAt(i) == ':')
                    || (string.charAt(i) == ',')) {
                int firstIndex = i + 1;
                int endIndex = 0;

                for (int j = i; j < top; j++) {
                    if (string.charAt(j) == '=') {
                        endIndex = j;
                        break;
                    }
                }
                String key = string.substring(firstIndex, endIndex).replaceAll("\\s+", "");
                keys.add(formatKey(key));
            }
        }
    }


    // converts a string like power_consumption
    // into a string powerConsumption
    // in order to match the fields of the class
    // that will be created by a reflection later
    private String formatKey(String key) {
        char[] keyCharsArray = key.toLowerCase().toCharArray();
        int top = keyCharsArray.length;

        for (int k = 0; k < top; k++) {
            if (keyCharsArray[k] == '_') {
                keyCharsArray[k + 1] = Character.toUpperCase(keyCharsArray[k + 1]);
                System.arraycopy(keyCharsArray, k + 1, keyCharsArray, k, top - k - 1);
                keyCharsArray[top - 1] = ' ';
            }
        }
        return new String(keyCharsArray).trim();
    }


    // this method creates a map from values and keys list
    private Map<String, String> makeParametersList() {
        Map<String, String> parametersList = new HashMap<>();

        for (int i = 0; i < keys.size(); i++) {
            parametersList.put(keys.get(i), values.get(i));
        }
        return parametersList;
    }

    public Map<String, String> getParametersList() {
        return parametersList;
    }
}

