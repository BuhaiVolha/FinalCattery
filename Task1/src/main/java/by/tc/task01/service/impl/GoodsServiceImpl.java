package by.tc.task01.service.impl;

import by.tc.task01.dao.GoodsDAO;
import by.tc.task01.dao.DAOFactory;
import by.tc.task01.entity.Goods;
import by.tc.task01.entity.criteria.Criteria;
import by.tc.task01.service.GoodsService;
import by.tc.task01.service.validation.Validator;

import java.util.List;

public class GoodsServiceImpl implements GoodsService {

	@Override
	public <E> List<Goods> find(Criteria<E> criteria) {
		if (!Validator.criteriaValidator(criteria)) {
			return null;
		}
		
		DAOFactory factory = DAOFactory.getInstance();
		GoodsDAO goodsDAO = factory.getGoodsDAO();

		return goodsDAO.find(criteria);
	}

}

//you may add your own new classes
