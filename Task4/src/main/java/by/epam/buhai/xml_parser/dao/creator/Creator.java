package by.epam.buhai.xml_parser.dao.creator;

import by.epam.buhai.xml_parser.entity.Node;
import by.epam.buhai.xml_parser.exception.NodeCreationFailedException;

public abstract class Creator {
    public abstract Node createNode(String line) throws NodeCreationFailedException;
}
