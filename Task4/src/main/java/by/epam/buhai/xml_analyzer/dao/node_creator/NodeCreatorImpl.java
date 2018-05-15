package by.epam.buhai.xml_analyzer.dao.node_creator;

import by.epam.buhai.xml_analyzer.entity.Node;
import by.epam.buhai.xml_analyzer.entity.NodeTypes;

import by.epam.buhai.xml_analyzer.exception.NodeCreationFailedException;

public final class NodeCreatorImpl extends NodeCreator {
    private static final String XML_CHARACTERS = "[/<]";

    public NodeCreatorImpl() {
    }

    @Override
    public Node createNode(String line) throws NodeCreationFailedException {
        NodeRecognizer nodeRecognizer = NodeRecognizer.getNodeRecognizer();

        NodeTypes type = nodeRecognizer.findNodeType(line);
        String name = line.replaceAll(XML_CHARACTERS, "");

        return new Node(type, name);
    }
}
