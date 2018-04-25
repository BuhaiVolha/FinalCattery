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
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        try {
            List<Goods> foundGoods;

            ServiceFactory factory = ServiceFactory.getInstance();
            GoodsService service = factory.getGoodsService();

            //////////////////////////////////////////////////////////////////////////////

            LOGGER.log(Level.INFO,"Searching for a particular type with certain criteria");

            Criteria<GoodsType.Laptop> criteriaLaptop = new Criteria<>(GoodsType.Laptop.class);
            criteriaLaptop.add(GoodsType.Laptop.OS, "Windows");
            criteriaLaptop.add(GoodsType.Laptop.CPU, 1.2);

            foundGoods = service.find(criteriaLaptop);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////

            LOGGER.log(Level.INFO,"Searching for any type with certain criteria, " +
                    "for example, both Oven and Refrigerator have Width=70");

            Criteria<GoodsType.Any> criteriaAny = new Criteria<>(GoodsType.Any.class);
            criteriaAny.add(GoodsType.Any.WIDTH, 70);

            foundGoods = service.find(criteriaAny);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////

            LOGGER.log(Level.INFO,"Searching for any type without any criteria" +
                    "(it will find all goods from the file)");

            Criteria<GoodsType.Any> criteriaNone = new Criteria<>(GoodsType.Any.class);

            foundGoods = service.find(criteriaNone);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////
            
            LOGGER.log(Level.INFO,"Searching for all items of a certain type");

            Criteria<GoodsType.Laptop> criteriaAllLaptops = new Criteria<>(GoodsType.Laptop.class);

            foundGoods = service.find(criteriaAllLaptops);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////

        } catch (TaskException e) {
            System.out.println(e.getMessage());
        }
    }
}
