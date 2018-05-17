package by.tc.task01.dao.impl;

import by.tc.task01.dao.GoodsDAO;
import by.tc.task01.dao.creator.Creator;
import by.tc.task01.dao.creator.withSettingFields.CreatorSettingFields;
import by.tc.task01.dao.dao_exception.ReadingFileFailedException;
import by.tc.task01.dao.utils.GoodsParser;
import by.tc.task01.entity.Goods;
import by.tc.task01.entity.criteria.Criteria;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GoodsDAOImpl implements GoodsDAO {
    private static final Logger LOGGER = LogManager.getLogger(GoodsDAOImpl.class);
    private ClassLoader classLoader = GoodsDAO.class.getClassLoader();
    private GoodsParser parser = GoodsParser.getParser();
    private static final String DATABASE_FILE_NAME = "appliances_db.txt";
    private static final String UNKNOWN_TYPE = "";

    /////////////// Two types of Creator are available! /////////////////////////////
    private Creator goodsCreator = new CreatorSettingFields();
    //private Creator goodsCreator = new CreatorWithReflection();

    public <E> List<Goods> find(Criteria<E> criteria) throws ReadingFileFailedException {
        List<Goods> foundGoods = new ArrayList<>();
        String goodsType = criteria.getGoodsType();

        Map<String, String> parametersParsedFromLine;
        Map<String, String> parametersToFind = criteria.getCriteria();

        try (InputStream is = classLoader.getResourceAsStream(DATABASE_FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String lineFromText;

            while ((lineFromText = reader.readLine()) != null) {
                boolean typeNeededToBeFound = false;

                if (!lineFromText.isEmpty()) {

                    if (lineFromText.startsWith(goodsType)) {
                        parametersParsedFromLine = parser.makeKeyValuePairsFrom(lineFromText);

                        if (parametersParsedFromLine.entrySet().containsAll(parametersToFind.entrySet())) {

                            if (goodsType.isEmpty()) {
                                goodsType = parser.findTypeIn(lineFromText);
                                typeNeededToBeFound = true;
                            }
                            Goods createdGoods = goodsCreator.createGoodsAndParameterize(goodsType, parametersParsedFromLine);
                            foundGoods.add(createdGoods);
                        }
                    }
                }
                if (typeNeededToBeFound) {
                    goodsType = UNKNOWN_TYPE;
                }
            }

        } catch (Exception e) {
            LOGGER.log(Level.FATAL, "Reading file failed");
            throw new ReadingFileFailedException(e);
        }
        return foundGoods;
    }
}