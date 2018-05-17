package by.tc.task01.service.impl;

import by.tc.task01.dao.DAOFactory;
import by.tc.task01.dao.GoodsDAO;
import by.tc.task01.dao.dao_exception.ReadingFileFailedException;
import by.tc.task01.entity.Goods;
import by.tc.task01.service.GoodsService;
import by.tc.task01.service.service_exception.FindingGoodsFailedException;
import by.tc.task01.service.validation.Validator;
import by.tc.task01.entity.criteria.Criteria;

import java.util.Collections;
import java.util.List;

public class GoodsServiceImpl implements GoodsService {

    @Override
    public <E> List<Goods> find(Criteria<E> criteria) throws FindingGoodsFailedException {
        if (!Validator.validateCriteria(criteria)) {
            return Collections.emptyList();
        }

        DAOFactory factory = DAOFactory.getInstance();
        GoodsDAO goodsDAO = factory.getGoodsDAO();
        List<Goods> goods;
        try {
            goods = goodsDAO.find(criteria);
        } catch (ReadingFileFailedException e) {
            throw new FindingGoodsFailedException(e);
        }

        return goods;
    }
}
