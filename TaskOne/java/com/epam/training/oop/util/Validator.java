package com.epam.training.oop.util;

import com.epam.training.oop.entity.criteria.*;
import com.epam.training.oop.entity.criteria.deviceCriteria.*;
import com.epam.training.oop.entity.criteria.paperProductCriteria.NewspaperCriteria;
import com.epam.training.oop.entity.criteria.paperProductCriteria.TextBookCriteria;

import java.util.Map;


public class Validator {

    public static <T> boolean validateCriteria(CriteriaMap criteria) {
        if (criteria == null) {
            return false;
        }

        Map<T, String> criteriaMap = criteria.getCriteria();

        for (Map.Entry<T, String> pair : criteriaMap.entrySet()) {
            if (pair.getKey() == null || pair.getValue() == null) {
                return false;
            }
        }

        String applianceType = criteria.getGoodsType();

        if (applianceType.equals("Oven")) {
            return checkOven(criteriaMap);
        }
        if (applianceType.equals("Laptop")) {
            return checkLaptop(criteriaMap);
        }
        if (applianceType.equals("Refrigerator")) {
            return checkRefrigerator(criteriaMap);
        }
        if (applianceType.equals("VacuumCleaner")) {
            return checkVacuumCleaner(criteriaMap);
        }
        if (applianceType.equals("TabletPC")) {
            return checkTabletPC(criteriaMap);
        }
        if (applianceType.equals("Speakers")) {
            return checkSpeakers(criteriaMap);
        }
        if (applianceType.equals("TextBook")) {
            return checkTextBook(criteriaMap);
        }
        if (applianceType.equals("Newspaper")) {
            return checkNewspaper(criteriaMap);
        }
        return false;
    }

    private static <E> boolean checkNewspaper(Map<E, String> criteriaMap) {
        if (criteriaMap.containsKey(NewspaperCriteria.TITLE)){
            if (!(criteriaMap.get(NewspaperCriteria.TITLE) instanceof String)){
                return false;
            }
        }
        if (criteriaMap.containsKey(NewspaperCriteria.PERIODICITY)){
            if (!(criteriaMap.get(NewspaperCriteria.PERIODICITY) instanceof String)){
                return false;
            }
        }
        if (criteriaMap.containsKey(NewspaperCriteria.PAID_OR_FREE)){
            if (!(criteriaMap.get(NewspaperCriteria.PAID_OR_FREE) instanceof String)){
                return false;
            }
        }
        return true;
    }

    private static <E> boolean checkTextBook(Map<E, String> criteriaMap) {
        if (criteriaMap.containsKey(TextBookCriteria.TITLE)){
            if (!(criteriaMap.get(TextBookCriteria.TITLE) instanceof String)){
                return false;
            }
        }
        if (criteriaMap.containsKey(TextBookCriteria.SUBJECT)){
            if (!(criteriaMap.get(TextBookCriteria.SUBJECT) instanceof String)){
                return false;
            }
        }
        if (criteriaMap.containsKey(TextBookCriteria.AUTHOR)){
            if (!(criteriaMap.get(TextBookCriteria.AUTHOR) instanceof String)){
                return false;
            }
        }
        if (criteriaMap.containsKey(TextBookCriteria.NUMBER_OF_PAGES)) {
            try {
                Double.parseDouble(criteriaMap.get(TextBookCriteria.NUMBER_OF_PAGES));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }


    private static <E> boolean checkSpeakers(Map<E, String> criteriaMap) {
        if (criteriaMap.containsKey(SpeakersCriteria.POWER_CONSUMPTION)) {
            try {
                Double.parseDouble(criteriaMap.get(SpeakersCriteria.POWER_CONSUMPTION));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(SpeakersCriteria.NUMBER_OF_SPEAKERS)) {
            try {
                Double.parseDouble(criteriaMap.get(SpeakersCriteria.NUMBER_OF_SPEAKERS));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(SpeakersCriteria.FREQUENCY_RANGE)){
            if (!(criteriaMap.get(SpeakersCriteria.FREQUENCY_RANGE) instanceof String)){
                return false;
            }
        }
        if (criteriaMap.containsKey(SpeakersCriteria.CORD_LENGTH)) {
            try {
                Double.parseDouble(criteriaMap.get(SpeakersCriteria.CORD_LENGTH));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private static <E> boolean checkTabletPC(Map<E, String> criteriaMap) {
        if (criteriaMap.containsKey(TabletPCCriteria.BATTERY_CAPACITY)) {
            try {
                Double.parseDouble(criteriaMap.get(TabletPCCriteria.BATTERY_CAPACITY));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(TabletPCCriteria.DISPLAY_INCHES)) {
            try {
                Double.parseDouble(criteriaMap.get(TabletPCCriteria.DISPLAY_INCHES));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(TabletPCCriteria.MEMORY_ROM)) {
            try {
                Double.parseDouble(criteriaMap.get(TabletPCCriteria.MEMORY_ROM));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(TabletPCCriteria.FLASH_MEMORY_CAPACITY)) {
            try {
                Double.parseDouble(criteriaMap.get(TabletPCCriteria.FLASH_MEMORY_CAPACITY));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(TabletPCCriteria.COLOR)){
            if (!(criteriaMap.get(TabletPCCriteria.COLOR) instanceof String)){
                return false;
            }
        }
        return true;
    }

    private static <E> boolean checkOven(Map<E, String> criteriaMap) {
        if (criteriaMap.containsKey(OvenCriteria.POWER_CONSUMPTION)) {
            try {
                Double.parseDouble(criteriaMap.get(OvenCriteria.POWER_CONSUMPTION));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(OvenCriteria.WEIGHT)) {
            try {
                Double.parseDouble(criteriaMap.get(OvenCriteria.WEIGHT));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(OvenCriteria.CAPACITY)) {
            try {
                Double.parseDouble(criteriaMap.get(OvenCriteria.CAPACITY));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(OvenCriteria.DEPTH)) {
            try {
                Double.parseDouble(criteriaMap.get(OvenCriteria.DEPTH));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(OvenCriteria.HEIGHT)) {
            try {
                Double.parseDouble(criteriaMap.get(OvenCriteria.HEIGHT));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(OvenCriteria.WIDTH)) {
            try {
                Double.parseDouble(criteriaMap.get(OvenCriteria.WIDTH));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }


    private static <E> boolean checkLaptop(Map<E, String> criteriaMap) {
        if (criteriaMap.containsKey(LaptopCriteria.BATTERY_CAPACITY)) {
            try {
                Double.parseDouble(criteriaMap.get(LaptopCriteria.BATTERY_CAPACITY));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(LaptopCriteria.OS)){
            if (!(criteriaMap.get(LaptopCriteria.OS) instanceof String)){
                return false;
            }
        }
        if (criteriaMap.containsKey(LaptopCriteria.MEMORY_ROM)) {
            try {
                Double.parseDouble(criteriaMap.get(LaptopCriteria.MEMORY_ROM));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(LaptopCriteria.SYSTEM_MEMORY)) {
            try {
                Double.parseDouble(criteriaMap.get(LaptopCriteria.SYSTEM_MEMORY));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(LaptopCriteria.CPU)) {
            try {
                Double.parseDouble(criteriaMap.get(LaptopCriteria.CPU)); //0
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(LaptopCriteria.DISPLAY_INCHES)) {
            try {
                Double.parseDouble(criteriaMap.get(LaptopCriteria.DISPLAY_INCHES));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private static <E> boolean checkRefrigerator(Map<E, String> criteriaMap) {
        if (criteriaMap.containsKey(RefrigeratorCriteria.POWER_CONSUMPTION)) {
            try {
                Double.parseDouble(criteriaMap.get(RefrigeratorCriteria.POWER_CONSUMPTION));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(RefrigeratorCriteria.WEIGHT)) {
            try {
                Double.parseDouble(criteriaMap.get(RefrigeratorCriteria.WEIGHT));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(RefrigeratorCriteria.FREEZER_CAPACITY)) {
            try {
                Double.parseDouble(criteriaMap.get(RefrigeratorCriteria.FREEZER_CAPACITY));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(RefrigeratorCriteria.OVERALL_CAPACITY)) {
            try {
                Double.parseDouble(criteriaMap.get(RefrigeratorCriteria.OVERALL_CAPACITY));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(RefrigeratorCriteria.HEIGHT)) {
            try {
                Double.parseDouble(criteriaMap.get(RefrigeratorCriteria.HEIGHT));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(RefrigeratorCriteria.WIDTH)) {
            try {
                Double.parseDouble(criteriaMap.get(RefrigeratorCriteria.WIDTH));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    private static <E> boolean checkVacuumCleaner(Map<E, String> criteriaMap) {
        if (criteriaMap.containsKey(VacuumCleanerCriteria.POWER_CONSUMPTION)) {
            try {
                Double.parseDouble(criteriaMap.get(VacuumCleanerCriteria.POWER_CONSUMPTION));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(VacuumCleanerCriteria.FILTER_TYPE)) {
            if (!(criteriaMap.get(VacuumCleanerCriteria.FILTER_TYPE) instanceof String)) {
                return false;
            }
        }
        if (criteriaMap.containsKey(VacuumCleanerCriteria.BAG_TYPE)) {
            if (!(criteriaMap.get(VacuumCleanerCriteria.BAG_TYPE) instanceof String)) {
                return false;
            }
        }
        if (criteriaMap.containsKey(VacuumCleanerCriteria.WAND_TYPE)) {
            if (!(criteriaMap.get(VacuumCleanerCriteria.WAND_TYPE) instanceof String)) {
                return false;
            }
        }
        if (criteriaMap.containsKey(VacuumCleanerCriteria.MOTOR_SPEED_REGULATION)) {
            try {
                Double.parseDouble(criteriaMap.get(VacuumCleanerCriteria.MOTOR_SPEED_REGULATION));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        if (criteriaMap.containsKey(VacuumCleanerCriteria.CLEANING_WIDTH)) {
            try {
                Double.parseDouble(criteriaMap.get(VacuumCleanerCriteria.CLEANING_WIDTH));
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }
}