package by.epam.buhai.xml_parser.dao;

import by.epam.buhai.xml_parser.dao.impl.AnalyzerDAOImpl;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final AnalyzerDAO analyzerDAO = new AnalyzerDAOImpl();

    private DAOFactory() {
    }

    public AnalyzerDAO getAnalyzerDAO() {
        return analyzerDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}
