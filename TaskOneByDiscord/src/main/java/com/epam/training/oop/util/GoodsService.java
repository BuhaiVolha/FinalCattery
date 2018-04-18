package com.epam.training.oop.util;

import com.epam.training.oop.dao.GoodsDAO;
import com.epam.training.oop.entity.Goods;
import com.epam.training.oop.entity.criteria.CriteriaMap;
import com.epam.training.oop.exception.WrongGoodsTypeException;
import com.epam.training.oop.entity.SearchParametersCreator;

import java.util.List;

public class GoodsService {

    public List<Goods> readDevice(CriteriaMap criteria) throws WrongGoodsTypeException {
        List<Goods> goods = null;

        if (Validator.validateCriteria(criteria)) {
            List<String> searchParameters = SearchParametersCreator.createSearchParameters(criteria);
            GoodsDAO goodsDAO = new GoodsDAO();
            goods = goodsDAO.readGoods(searchParameters);
        }

        return goods;
    }
}