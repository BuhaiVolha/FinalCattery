package by.tc.task01.dao;

import by.tc.task01.dao.impl.GoodsDAOImpl;

public final class DAOFactory {
	private static final DAOFactory instance = new DAOFactory();

	private final GoodsDAO goodsDAO = new GoodsDAOImpl();
	
	private DAOFactory() {}

	public GoodsDAO getGoodsDAO() {
		return goodsDAO;
	}

	public static DAOFactory getInstance() {
		return instance;
	}
}
