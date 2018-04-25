package by.tc.task01.main;

import by.tc.task01.entity.Goods;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PrintGoodsInfo {
    private static final Logger LOGGER = LogManager.getLogger(PrintGoodsInfo.class);
    private static final String NOT_FOUND_MESSAGE = "No items were found";

    public static void print(List<Goods> goods) {
        if (goods != null && !goods.isEmpty()) {
            for (Goods g : goods) {
                System.out.println(g);
            }
        } else {
            LOGGER.log(Level.INFO, NOT_FOUND_MESSAGE);
        }
    }
}
