package by.epam.buhai.xml_parser.dao.creator;

import by.epam.buhai.xml_parser.dao.util.NodeRecognizer;
import by.epam.buhai.xml_parser.entity.Node;
import by.epam.buhai.xml_parser.entity.NodeTypes;

import by.epam.buhai.xml_parser.exception.NodeCreationFailedException;

public final class CreatorImpl extends Creator {
    private static final String XML_CHARACTERS = "[/<]";

    public CreatorImpl() {
    }

    @Override
    public Node createNode(String line) throws NodeCreationFailedException {
        NodeRecognizer nodeRecognizer = NodeRecognizer.getNodeRecognizer();

        NodeTypes type = nodeRecognizer.findNodeType(line);
        String content = line.replaceAll(XML_CHARACTERS, "");

        Node node = Node.getNode();
        node.setContent(content);
        node.setType(type);

        return node;
    }
}
