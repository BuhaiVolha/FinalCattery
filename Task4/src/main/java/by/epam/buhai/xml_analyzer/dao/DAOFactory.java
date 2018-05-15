package by.epam.buhai.xml_analyzer.dao;

import by.epam.buhai.xml_analyzer.dao.impl.AnalyzerDAOImpl;

public final class DAOFactory {
    private final AnalyzerDAO analyzerDAO = new AnalyzerDAOImpl();

    private DAOFactory() {
    }

    public AnalyzerDAO getAnalyzerDAO() {
        return analyzerDAO;
    }

    public static DAOFactory getDAOFactory() {
        return new DAOFactory();
    }
}
