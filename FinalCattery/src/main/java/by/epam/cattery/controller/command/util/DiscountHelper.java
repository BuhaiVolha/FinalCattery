package by.epam.cattery.controller.command.util;

import by.epam.cattery.entity.Cat;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DiscountHelper {
    private static final Logger logger = LogManager.getLogger(DiscountHelper.class);

    private static final DiscountHelper instance = new DiscountHelper();

    private DiscountHelper() {
    }

    public void setPriceWithDiscount(List<Cat> cats, int discountPercents) {

        for (Cat cat : cats) {

            if (discountPercents == 0) {
                cat.setPriceWithDiscount(cat.getPrice());
            } else {
                cat.setPriceWithDiscount(countPriceWithDiscount(cat.getPrice(), discountPercents));
            }
        }
    }


    public double countPriceWithDiscount(double price, int discountPercents) {
        return price - (price * discountPercents) / 100;
    }


    public static DiscountHelper getInstance() {
        return instance;
    }
}
