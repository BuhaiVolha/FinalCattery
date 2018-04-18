package by.epam.training.model;

import by.epam.training.entity.Goods;
import by.epam.training.utils.GoodsCreator;
import by.epam.training.utils.GoodsType;

import by.epam.training.exception.FileNotFoundException;
import by.epam.training.exception.TaskException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoodsDAO {
    private static final String DATABASE_FILE_NAME = "appliances_db.txt";


    // this method creates a list with appliances
    // that match a search criterion

    public List<Goods> makeGoodsList(GoodsType goodsType, SearchCriterion criterion) throws TaskException {
        ClassLoader classLoader = GoodsDAO.class.getClassLoader();

        GoodsCreator goodsCreator = GoodsCreator.getInstance();
        Parser parser = Parser.getInstance();

        List<Goods> goodsList = new ArrayList<>();

        // this is a map with fields names and their values, parsed from the line
        // for example, freezerCapacity, weight

        Map<String, String> parametersMap;

        // this is a map with search criteria
        // that are formatted in order to match fields name
        // for example, freezerCapacity, weight

        Map<String, String> searchCriterionMap = criterion.getSearchCriterionMap();

        try (InputStream is = classLoader.getResourceAsStream(DATABASE_FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            boolean typeNeededToBeFound = false;

            while ((line = reader.readLine()) != null) {

                // checking for a certain type of goods

                if (line.startsWith(goodsType.getDescription())) {
                    parametersMap = parser.makeParametersList(line);

                    // checking if a search criteria set is a subset of a parameters set

                    if (parametersMap.entrySet().containsAll(searchCriterionMap.entrySet())) {

                        // if the type is ANY, the type of the goods with certain criteria
                        // will be found

                        if (goodsType == GoodsType.ANY) {
                            goodsType = GoodsType.valueOf(parser.findType(line));
                            typeNeededToBeFound = true;
                        }
                        goodsList.add(goodsCreator.createGoods(goodsType, parametersMap));
                    }
                }
                // erasing the type if it was ANY
                // because you need to find  more goods
                // that match the criteria

                if (typeNeededToBeFound) {
                    goodsType = GoodsType.ANY;
                }
            }

        } catch (IOException e) {
            throw new FileNotFoundException("File not found");
        }
        return goodsList;
    }
}
