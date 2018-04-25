package by.tc.task01.dao.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsParser {

    public Map<String, String> makeKeyValuePairsFrom(String line) {
        Map<String, String> parametersList = new HashMap<>();

        List<String> keys = makeKeysList(line);
        List<String> values = makeValuesList(line);

        for (int i = 0; i < keys.size(); i++) {
            parametersList.put(keys.get(i), values.get(i));
        }
        return parametersList;
    }


    // this method parses a line to find a type of goods
    // like Oven in "Oven: powerConsumption=1000, " etc

    public String findType(String line) {
        return line.split(":")[0].trim();
    }


    // this method creates a list of values
    // by creating substrings from = to , or ;

    private List<String> makeValuesList(String string) {
        List<String> values = new ArrayList<>();
        int top = string.length();

        for (int i = 0; i < top; i++) {
            if (string.charAt(i) == '=') {
                int substringStartIndex = i + 1;
                int substringEndIndex = 0;

                for (int j = i; j < top; j++) {
                    if ((string.charAt(j) == ',')
                            || (string.charAt(j) == ';')) {
                        substringEndIndex = j;
                        break;
                    }
                }
                String value = string.substring(substringStartIndex, substringEndIndex).trim();
                values.add(value);
            }
        }
        return values;
    }


    // this method creates a list of keys
    // by creating substrings from : and , to = ;

    private List<String> makeKeysList(String string) {
        List<String> keys = new ArrayList<>();
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
                String key = string.substring(firstIndex, endIndex).trim();

                keys.add(key);
            }
        }
        return keys;
    }
}
