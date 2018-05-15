package by.epam.buhai.xml_analyzer.service.impl;

import by.epam.buhai.xml_analyzer.dao.DAOFactory;
import by.epam.buhai.xml_analyzer.dao.AnalyzerDAO;
import by.epam.buhai.xml_analyzer.entity.Node;
import by.epam.buhai.xml_analyzer.service.AnalyzerService;

import by.epam.buhai.xml_analyzer.service.service_exception.FindingNodeFailedException;
import by.epam.buhai.xml_analyzer.service.service_exception.InvalidPathException;
import by.epam.buhai.xml_analyzer.dao.dao_exception.LoadingFileFailedException;

public final class AnalyzerServiceImpl implements AnalyzerService {
    private DAOFactory factory = DAOFactory.getDAOFactory();
    private AnalyzerDAO analyzerDAO = factory.getAnalyzerDAO();

    @Override
    public Node findNode() throws FindingNodeFailedException {
        Node node;

        try {
            node = analyzerDAO.findNode();
        } catch (final Throwable e) {
            throw new FindingNodeFailedException(e);
        }
        return node;
    }

    public boolean hasNext() {
        return analyzerDAO.hasNext();
    }

    public void setPath(String path) throws InvalidPathException {
        try {
            analyzerDAO.setPath(path);
        } catch (LoadingFileFailedException e) {
            throw new InvalidPathException(e);
        }
    }

    public void close() {
        analyzerDAO.close();
    }
}
