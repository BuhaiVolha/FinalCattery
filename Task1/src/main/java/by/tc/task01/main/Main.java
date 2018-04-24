package by.tc.task01.main;

import by.tc.task01.entity.Goods;
import by.tc.task01.entity.criteria.Criteria;
import static by.tc.task01.entity.criteria.Parameters.*;
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

            System.out.println("\nИщем определенный тип с критерием");
            Criteria<GoodsType.Oven> criteriaOven = new Criteria<>(GoodsType.Oven.class);
		    criteriaOven.add(GoodsType.Oven.CAPACITY, 33);
		    criteriaOven.add(GoodsType.Oven.HEIGHT, 45);

            foundGoods = service.find(criteriaOven);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////

            System.out.println("\nИщем Любой тип с критерием");
            Criteria<GoodsType.Any> criteriaAny = new Criteria<>(GoodsType.Any.class);
            criteriaAny.add(GoodsType.Any.WIDTH, 70);

		    foundGoods = service.find(criteriaAny);

		    PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////

            System.out.println("\nИщем все");
            Criteria<GoodsType.Any> criteriaNone = new Criteria<>(GoodsType.Any.class);

            foundGoods = service.find(criteriaNone);

            PrintGoodsInfo.print(foundGoods);

            //////////////////////////////////////////////////////////////////////////////

            System.out.println("\nИщем все");
            Criteria<GoodsType.Any> criteriaElse = new Criteria<>(GoodsType.Any.class);
            criteriaElse.add(GoodsType.Any.POWER_CONSUMPTION, 20000);

            foundGoods = service.find(criteriaElse);

            PrintGoodsInfo.print(foundGoods);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
