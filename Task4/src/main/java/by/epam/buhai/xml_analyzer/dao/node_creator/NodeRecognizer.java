package by.epam.buhai.xml_analyzer.dao.node_creator;

import by.epam.buhai.xml_analyzer.entity.NodeTypes;
import by.epam.buhai.xml_analyzer.dao.dao_exception.NodeCreationFailedException;

public final class NodeRecognizer {
    private String line;
    private static NodeRecognizer nodeRecognizer;

    private static final String EMPTY_TAG_REGEX = "<\\w+/";
    private static final String PROLOG_REGEX = "<?";
    private static final String TEXT_NODE_REGEX = "^[^</].*$";
    private static final String CLOSING_TAG_REGEX = "/.*";
    private static final String OPENING_TAG_REGEX = "^<[^?/].*[^/]";
    private static final String ATTRIBUTE_REGEX = "=\"";
    private static final String XML_OPENER = "<";
    private static final String EQUALS_SIGN = "=";

    private NodeRecognizer() {
    }


    public static NodeRecognizer getNodeRecognizer() {
        if (nodeRecognizer == null) {
            nodeRecognizer = new NodeRecognizer();
        }
        return nodeRecognizer;
    }


    public NodeTypes findNodeType(String line) throws NodeCreationFailedException {
        this.line = line;

        if (openingTag()) {
            return NodeTypes.OPENING_TAG;

        } else if (closingTag()) {
            return NodeTypes.CLOSING_TAG;

        } else if (textNode()) {
            return NodeTypes.TEXT_NODE;

        } else if (emptyTag()) {
            return NodeTypes.EMPTY_TAG;

        } else if (prolog()) {
            return NodeTypes.PROLOG;

        } else if (withAttribute()) {
            return NodeTypes.TAG_WITH_ATTRIBUTE;
        }
        throw new NodeCreationFailedException("Creating node has failed",
                new EnumConstantNotPresentException(NodeTypes.class, line));
    }


    private boolean openingTag() {
        return line.matches(OPENING_TAG_REGEX)
                && !line.contains(ATTRIBUTE_REGEX);
    }


    private boolean closingTag() {
        return line.matches(CLOSING_TAG_REGEX);
    }


    private boolean textNode() {
        return line.matches(TEXT_NODE_REGEX);
    }


    private boolean emptyTag() {
        return line.matches(EMPTY_TAG_REGEX);
    }


    private boolean prolog() {
        return line.startsWith(PROLOG_REGEX);
    }


    private boolean withAttribute() {
        return line.startsWith(XML_OPENER)
                && line.contains(EQUALS_SIGN);
    }
}
