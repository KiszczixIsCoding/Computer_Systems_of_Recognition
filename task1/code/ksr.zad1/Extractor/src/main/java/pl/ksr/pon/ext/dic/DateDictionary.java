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


}
