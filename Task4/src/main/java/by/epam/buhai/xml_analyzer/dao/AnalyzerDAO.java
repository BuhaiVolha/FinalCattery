package by.epam.buhai.xml_analyzer.dao;

import by.epam.buhai.xml_analyzer.entity.Node;
import by.epam.buhai.xml_analyzer.exception.LoadingFileFailedException;
import by.epam.buhai.xml_analyzer.exception.NodeCreationFailedException;

public interface AnalyzerDAO {
    void setPath(String path) throws LoadingFileFailedException;
    Node findNode() throws NodeCreationFailedException;
    void close();
    boolean hasNext();
}
