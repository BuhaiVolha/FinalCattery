package by.epam.buhai.xml_analyzer.service;

import by.epam.buhai.xml_analyzer.entity.Node;
import by.epam.buhai.xml_analyzer.service.service_exception.FindingNodeFailedException;
import by.epam.buhai.xml_analyzer.service.service_exception.InvalidPathException;

public interface AnalyzerService {
    void close();
    void setPath(String path) throws InvalidPathException;
    Node findNode() throws FindingNodeFailedException;
    boolean hasNext();
}
