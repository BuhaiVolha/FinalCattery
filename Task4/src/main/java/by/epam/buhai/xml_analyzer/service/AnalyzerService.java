package by.epam.buhai.xml_analyzer.service;

import by.epam.buhai.xml_analyzer.entity.Node;
import by.epam.buhai.xml_analyzer.exception.LoadingFileFailedException;
import by.epam.buhai.xml_analyzer.exception.NodeCreationFailedException;

public interface AnalyzerService {
    void close();
    void setPath(String path) throws LoadingFileFailedException;
    Node findNode() throws NodeCreationFailedException;
    boolean hasNext();
}
