package by.epam.buhai.xml_parser.service;

import by.epam.buhai.xml_parser.entity.Node;
import by.epam.buhai.xml_parser.exception.LoadingFileFailedException;
import by.epam.buhai.xml_parser.exception.NodeCreationFailedException;

public interface AnalyzerService {
    void close();
    void setPath(String path) throws LoadingFileFailedException;
    Node findNode() throws NodeCreationFailedException;
    boolean hasNext();
}
