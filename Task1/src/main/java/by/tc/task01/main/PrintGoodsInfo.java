package by.tc.task01.main;

import by.tc.task01.entity.Goods;
import by.tc.task01.exception.NoItemFoundException;

import java.util.List;

public class PrintGoodsInfo {
    private static final String NOT_FOUND_MESSAGE = "No items were found";
	
	public static void print(List<Goods> goods) throws NoItemFoundException {
		if (goods != null && !goods.isEmpty()) {
            for (Goods g : goods) {
                System.out.println(g);
            }
        } else {
            throw new NoItemFoundException(NOT_FOUND_MESSAGE);
        }
	}
}
