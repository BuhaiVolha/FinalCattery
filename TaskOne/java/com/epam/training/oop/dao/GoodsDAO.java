package com.epam.training.oop.dao;

import com.epam.training.oop.entity.*;
import com.epam.training.oop.entity.device.*;
import com.epam.training.oop.entity.paperProduct.Newspaper;
import com.epam.training.oop.entity.paperProduct.TextBook;
import com.epam.training.oop.exception.WrongGoodsTypeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class GoodsDAO {
    private static final String DATABASE_FILE_NAME = "device_db.txt";

    public List<Goods> readGoods(List<String> criteria) throws WrongGoodsTypeException {
        String applianceType = criteria.get(0);

        return searchInDatabase(applianceType, criteria);
    }

    private List<Goods> searchInDatabase(String goodsType, List<String> criteriaList) throws WrongGoodsTypeException {
        List<Goods> relevantGoods = new ArrayList<>();
        ClassLoader classLoader = GoodsDAO.class.getClassLoader();

        try (InputStream is = classLoader.getResourceAsStream(DATABASE_FILE_NAME);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String readLine;

            while ((readLine = br.readLine()) != null) {
                if (getMatch(readLine, criteriaList)) {
                    Goods goods = createGoods(goodsType, readLine);
                    relevantGoods.add(goods);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return relevantGoods;
    }


    private static boolean getMatch(String readData, List<String> criteriaList) {
        boolean isMatch = true;
        int size = criteriaList.size();

        for (int i = 1; i < size; i++) {
            if (!Pattern.matches(criteriaList.get(i), readData.toUpperCase())) {
                isMatch = false;
                break;
            }
        }
        return isMatch;
    }


    private static Goods createGoods(String goodsType, String readLine) throws WrongGoodsTypeException {
        Goods goods = null;
        List<String> parametersList = getParametersArrayForFoundedGoods(readLine);

        switch (goodsType) {
            case "Oven":
                goods = buildOven(parametersList);
                break;
            case "Laptop":
                goods = buildLaptop(parametersList);
                break;
            case "Refrigerator":
                goods = buildRefrigerator(parametersList);
                break;
            case "VacuumCleaner":
                goods = buildVacuumCleaner(parametersList);
                break;
            case "TabletPC":
                goods = buildTabletPC(parametersList);
                break;
            case "Speakers":
                goods = buildSpeakers(parametersList);
                break;
            case "TextBook":
                goods = buildTextBook(parametersList);
                break;
            case "Newspaper":
                goods = buildNewspaper(parametersList);
                break;
            default:
                throw new WrongGoodsTypeException("No such type");
        }

        return goods;
    }

    private static Goods buildNewspaper(List<String> parametersList) {
        Newspaper newspaper = new Newspaper();

        newspaper.setTitle(parametersList.get(0));
        newspaper.setPeriodicity(parametersList.get(1));
        newspaper.setPaidOrFree(parametersList.get(2));

        return newspaper;
    }

    private static Goods buildTextBook(List<String> parametersList) {
        TextBook textBook = new TextBook();

        textBook.setTitle(parametersList.get(0));
        textBook.setSubject(parametersList.get(1));
        textBook.setAuthor(parametersList.get(2));
        textBook.setNumberOfPages(Double.parseDouble(parametersList.get(3)));

        return textBook;
    }


    private static Goods buildSpeakers(List<String> parametersList) {
        Speakers speakers = new Speakers();

        speakers.setPowerConsumption(Double.parseDouble(parametersList.get(0)));
        speakers.setNumberOfSpeakers(Double.parseDouble(parametersList.get(1)));
        speakers.setFreqencyRange(parametersList.get(2));
        speakers.setCordLength(Double.parseDouble(parametersList.get(3)));

        return speakers;
    }

    private static Goods buildTabletPC(List<String> parametersList) {
        TabletPC tabletPC = new TabletPC();

        tabletPC.setBatteryCapacity(Double.parseDouble(parametersList.get(0)));
        tabletPC.setDisplayInches(Double.parseDouble(parametersList.get(1)));
        tabletPC.setMemoryROM(Double.parseDouble(parametersList.get(2)));
        tabletPC.setFlashMemoryCapacity(Double.parseDouble(parametersList.get(3)));
        tabletPC.setColor(parametersList.get(4));

        return tabletPC;
    }


    private static Goods buildRefrigerator(List<String> parametersList) {

        Refrigerator refrigerator = new Refrigerator();
        refrigerator.setPowerConsumption(Double.parseDouble(parametersList.get(0)));
        refrigerator.setWeight(Double.parseDouble(parametersList.get(1)));
        refrigerator.setFreezerCapacity(Double.parseDouble(parametersList.get(2)));
        refrigerator.setOverallCapacity(Double.parseDouble(parametersList.get(3)));
        refrigerator.setHeight(Double.parseDouble(parametersList.get(4)));
        refrigerator.setWidth(Double.parseDouble(parametersList.get(5)));

        return refrigerator;
    }


    private static Goods buildLaptop(List<String> parametersList) {
        Laptop laptop = new Laptop();

        laptop.setBatteryCapacity(Double.parseDouble(parametersList.get(0)));
        laptop.setOs(parametersList.get(1));
        laptop.setMemoryROM(Double.parseDouble(parametersList.get(2)));
        laptop.setSystemMemory(Double.parseDouble(parametersList.get(3)));
        laptop.setCpu(Double.parseDouble(parametersList.get(4)));
        laptop.setDisplayInches(Double.parseDouble(parametersList.get(5)));

        return laptop;
    }

    private static Goods buildOven(List<String> parametersList) {
        Oven oven = new Oven();

        oven.setPowerConsumption(Double.parseDouble(parametersList.get(0)));
        oven.setWeight(Double.parseDouble(parametersList.get(1)));
        oven.setCapacity(Double.parseDouble(parametersList.get(2)));
        oven.setDepth(Double.parseDouble(parametersList.get(3)));
        oven.setHeight(Double.parseDouble(parametersList.get(4)));
        oven.setWidth(Double.parseDouble(parametersList.get(5)));

        return oven;
    }

    private static Goods buildVacuumCleaner(List<String> parametersList) {
        VacuumCleaner vacuumCleaner = new VacuumCleaner();

        vacuumCleaner.setPowerConsumption(Double.parseDouble(parametersList.get(0)));
        vacuumCleaner.setFilterType(parametersList.get(1));
        vacuumCleaner.setBagType(parametersList.get(2));
        vacuumCleaner.setWandType(parametersList.get(3));
        vacuumCleaner.setMotorSpeedRegulation(Double.parseDouble(parametersList.get(4)));
        vacuumCleaner.setCleaningWidth(Double.parseDouble(parametersList.get(5)));

        return vacuumCleaner;
    }

    private static List<String> getParametersArrayForFoundedGoods(String parameters) {
        List<String> paramsList = new ArrayList<>();
        int currentPosition = 0;
        int currentStartIndex;

        while ((currentStartIndex = parameters.indexOf("=", currentPosition)) != -1) {
            int indexOfEndMark = parameters.indexOf(" ,", currentStartIndex);

            if (indexOfEndMark == -1) {
                indexOfEndMark = parameters.indexOf(" ;");
            }
            paramsList.add(parameters.substring(currentStartIndex + 1, indexOfEndMark));
            currentPosition = currentStartIndex + 1;
        }
        return paramsList;
    }
}