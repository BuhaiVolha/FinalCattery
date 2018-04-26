package by.tc.task01.dao.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GoodsParser {
    private static GoodsParser parser;

    private GoodsParser() {}

    public static GoodsParser getParser() {
        if (parser == null) {
            parser = new GoodsParser();
        }
        return parser;
    }


    // this method parses a line to find a type of goods
    // like Oven in "Oven: powerConsumption=1000, " etc

    public String findTypeIn(String line) {
        return line.split(":")[0].trim();
    }


    public Map<String, String> makeKeysLookLikeFields(Map<String, String> parameters) {
        Map<String, String> convertedParameters = new HashMap<>();

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            convertedParameters.put(formatKey(entry.getKey()), entry.getValue());
        }
        return convertedParameters;
    }


    // converts a string like power_consumption
    // into a string powerConsumption
    // in order to match the fields of the class

    private String formatKey(String key) {
        key = key.toLowerCase();
        Pattern p = Pattern.compile("_([a-zA-Z])");
        Matcher m = p.matcher(key);
        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase());
        }
        m.appendTail(sb);

        return new String(sb);
    }

    public Map<String, String> makeKeyValuePairsFrom(String line) {
        Map<String, String> parametersList = new HashMap<>();

        List<String> keys = makeKeysList(line);
        List<String> values = makeValuesList(line);

        for (int i = 0; i < keys.size(); i++) {
            parametersList.put(keys.get(i), values.get(i));
        }
        return parametersList;
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
