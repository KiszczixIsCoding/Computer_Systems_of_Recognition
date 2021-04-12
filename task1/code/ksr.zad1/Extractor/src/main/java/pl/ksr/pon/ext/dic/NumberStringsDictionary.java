package pl.ksr.pon.ext.dic;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class NumberStringsDictionary implements Dictionary{
    @Override
    public List<String> getDictionary() {
        return dictionary;
    }

    List<String> dictionary = Arrays.asList(
            "+1",
            "+001",
            "+44",
            "+0044",
            "+49",
            "+0049",
            "+33",
            "+0033",
            "+81",
            "+0081"
    );
}
