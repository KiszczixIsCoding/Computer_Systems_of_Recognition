package pl.ksr.pon.ext.dic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class UnitDictionary implements Dictionary{
    @Override
    public List<String> getDictionary() {
        List<String> allDictionary = new ArrayList<>();
        allDictionary.addAll(getImperialUnits());
        allDictionary.addAll(getSiUnits());
        return allDictionary;
    }

    public List<String> getImperialUnits() {
        return imperialUnits;
    }

    public List<String> getSiUnits() {
        return siUnits;
    }
    List<String> imperialUnits = Arrays.asList(
            "inch",
            "inches",
            "in",
            "\"",
            "foot",
            "feet",
            "ft",
            "yard",
            "yards",
            "yd",
            "mile",
            "miles",
            "mi",
            "ounce",
            "ounces",
            "oz",
            "gallon",
            "gallons",
            "gal",
            "quart",
            "qt",
            "stone",
            "st",
            "pound",
            "pounds",
            "lb",
            "ton",
            "Fahrenheit",
            "°F"
    );

    List<String> siUnits = Arrays.asList(
            "gram",
            "g",
            "kilogram",
            "kg",
            "meter",
            "m",
            "centimeter",
            "cm",
            "Celsius",
            "°C"
    );
}
