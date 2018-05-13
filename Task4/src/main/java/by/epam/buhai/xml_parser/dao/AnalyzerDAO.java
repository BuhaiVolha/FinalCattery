package by.epam.buhai.xml_parser.dao;

import by.epam.buhai.xml_parser.entity.Node;
import by.epam.buhai.xml_parser.exception.LoadingFileFailedException;
import by.epam.buhai.xml_parser.exception.NodeCreationFailedException;

public interface AnalyzerDAO {
    void setPath(String path) throws LoadingFileFailedException;
    Node findNode() throws NodeCreationFailedException;
    void close();
    boolean hasNext();
}
