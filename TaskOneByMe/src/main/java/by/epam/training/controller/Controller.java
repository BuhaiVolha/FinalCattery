/**
 * Thanks to Discord's code this code has been updated
 * 
 * Now not all the file is read, but a line
 * Also ClassLoader is used when being read
 * 
 * Search system has been reworked
 * Also you are able to search with multiply criteria from now on
 */

package by.epam.training.controller;

import by.epam.training.exception.TaskException;
import by.epam.training.model.Searcher;
import by.epam.training.view.View;

public class Controller {

    public static void main(String[] args) {

        try {
            Searcher searcher = new Searcher();

            // finding appliances by their type
            // returns a list with all records of this type appliance

            searcher.addGoodsType("tablet PC");
            View.print("Searching for a records of a certain type: "
                    + searcher.getGoodsType().getDescription());

            searcher.findGoods();
            View.printList(searcher.showResults());

            ///////////////////////////////////////////////////////////////////////////

            // finding an appliance based on a particular search criterion
            // returns a list with all records that match the criterion
            // or criteria if many

            View.print("\nSearching using criteria:");
            searcher.addGoodsType("newspaper");
            searcher.addSearchCriterion("title", "Вечерний Минск");

            searcher.findGoods();

            View.printList(searcher.showResults());

            ///////////////////////////////////////////////////////////////////////////

            View.print("\nSearching using criteria again:");

            searcher.addGoodsType("Refrigerator");
            searcher.addSearchCriterion("height", "180");
            searcher.addSearchCriterion("Width", "80");
            searcher.addSearchCriterion("overall capacity", "300");

            searcher.findGoods();

            View.printList(searcher.showResults());

        } catch (TaskException e) {
            View.print(e.getMessage());
            e.printStackTrace();
        }
    }
}
