package by.tc.task01.main;

import by.tc.task01.entity.Goods;
import java.util.List;

public class PrintGoodsInfo {
	
	public static void print(List<Goods> goods) {
		if (goods != null && !goods.isEmpty()) {
            for (Goods g : goods) {
                System.out.println(g);
            }
        } else {
            //throw new NoItemFoundException;
        }
	}
}
