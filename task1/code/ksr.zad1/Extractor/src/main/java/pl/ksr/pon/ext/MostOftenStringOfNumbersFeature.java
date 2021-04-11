package pl.ksr.pon.ext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MostOftenStringOfNumbersFeature {

    public String extract(String content) {
        String[] wordsList = content.split("\\s+");
        List<String> numbersList = new ArrayList<>();
        for (String word : wordsList) {
            // 1. poczatek '+' i minimum 2 cyfry po tym
            if (word.charAt(0) == '+' &&
                    (word.charAt(1) >= '0' && word.charAt(1) <= '9') &&
                    (word.charAt(2) >= '0' && word.charAt(2) <= '9')) {
                numbersList.add(word);
            }
            // 2. ciąg cyfr (plus dozwolona '-') skladajacy sie z min. 5 cyfr
            // (aby odrzucić zapis lat)
            boolean numbersFlag = true;
            if (word.length() >= 5) {
                for (int i = 0; i < word.length(); i++) {
                    if ((word.charAt(i) < '0' || word.charAt(i) > '9') &&
                            word.charAt(i) != '-') {
                        numbersFlag = false;
                        break;
                    }
                }
                if (numbersFlag) {
                    numbersList.add(word);
                }
            }
        }
        if (numbersList.size() > 0) {
            return FeatureUtils.mostCommon(numbersList);
        } else {
            return null;
        }
    }

}
