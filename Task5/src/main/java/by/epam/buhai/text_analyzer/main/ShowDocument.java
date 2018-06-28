package by.epam.buhai.text_analyzer.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ShowDocument {
    public static void main(String[] args) {
        URL bsu = null;
        String URLname = "https://www.bsu.by";

        try {
            bsu = new URL(URLname);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (bsu == null) {
            throw new RuntimeException();
        }
        try (BufferedReader b = new BufferedReader(new InputStreamReader(bsu.openStream()))) {
            String line;
            while ((line = b.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
