package by.epam.buhai.xml_analyzer.dao.impl;

import by.epam.buhai.xml_analyzer.dao.AnalyzerDAO;
import by.epam.buhai.xml_analyzer.dao.dao_exception.ReadingLineFailedException;
import by.epam.buhai.xml_analyzer.dao.node_creator.NodeCreator;
import by.epam.buhai.xml_analyzer.dao.node_creator.NodeCreatorImpl;
import by.epam.buhai.xml_analyzer.entity.Node;

import by.epam.buhai.xml_analyzer.dao.dao_exception.LoadingFileFailedException;
import by.epam.buhai.xml_analyzer.dao.dao_exception.NodeCreationFailedException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public final class AnalyzerDAOImpl implements AnalyzerDAO {
    private static final Logger LOGGER = LogManager.getLogger(AnalyzerDAOImpl.class);
    private Scanner reader;
    private ClassLoader classLoader;
    private NodeCreator nodeCreator;

    private static final String NODE_FINDING_REGEX = ">|<(?=/)|(?=<)";
    private static final String DELETING_UNWANTED_WHITESPACES_REGEX = "(<?)\\s{2,}";
    private static final String GROUP_TO_REPLACE = "$1";
    private static final String NEW_LINE = "\n";


    public AnalyzerDAOImpl() {
        classLoader = AnalyzerDAO.class.getClassLoader();
        nodeCreator = new NodeCreatorImpl();
    }


    public final void setPath(String path) throws LoadingFileFailedException {
        InputStream in = classLoader.getResourceAsStream(path);

        if (in == null) {
            LOGGER.log(Level.FATAL, "Loading file has failed");
            throw new LoadingFileFailedException();
        }
        reader = new Scanner(new InputStreamReader(in));
    }


    public boolean hasNext() {
        return reader.hasNext();
    }


    public Node findNode() throws ReadingLineFailedException, NodeCreationFailedException {
        String lineFromText = readLine();

        return nodeCreator.createNode(lineFromText);
    }


    private String readLine() throws ReadingLineFailedException {
        String lineFromText;

        try {

            do {
                lineFromText = reader.useDelimiter(NODE_FINDING_REGEX).next();

                if (lineFromText.contains(NEW_LINE)) {

                    lineFromText = lineFromText.replaceAll(DELETING_UNWANTED_WHITESPACES_REGEX, GROUP_TO_REPLACE);
                }
            } while (lineFromText.isEmpty());

        } catch (Exception e) {
            LOGGER.log(Level.FATAL, "Reading a line has failed", e);
            throw new ReadingLineFailedException(e);

        }
        return lineFromText;
    }


    @Override
    public void close() {
        reader.close();
    }
}
