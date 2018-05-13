package by.epam.buhai.xml_analyzer.service;

import by.epam.buhai.xml_analyzer.service.impl.AnalyzerServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final AnalyzerService analyzerService = new AnalyzerServiceImpl();

    private ServiceFactory() {
    }

    public AnalyzerService getAnalyzerService() {
        return analyzerService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

}
