package pl.ksr.pon.ext.dic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DateDictionary implements Dictionary {

    @Override
    public List<String> getDictionary() {
        List<String> allDictionary = new ArrayList<>();
        allDictionary.addAll(getMonthDictionary());
        return allDictionary;
    }

    public static List<String> getMonthDictionary() {
        return monthDictionary;
    }

    public static List<String> getShortMonthDictionary() {
        return shortMonthDictionary;
    }

    public static List<String> getEvenMonthDictionary() {
        List<String> evenList = new ArrayList<>();
        for (String month : monthDictionary) {
            if (monthDictionary.indexOf(month) % 2 == 0) {
                evenList.add(month);
            }
        }
        return evenList;
    }

    public static List<String> getEvenShortMonthDictionary() {
        List<String> evenList = new ArrayList<>();
        for (String month : shortMonthDictionary) {
            if (shortMonthDictionary.indexOf(month) % 2 == 0) {
                evenList.add(month);
            }
        }
        return evenList;
    }

    public static List<String> getOddMonthDictionary() {
        List<String> oddList = new ArrayList<>();
        for (String month : monthDictionary) {
            if (monthDictionary.indexOf(month) % 2 != 0) {
                oddList.add(month);
            }
        }
        return oddList;
    }

    public static List<String> getOddShortMonthDictionary() {
        List<String> oddList = new ArrayList<>();
        for (String month : shortMonthDictionary) {
            if (shortMonthDictionary.indexOf(month) % 2 != 0) {
                oddList.add(month);
            }
        }
        return oddList;
    }

    static List<String> monthDictionary = Arrays.asList(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    );

    static List<String> shortMonthDictionary = Arrays.asList(
            "Jan", "Feb", "Mar", "Apr", "May", "Jun",
            "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");



}
