package pl.ksr.pon.ext;

import pl.ksr.pon.ext.dic.DateDictionary;

public class DatesCountFeature implements NumericalFeature {

    int datesCounter = 0;

    @Override
    public int extract(String content) {
        String[] wordsList = content.split("\\s+");
        for (String word : wordsList) {
            // 1. jesli miesiac
            if (DateDictionary.getMonthDictionary().contains(word)) {
                datesCounter++;
            }
            // 2. dlugosc 4, pierwsze 2 znaki to 19 lub 20 - zakladamy, że rok
            if (word.length() == 4 &&
                    ((word.charAt(0) == '1' && word.charAt(1) == '9') ||
                            (word.charAt(0) == '2' && word.charAt(1) == '0'))) {
                datesCounter++;
            }
            // 3. np 1989/1990 lub 1989/90 lub 89/90
            if (word.length() <= 9) {
                if (word.charAt(4) == '/' &&
                        ((word.charAt(0) == '1' && word.charAt(1) == '9') ||
                                (word.charAt(0) == '2' && word.charAt(1) == '0'))) {
                    datesCounter++;
                }
                if (word.charAt(2) == '/' &&
                        ((word.charAt(0) >= '0' && word.charAt(0) <= '9') &&
                                (word.charAt(1) >= '0' && word.charAt(1) <= '9') &&
                                (word.charAt(3) >= '0' && word.charAt(3) <= '9') &&
                                (word.charAt(4) >= '0' && word.charAt(4) <= '9'))) {
                    datesCounter++;
                }
            }
        }
        return datesCounter;
    }
}
