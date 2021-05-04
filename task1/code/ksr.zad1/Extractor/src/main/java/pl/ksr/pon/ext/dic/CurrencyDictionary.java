package pl.ksr.pon.ext.dic;

import java.util.Arrays;
import java.util.List;

public class CurrencyDictionary implements Dictionary{

    @Override
    public List<String> getDictionary() {
        return dictionary;
    }

    List<String> dictionary = Arrays.asList(
            "dollar", "dlr", "cent", "german mark", "penny",
            "pennies", "deutschmark", "pfennig", "french franc", "franc",
            "francs", "canadian dollar", "yen", "sen", "sens", "rin",
            "rins", "pound"
    );

}
