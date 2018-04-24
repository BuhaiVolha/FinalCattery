package by.tc.task01.dao.impl;

import by.tc.task01.dao.GoodsDAO;
import by.tc.task01.entity.Goods;
import by.tc.task01.entity.criteria.Criteria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsDAOImpl implements GoodsDAO {
    private static final String DATABASE_FILE_NAME = "appliances_db.txt";

    public <E> List<Goods> find(Criteria<E> criteria) {
        ClassLoader classLoader = GoodsDAO.class.getClassLoader();
        GoodsCreator goodsCreator = GoodsCreator.getInstance();

        String goodsType = criteria.getGoodsTypeString();
        List<Goods> foundGoods = new ArrayList<>();

        Map<String, String> parametersParsedFromLine;
        Map<String, String> parametersToFind = criteria.getCriteria();

        try (InputStream is = classLoader.getResourceAsStream(DATABASE_FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String lineFromText;

            while ((lineFromText = reader.readLine()) != null) {
                boolean typeNeededToBeFound = false;

                if (!lineFromText.isEmpty()) {

                    if (lineFromText.startsWith(goodsType)) {
                        parametersParsedFromLine = makeKeyValuePairsFrom(lineFromText);

                        if (parametersParsedFromLine.entrySet().containsAll(parametersToFind.entrySet())) {

                            if (goodsType.isEmpty()) {
                                goodsType = findType(lineFromText);
                                typeNeededToBeFound = true;
                            }
                            foundGoods.add(goodsCreator.createGoodsAndParameterize(goodsType, parametersParsedFromLine));
                        }
                    }
                }
                if (typeNeededToBeFound) {
                    goodsType = "";
                }
            }

        } catch (IOException e) {
            //throw new FileNotFoundException("File not found");
            System.out.println("5555");
        }
        return foundGoods;
    }


    private Map<String, String> makeKeyValuePairsFrom(String line) {
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

    private String findType(String line) {
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
                values.add(string.substring(substringStartIndex, substringEndIndex).trim());
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
                String key = string.substring(firstIndex, endIndex).replaceAll("\\s+", "");

                keys.add(key);
            }
        }
        return keys;
    }
}