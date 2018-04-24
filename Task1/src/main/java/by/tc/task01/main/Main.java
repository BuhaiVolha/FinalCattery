package by.tc.task01.main;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.criteria.Criteria;

import static by.tc.task01.entity.criteria.Parameters.*;

import by.tc.task01.exception.TaskException;

import by.tc.task01.service.GoodsService;
import by.tc.task01.service.ServiceFactory;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        try {
            List<Goods> foundGoods;

            ServiceFactory factory = ServiceFactory.getInstance();
            GoodsService service = factory.getGoodsService();

            //////////////////////////////////////////////////////////////////////////////
            // searching for a particular type with certain criteria

            System.out.println("\nИщем определенный тип с критерием");
            Criteria<GoodsType.Oven> criteriaOven = new Criteria<>(GoodsType.Oven.class);
            criteriaOven.add(GoodsType.Oven.CAPACITY, 33);
            criteriaOven.add(GoodsType.Oven.HEIGHT, 45);

            foundGoods = service.find(criteriaOven);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////
            // searching for any type with certain criteria
            // for example, both Oven and Refrigerator have Width=70

            System.out.println("\nИщем Любой тип с критерием");
            Criteria<GoodsType.Any> criteriaAny = new Criteria<>(GoodsType.Any.class);
            criteriaAny.add(GoodsType.Any.WIDTH, 70);

            foundGoods = service.find(criteriaAny);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////
            // searching for any type without any criteria
            // (it will find all goods from the file)

            System.out.println("\nИщем все");
            Criteria<GoodsType.Any> criteriaNone = new Criteria<>(GoodsType.Any.class);

            foundGoods = service.find(criteriaNone);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////
            // searching for a certain type with string criteria

            System.out.println("\nИщем опред тип со строковым критерием");
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
