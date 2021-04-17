package pl.ksr.pon.ext.fea;

import lombok.Getter;
import pl.ksr.pon.ext.NumericalFeature;


@Getter
public class AllCapitalLettersFeature extends Feature implements NumericalFeature {

    public AllCapitalLettersFeature(boolean isSelected) {
        super(isSelected);
    }


    public void extract(String content) {
        featureValue = extractNumericalFeature(content);
    }

    @Override
    public int extractNumericalFeature(String content) {
        int allCapitalLettersCount = 0;
        String[] wordsList = content.split("\\s+");
        for (String word : wordsList) {
            boolean allCapitalsFlag = true;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (c < 'A' || c > 'Z') {
                    allCapitalsFlag = false;
                    break;
                }
            }
            if (allCapitalsFlag) {
                allCapitalLettersCount++;
            }
        }
        return allCapitalLettersCount;
    }
}
