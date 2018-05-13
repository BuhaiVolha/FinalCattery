package by.epam.buhai.xml_parser.main;

import by.epam.buhai.xml_parser.exception.Task4Exception;
import by.epam.buhai.xml_parser.service.AnalyzerService;
import by.epam.buhai.xml_parser.service.ServiceFactory;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        ServiceFactory factory = ServiceFactory.getInstance();
        AnalyzerService reader = factory.getAnalyzerService();

        try {
            reader.setPath("damagednotes.xml");

            while (reader.hasNext()) {
                PrintInfo.print((reader.findNode()));
            }
            reader.close();

            //////////////////////////////////////////////////////////////

            reader.setPath("breakfastMenu.xml");

            while (reader.hasNext()) {
                PrintInfo.printWithColumns((reader.findNode()));
            }
            reader.close();

        } catch (Task4Exception e) {
            LOGGER.log(Level.INFO, e.getCause());
        }
    }
}
