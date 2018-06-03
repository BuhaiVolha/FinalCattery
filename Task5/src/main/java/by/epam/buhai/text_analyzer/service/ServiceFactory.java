package by.epam.buhai.text_analyzer.service;

import by.epam.buhai.text_analyzer.service.impl.TextAnalyzerServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory serviceFactory = new ServiceFactory();
    private final TextAnalyzerService textAnalyzerService = new TextAnalyzerServiceImpl();

    private ServiceFactory() {
    }

    public TextAnalyzerService getTextAnalyzerService() {
        return textAnalyzerService;
    }

    public static ServiceFactory getServiceFactory() {
        return serviceFactory;
    }

}
