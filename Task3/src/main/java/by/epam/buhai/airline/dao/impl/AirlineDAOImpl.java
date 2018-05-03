package by.epam.buhai.airline.dao.impl;

import by.epam.buhai.airline.dao.AirlineDAO;
import by.epam.buhai.airline.dao.Creator;
import by.epam.buhai.airline.dao.creator.CreatorWithCommand;
import by.epam.buhai.airline.dao.dto.AirplaneCommandParameters;
import by.epam.buhai.airline.dao.dto.DTO;
import by.epam.buhai.airline.dao.dto.FreighterCommandParameters;
import by.epam.buhai.airline.dao.dto.SpaceplaneCommandParameters;
import by.epam.buhai.airline.dao.utils.Parser;
import by.epam.buhai.airline.entity.Plane;
import by.epam.buhai.airline.entity.Specification;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class AirlineDAOImpl implements AirlineDAO {
    private static final Logger LOGGER = LogManager.getLogger(AirlineDAOImpl.class);
    private ClassLoader classLoader = AirlineDAO.class.getClassLoader();
    private static final String DATABASE_FILE_NAME = "planes.txt";

    private Parser parser = Parser.getParser();
    private Creator planeCreator = new CreatorWithCommand();

    public List<Plane> createPlaneList() {
        List<Plane> planes = new ArrayList<>();
        Map<String, String> parametersParsedFromLine;

        try (InputStream is = classLoader.getResourceAsStream(DATABASE_FILE_NAME);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String lineFromText;

            while ((lineFromText = reader.readLine()) != null) {

                if (!lineFromText.isEmpty()) {

                    parametersParsedFromLine = parser.makeKeyValuePairsFrom(lineFromText);
                    String planeType = parser.findTypeIn(lineFromText);
                    DTO parameters = planeCreator.createDTO(planeType, parametersParsedFromLine);
                    Plane createdPlane = planeCreator.createPlaneAndParameterize(planeType, parameters);
                    planes.add(createdPlane);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            LOGGER.log(Level.FATAL, "Reading file has failed");
        }
        return planes;
    }
}
