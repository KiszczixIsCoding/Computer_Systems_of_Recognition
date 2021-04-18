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

    public static List<String> getImperialUnits() {
        return imperialUnits;
    }

    public static List<String> getSiUnits() {
        return siUnits;
    }
    static List<String> imperialUnits = Arrays.asList(
            " inch ",
            " inches ",
            " foot ",
            " feet ",
            " ft ",
            " yard ",
            " yards ",
            " yd ",
            " mile ",
            " miles ",
            " mi ",
            " ounce ",
            " ounces ",
            " oz ",
            " gallon ",
            " gallons ",
            " gal ",
            " quart ",
            " qt ",
            " stone ",
            " st ",
            " pound ",
            " pounds",
            " lb ",
            " ton ",
            " tons ",
            " tonnes ",
            " Fahrenheit ",
            " °F ",
            " inch,",
            " inches,",
            " \" ",
            " foot, ",
            " feet,",
            " ft,",
            " yard,",
            " yards,",
            " yd,",
            " mile,",
            " miles,",
            " mi,",
            " ounce,",
            " ounces,",
            " oz,",
            " gallon,",
            " gallons,",
            " gal,",
            " quart,",
            " qt,",
            " stone,",
            " st,",
            " pound,",
            " pounds,",
            " lb,",
            " ton,",
            " tons,",
            " tonnes,",
            " Fahrenheit,",
            " °F,",
            " inch.",
            " inches.",
            " \".",
            " foot.",
            " feet.",
            " ft.",
            " yard.",
            " yards.",
            " yd.",
            " mile.",
            " miles.",
            " mi.",
            " ounce.",
            " ounces.",
            " oz.",
            " gallon.",
            " gallons.",
            " gal.",
            " quart.",
            " qt.",
            " stone.",
            " st.",
            " pound.",
            " pounds.",
            " lb.",
            " ton.",
            " tons.",
            " tonnes.",
            " Fahrenheit.",
            " °F.",
            "-inch ",
            "-inches ",
            "-\" ",
            "-foot ",
            "-feet ",
            "-ft ",
            "-yard ",
            "-yards ",
            "-yd ",
            "-mile ",
            "-miles ",
            "-mi ",
            "-ounce ",
            "-ounces ",
            "-oz ",
            "-gallon ",
            "-gallons ",
            "-gal ",
            "-quart ",
            "-qt ",
            "-stone ",
            "-st ",
            "-pound ",
            "-pounds",
            "-lb ",
            "-ton ",
            "-tons ",
            "-tonnes ",
            "-Fahrenheit ",
            "-°F "
    );

    static List<String> siUnits = Arrays.asList(
            " gram ",
            " grams ",
            " g ",
            " kilogram ",
            " kilograms ",
            " kg ",
            " meter ",
            " meters ",
            " m ",
            " centimeter ",
            " centimeters ",
            " cm ",
            " Celsius ",
            " °C ",
            " gram,",
            " grams,",
            " g,",
            " kilogram,",
            " kilograms, ",
            " kg,",
            " meter,",
            " meters, ",
            " m,",
            " centimeter,",
            " centimeters, ",
            " cm,",
            " Celsius,",
            " °C,",
            " gram.",
            " grams.",
            " g.",
            " kilogram.",
            " kilograms,",
            " kg.",
            " meter.",
            " meters.",
            " m.",
            " centimeter.",
            " centimeters.",
            " cm.",
            " Celsius.",
            " °C.",
            "-gram ",
            "-grams ",
            "-g ",
            "-kilogram ",
            "-kilograms ",
            "-kg ",
            "-meter ",
            "-meters ",
            "-m ",
            "-centimeter ",
            "-centimeters ",
            "-cm ",
            "-Celsius ",
            "-°C "
    );
}
