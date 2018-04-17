package com.epam.training.oop.controller;

import com.epam.training.oop.entity.Goods;
import com.epam.training.oop.entity.criteria.*;
import com.epam.training.oop.entity.criteria.deviceCriteria.*;
import com.epam.training.oop.entity.criteria.paperProductCriteria.NewspaperCriteria;
import com.epam.training.oop.entity.criteria.paperProductCriteria.TextBookCriteria;
import com.epam.training.oop.exception.TaskException;
import com.epam.training.oop.util.GoodsService;
import com.epam.training.oop.view.PrintGoodsInfo;
import com.epam.training.oop.view.View;

import java.util.List;

public class Main {

	public static void main(String[] args) {

		List<Goods> goodsList;
		GoodsService service = new GoodsService();

		try {
            CriteriaMap<OvenCriteria> criteriaOven = new CriteriaMap();
            criteriaOven.setGoodsType("Oven");
            criteriaOven.add(OvenCriteria.CAPACITY, 52);

            goodsList = service.readDevice(criteriaOven);
            PrintGoodsInfo.print(goodsList);

            //////////////////////////////////////////////////////////////////

            criteriaOven = new CriteriaMap();
            criteriaOven.setGoodsType("Oven");
            criteriaOven.add(OvenCriteria.HEIGHT, 45);
            criteriaOven.add(OvenCriteria.DEPTH, 60);

            goodsList = service.readDevice(criteriaOven);
            PrintGoodsInfo.print(goodsList);

            //////////////////////////////////////////////////////////////////

            criteriaOven = new CriteriaMap();
            criteriaOven.setGoodsType("Oven");
            criteriaOven.add(OvenCriteria.HEIGHT, 45.5);
            criteriaOven.add(OvenCriteria.DEPTH, 60);
            criteriaOven.add(OvenCriteria.CAPACITY, 32);

            goodsList = service.readDevice(criteriaOven);
            PrintGoodsInfo.print(goodsList);

            //////////////////////////////////////////////////////////////////

            CriteriaMap criteriaRefrigerator = new CriteriaMap();
            criteriaRefrigerator.setGoodsType("Refrigerator");
            criteriaRefrigerator.add(RefrigeratorCriteria.POWER_CONSUMPTION, "200");
            criteriaRefrigerator.add(RefrigeratorCriteria.WEIGHT, 30);
            criteriaRefrigerator.add(RefrigeratorCriteria.HEIGHT, 180);

            goodsList = service.readDevice(criteriaRefrigerator);
            PrintGoodsInfo.print(goodsList);

            //////////////////////////////////////////////////////////////////

            CriteriaMap criteriaLaptop = new CriteriaMap();
            criteriaLaptop.setGoodsType("Laptop");
            criteriaLaptop.add(LaptopCriteria.OS, "Linux");

            goodsList = service.readDevice(criteriaLaptop);
            PrintGoodsInfo.print(goodsList);

            ////////////////////////////////////////////////////////////////////

            CriteriaMap criteriaVacuumCleaner = new CriteriaMap();
            criteriaVacuumCleaner.setGoodsType("VacuumCleaner");
            criteriaVacuumCleaner.add(VacuumCleanerCriteria.CLEANING_WIDTH, 30);

            goodsList = service.readDevice(criteriaVacuumCleaner);
            PrintGoodsInfo.print(goodsList);

            ////////////////////////////////////////////////////////////////////

            CriteriaMap criteriaTabletPC = new CriteriaMap();
            criteriaTabletPC.setGoodsType("TabletPC");
            criteriaTabletPC.add(TabletPCCriteria.FLASH_MEMORY_CAPACITY, 2);

            goodsList = service.readDevice(criteriaTabletPC);
            PrintGoodsInfo.print(goodsList);

            ////////////////////////////////////////////////////////////////////

            CriteriaMap criteriaSpeakers = new CriteriaMap();
            criteriaSpeakers.setGoodsType("Speakers");
            criteriaSpeakers.add(SpeakersCriteria.CORD_LENGTH, 4);

            goodsList = service.readDevice(criteriaSpeakers);
            PrintGoodsInfo.print(goodsList);

            ////////////////////////////////////////////////////////////////////

            CriteriaMap criteriaTextBook = new CriteriaMap();
            criteriaTextBook.setGoodsType("TextBook");
            criteriaTextBook.add(TextBookCriteria.AUTHOR, "Шарль Перо");

            goodsList = service.readDevice(criteriaTextBook);
            PrintGoodsInfo.print(goodsList);

            ////////////////////////////////////////////////////////////////////

            CriteriaMap criteriaNewspaper = new CriteriaMap();
            criteriaNewspaper.setGoodsType("Newspaper");
            criteriaNewspaper.add(NewspaperCriteria.PAID_OR_FREE, "paid");

            goodsList = service.readDevice(criteriaNewspaper);
            PrintGoodsInfo.print(goodsList);

        } catch (TaskException e) {
            View.print(e.getMessage());
        }
	}
}
