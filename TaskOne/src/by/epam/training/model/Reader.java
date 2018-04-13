package by.epam.training.model;

import by.epam.training.exception.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private static Reader instance;
    private List<String> textFromFile;

    private Reader() {}

    public static Reader getInstance() {
        if (instance == null) {
            instance = new Reader();
        }
        return instance;
    }

    public void readFile(String path) throws FileNotFoundException {

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            textFromFile = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                textFromFile.add(line);
            }

        } catch (IOException e) {
            throw new FileNotFoundException("File not found");
        }
    }

    public List<String> getTextFromFile() {
        return textFromFile;
    }
}

