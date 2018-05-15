package by.epam.buhai.xml_analyzer.dao;

import by.epam.buhai.xml_analyzer.dao.dao_exception.NodeCreationFailedException;
import by.epam.buhai.xml_analyzer.dao.dao_exception.ReadingLineFailedException;
import by.epam.buhai.xml_analyzer.entity.Node;
import by.epam.buhai.xml_analyzer.dao.dao_exception.LoadingFileFailedException;

public interface AnalyzerDAO {
    void setPath(String path) throws LoadingFileFailedException;
    Node findNode() throws ReadingLineFailedException, NodeCreationFailedException;
    void close();
    boolean hasNext();
}
