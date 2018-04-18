package by.epam.training.model;

import by.epam.training.entity.Goods;
import by.epam.training.utils.GoodsType;

import by.epam.training.exception.ItemNotFoundException;
import by.epam.training.exception.NoSuchTypeException;
import by.epam.training.exception.TaskException;

import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searcher {
    private GoodsType goodsType;
    private List<Goods> goodsList;
    private SearchCriterion searchCriterion;
    private GoodsDAO GoodsDAO;

    public Searcher() {
        GoodsDAO = new GoodsDAO();
        searchCriterion = new SearchCriterion();
    }


    // this method looks for certain type of goods
    // that match the criteria
    // or - if none - just all goods of the type

    public void findGoods() throws TaskException {
        if (searchCriterion == null) {
            searchCriterion = new SearchCriterion("", "");
        }
        goodsList = GoodsDAO.makeGoodsList(goodsType, searchCriterion);
    }


    // this method allows to specify the type of goods

    public void addGoodsType(String type) throws TaskException {
        try {
            searchCriterion.clearCriterion();

            if (type.contains(" ")) {
                type = type.replaceAll(" ", "_");
            }
            goodsType = GoodsType.valueOf(type.toUpperCase());

        } catch (Exception e) {
            throw new NoSuchTypeException("Wrong type of appliance");
        }
    }


    public List<Goods> showResults() throws TaskException {
        if (goodsList.size() == 0) {
            throw new ItemNotFoundException("No such item");
        }
        return goodsList;
    }


    // this method allows to add one or several criteria

    public void addSearchCriterion(String parameter, String value) {
        parameter = parameter.toLowerCase();

        if (parameter.contains(" ")) {
            parameter = parameter.replaceAll(" ", "_");
            parameter = formatString(parameter);

        }
        searchCriterion.addElements(parameter, value);
    }


    // this method convert a line like
    // power_consumption to powerConsumption

    private String formatString(String parameter) {
        Pattern p = Pattern.compile( "_([a-zA-Z])" );
        Matcher m = p.matcher(parameter);
        StringBuffer sb = new StringBuffer();

        while (m.find()) {
            m.appendReplacement(sb, m.group(1).toUpperCase());
        }
        m.appendTail(sb);
        return new String(sb);
    }

    public SearchCriterion getSearchCriterion() {
        return searchCriterion;
    }


    public GoodsType getGoodsType() {
        return goodsType;
    }
}
