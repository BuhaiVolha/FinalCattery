package com.epam.training.oop.view;

import com.epam.training.oop.entity.Goods;

import java.util.List;

public class PrintGoodsInfo {

    public static void print(List<Goods> appliances) {

        if ((appliances != null) && (appliances.size() > 0)) {
            for (Goods goods : appliances) {
                System.out.println(goods);
            }
        } else {
            System.out.println("Not found");
        }
    }
}
