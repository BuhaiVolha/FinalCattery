package by.epam.buhai.xml_analyzer.dao.creator;

import by.epam.buhai.xml_analyzer.dao.util.NodeRecognizer;
import by.epam.buhai.xml_analyzer.entity.Node;
import by.epam.buhai.xml_analyzer.entity.NodeTypes;

import by.epam.buhai.xml_analyzer.exception.NodeCreationFailedException;

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
        node.setName(content);
        node.setType(type);

        return node;
    }
}
