package by.tc.task01.service;

import by.tc.task01.service.impl.GoodsServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final GoodsService goodsService = new GoodsServiceImpl();

    private ServiceFactory() {
    }

    public GoodsService getGoodsService() {
        return goodsService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

}
