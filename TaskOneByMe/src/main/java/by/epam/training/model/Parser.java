package by.epam.training.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    private List<String> keys;
    private List<String> values;
    private static Parser instance;

    private Parser() {
    }

    public static Parser getInstance() {
        if (instance == null) {
            instance = new Parser();
        }
        return instance;
    }


    // this method creates a map from values and keys list

    public Map<String, String> makeParametersList(String line) {
        Map<String, String> parametersList = new HashMap<>();

        makeKeysList(line);
        makeValuesList(line);

        for (int i = 0; i < keys.size(); i++) {
            parametersList.put(keys.get(i), values.get(i));
        }
        return parametersList;
    }

    // this method parses a line to find a type of goods
    // like Oven in "Oven: powerConsumption=1000, " etc
    // also it trims it and makes it uppercase
    // in order to match enum type
    // so it will return OVEN

    public String findType(String line) {
        return line.substring(0, findIndexOfTypeEnding(line)).toUpperCase().trim();
    }

    // this method finds an index of : character
    // to help findType method to parse a line

    private int findIndexOfTypeEnding(String line) {
        char[] arr = line.toCharArray();
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ':') {
                index = i;
                break;
            }
        }
        return index;
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
                values.add(string.substring(firstIndex, endIndex).trim());
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
}
