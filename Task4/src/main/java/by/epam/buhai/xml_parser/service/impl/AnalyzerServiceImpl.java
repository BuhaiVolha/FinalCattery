package by.epam.buhai.xml_parser.service.impl;

import by.epam.buhai.xml_parser.dao.DAOFactory;
import by.epam.buhai.xml_parser.dao.AnalyzerDAO;
import by.epam.buhai.xml_parser.entity.Node;

import by.epam.buhai.xml_parser.exception.LoadingFileFailedException;
import by.epam.buhai.xml_parser.exception.NodeCreationFailedException;
import by.epam.buhai.xml_parser.service.AnalyzerService;

public final class AnalyzerServiceImpl implements AnalyzerService {
    private DAOFactory factory = DAOFactory.getInstance();
    private AnalyzerDAO analyzerDAO = factory.getAnalyzerDAO();

    @Override
    public Node findNode() throws NodeCreationFailedException {
        return analyzerDAO.findNode();
    }

    public boolean hasNext() {
        return analyzerDAO.hasNext();
    }

    public void setPath(String path) throws LoadingFileFailedException {
        analyzerDAO.setPath(path);
    }

    public void close() {
        analyzerDAO.close();
    }
}
