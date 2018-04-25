package by.tc.task01.main;

import by.tc.task01.service.GoodsService;
import by.tc.task01.service.ServiceFactory;
import by.tc.task01.entity.Goods;
import by.tc.task01.entity.criteria.Criteria;

import static by.tc.task01.entity.criteria.Parameters.*;

import by.tc.task01.exception.TaskException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        try {
            List<Goods> foundGoods;

            ServiceFactory factory = ServiceFactory.getInstance();
            GoodsService service = factory.getGoodsService();

            //////////////////////////////////////////////////////////////////////////////

            logger.log(Level.INFO,"Searching for a particular type with certain criteria");

            Criteria<GoodsType.Oven> criteriaOven = new Criteria<>(GoodsType.Oven.class);
            criteriaOven.add(GoodsType.Oven.CAPACITY, 33);
            criteriaOven.add(GoodsType.Oven.HEIGHT, 45);

            foundGoods = service.find(criteriaOven);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////

            logger.log(Level.INFO,"Searching for any type with certain criteria\n" +
                    "for example, both Oven and Refrigerator have Width=70");

            Criteria<GoodsType.Any> criteriaAny = new Criteria<>(GoodsType.Any.class);
            criteriaAny.add(GoodsType.Any.WIDTH, 70);

            foundGoods = service.find(criteriaAny);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////

            logger.log(Level.INFO,"Searching for any type without any criteria\n" +
                    "(it will find all goods from the file)");

            Criteria<GoodsType.Any> criteriaNone = new Criteria<>(GoodsType.Any.class);

            foundGoods = service.find(criteriaNone);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////

            logger.log(Level.INFO,"Searching for a certain type with string criteria");

            Criteria<GoodsType.Laptop> criteriaWithString = new Criteria<>(GoodsType.Laptop.class);
            criteriaWithString.add(GoodsType.Laptop.OS, "Windows");

            foundGoods = service.find(criteriaWithString);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////

        } catch (TaskException e) {
            System.out.println(e.getMessage());
        }
    }
}
