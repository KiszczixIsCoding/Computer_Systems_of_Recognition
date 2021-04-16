package pl.ksr.pon.ext.fea;

import lombok.Getter;
import pl.ksr.pon.ext.NumericalFeature;


@Getter
public class AllCapitalLettersFeature extends Feature implements NumericalFeature {
    private int allCapitalLettersCount;

    public AllCapitalLettersFeature(int allCapitalLettersCount, boolean isSelected) {
        this.allCapitalLettersCount = allCapitalLettersCount;
        this.isSelected = isSelected;
    }

    @Override
    public int extract(String content) {
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
