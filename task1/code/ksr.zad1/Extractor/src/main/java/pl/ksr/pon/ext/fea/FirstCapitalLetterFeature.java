package pl.ksr.pon.ext.fea;

import lombok.Getter;
import pl.ksr.pon.ext.NumericalFeature;

@Getter
public class FirstCapitalLetterFeature extends Feature implements NumericalFeature {

    public FirstCapitalLetterFeature(boolean isSelected) {
        super(isSelected);
    }

    public void extract(String content) {
        featureValue = extractNumericalFeature(content);
    }

    @Override
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
