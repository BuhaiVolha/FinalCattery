package by.epam.buhai.xml_analyzer.dao.node_creator;

import by.epam.buhai.xml_analyzer.entity.Node;
import by.epam.buhai.xml_analyzer.exception.NodeCreationFailedException;

public abstract class NodeCreator {
    public abstract Node createNode(String line) throws NodeCreationFailedException;
}
