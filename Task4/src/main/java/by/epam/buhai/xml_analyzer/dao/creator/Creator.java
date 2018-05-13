package by.epam.buhai.xml_analyzer.dao.creator;

import by.epam.buhai.xml_analyzer.entity.Node;
import by.epam.buhai.xml_analyzer.exception.NodeCreationFailedException;

public abstract class Creator {
    public abstract Node createNode(String line) throws NodeCreationFailedException;
}
