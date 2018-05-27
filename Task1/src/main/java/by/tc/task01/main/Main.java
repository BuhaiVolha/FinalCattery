package by.tc.task01.main;

import by.tc.task01.service.GoodsService;
import by.tc.task01.service.ServiceFactory;
import by.tc.task01.entity.Goods;
import by.tc.task01.entity.criteria.Criteria;
import static by.tc.task01.entity.criteria.Parameters.*;

import by.tc.task01.service.service_exception.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        try {
            List<Goods> foundGoods;

            ServiceFactory factory = ServiceFactory.getInstance();
            GoodsService service = factory.getGoodsService();

            //////////////////////////////////////////////////////////////////////////////

            LOGGER.log(Level.INFO,"Searching for a particular type with certain criteria");

            Criteria<Laptop> criteriaLaptop = new Criteria<>(Laptop.class);
            criteriaLaptop.add(Laptop.OS, "Windows");
            criteriaLaptop.add(Laptop.CPU, 1.2);

            foundGoods = service.find(criteriaLaptop);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////

            LOGGER.log(Level.INFO,"Searching for any type with certain criteria, " +
                    "for example, both Oven and Refrigerator have Width=70");

            Criteria<Any> criteriaAny = new Criteria<>(Any.class);
            criteriaAny.add(Any.WIDTH, 70);

            foundGoods = service.find(criteriaAny);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////

            LOGGER.log(Level.INFO,"Searching for any type without any criteria" +
                    "(it will find all goods from the file)");

            Criteria<Any> criteriaNone = new Criteria<>(Any.class);

            foundGoods = service.find(criteriaNone);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////

            LOGGER.log(Level.INFO,"Searching for all items of a certain type");

            Criteria<Laptop> criteriaAllLaptops = new Criteria<>(Laptop.class);

            foundGoods = service.find(criteriaAllLaptops);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////
            LOGGER.log(Level.INFO,"Get parameter and value by index methods");

            Criteria<Laptop> someCriteria = new Criteria<>(Laptop.class);
            someCriteria.add(Laptop.OS, "Windows");
            someCriteria.add(Laptop.CPU, 1.2);
            someCriteria.add(Laptop.BATTERY_CAPACITY, 2000);

            System.out.println(someCriteria.getParameterByIndex(1));
            System.out.println(someCriteria.getValueByIndex(2));

            LOGGER.log(Level.INFO,"Iterate over criteria");
            Iterator criteriaIterator = someCriteria.iterator();
            PrintGoodsInfo.showCriteria(criteriaIterator);

        } catch (ServiceException e) {
            LOGGER.log(Level.WARN, e);
        }
    }
}
