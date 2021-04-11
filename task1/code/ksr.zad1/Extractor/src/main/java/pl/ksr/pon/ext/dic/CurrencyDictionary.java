package pl.ksr.pon.ext.dic;

import java.util.Arrays;
import java.util.List;

public class CurrencyDictionary implements Dictionary{

    @Override
    public List<String> getDictionary() {
        return dictionary;
    }

    List<String> dictionary = Arrays.asList(
            "dollar",
            "dollars",
            "dlr",
            "cent",
            "cents",
            "german mark",
            "german marks",
            "penny",
            "pennies",
            "deutschmark",
            "deutschmarks",
            "pfennig",
            "french franc",
            "franc",
            "francs",
            "canadian dollar",
            "canadian dollars",
            "yen",
            "yens",
            "sen",
            "sens",
            "rin",
            "rins",
            "pound",
            "pounds"
    );

}
