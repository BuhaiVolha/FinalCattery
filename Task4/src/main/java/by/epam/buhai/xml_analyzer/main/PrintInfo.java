package by.epam.buhai.xml_analyzer.main;

import by.epam.buhai.xml_analyzer.entity.Node;
import by.epam.buhai.xml_analyzer.entity.NodeTypes;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class PrintInfo {
    private static final Logger LOGGER = LogManager.getLogger(PrintInfo.class);
    private static final String EMPTY_MESSAGE = "Node is null";
    private static final String XML_OPENING_TAG_OPENER = "<";
    private static final String XML_CLOSING_TAG_OPENER = "</";
    private static final String XML_EMPTY_TAG_CLOSER = "/>";
    private static final String XML_CLOSER = ">";


    public static void print(Node node) {
        if (node != null) {
            if (node.getType().equals(NodeTypes.TEXT_NODE)) {
                System.out.println(node.getType().getDescription()
                        + " -> "
                        + node.getContent());

            } else if (node.getType().equals(NodeTypes.CLOSING_TAG)) {
                System.out.println(node.getType().getDescription()
                        + " -> "
                        + XML_CLOSING_TAG_OPENER + node.getContent()+ XML_CLOSER);

            } else if (node.getType().equals(NodeTypes.EMPTY_TAG)) {
                System.out.println(node.getType().getDescription()
                        + " -> "
                        + XML_OPENING_TAG_OPENER
                        + node.getContent()
                        + XML_EMPTY_TAG_CLOSER);

            } else {
                System.out.println(node.getType().getDescription()
                        + " -> "
                        + XML_OPENING_TAG_OPENER
                        + node.getContent()
                        + XML_CLOSER);
            }
        } else {
            LOGGER.log(Level.INFO, EMPTY_MESSAGE);
        }
    }

    public static void printWithColumns(Node node) {
        if (node != null) {
            if (node.getType().equals(NodeTypes.TEXT_NODE)) {
                System.out.printf("%-30s %s%n", node.getType().getDescription(), node.getContent());

            } else if (node.getType().equals(NodeTypes.CLOSING_TAG)) {
                System.out.printf("%-30s </%s>%n", node.getType().getDescription(), node.getContent());

            } else if (node.getType().equals(NodeTypes.EMPTY_TAG)) {
                System.out.printf("%-30s <%s/>%n", node.getType().getDescription(), node.getContent());

            } else {
                System.out.printf("%-30s <%s>%n", node.getType().getDescription(), node.getContent());
            }
        } else {
            LOGGER.log(Level.INFO, EMPTY_MESSAGE);
        }
    }
}
