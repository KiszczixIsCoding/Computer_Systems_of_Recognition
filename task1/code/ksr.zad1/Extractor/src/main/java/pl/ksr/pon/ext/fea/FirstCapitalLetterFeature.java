package pl.ksr.pon.ext.fea;

import lombok.Getter;
import pl.ksr.pon.ext.NumericalFeature;

@Getter
public class FirstCapitalLetterFeature extends NumericalFeature {

    public FirstCapitalLetterFeature(boolean isSelected) {
        super(isSelected);
    }

    public void extract(String content) {
        numericalFeatureValue = extractNumericalFeature(content) / 30.0;
    }

    public int extractNumericalFeature(String content) {
        AllCapitalLettersFeature allCapitalLettersFeature = new AllCapitalLettersFeature(true);

        int counter = 0;
        String[] wordsList = content.split("\\s+");
        for (String word : wordsList) {
            char firstLetter = word.charAt(0);
            if (firstLetter >= 'A' && firstLetter <= 'Z') {
                counter++;
            }
        }

        counter = counter - allCapitalLettersFeature.extractNumericalFeature(content);
        return counter;
    }
}
