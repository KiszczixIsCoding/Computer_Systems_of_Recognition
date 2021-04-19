package pl.ksr.pon.ext.fea;

import pl.ksr.pon.ext.FeatureUtils;
import pl.ksr.pon.ext.TextFeature;
import pl.ksr.pon.ext.TrigramMethod;

import java.util.*;

public class MostOftenStringOfNumbersFeature extends Feature implements TextFeature {

    public MostOftenStringOfNumbersFeature(boolean isSelected) {
        super(isSelected);
    }

    @Override
    public void extract(String content, String comparingContent) {
        String mainContent = extractTextFeature(content);
        String compContent = extractTextFeature(comparingContent);
        featureValue = TrigramMethod.calculateSimilarity(mainContent, compContent);    }

    @Override
    public String extractTextFeature(String content) {
        if (content == null) {
            return null;
        }
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
