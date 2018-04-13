package by.epam.training.controller;

import by.epam.training.entity.*;
import by.epam.training.exception.TaskException;
import by.epam.training.model.Reader;
import by.epam.training.model.Searcher;
import by.epam.training.view.View;

public class Controller {

    public static void main(String[] args) {

        try {
            // setting a path to the file
            // and creating a reader
            String path = "appliances_db.txt";
            Reader reader = Reader.getInstance();
            reader.readFile(path);

            Searcher searcher = new Searcher(reader.getTextFromFile());

            // finding appliances by their type
            // returns a list with all records of this type's appliance
            View.print("Searching all records of a certain type:");
            searcher.findAppliance("Oven");
            View.printList(searcher.showResults());

            // finding an appliance based on particular search criterion
            // returns a list with all records that match the criterion
            //searcher.findAppliance("Oven", "weight", "12");
            View.print("\nSearching with a search criterion:");
            searcher.findAppliance("Laptop", "os", "Linux");
            View.printList(searcher.showResults());

        } catch (TaskException e) {
            View.print(e.getMessage());
            e.printStackTrace();
        }
    }
}