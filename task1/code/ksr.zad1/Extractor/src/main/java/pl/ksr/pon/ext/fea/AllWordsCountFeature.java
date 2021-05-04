package pl.ksr.pon.ext.fea;

import pl.ksr.pon.ext.NumericalFeature;

public class AllWordsCountFeature extends NumericalFeature {

    public AllWordsCountFeature(boolean isSelected) {
        super(isSelected);
    }

    public void extract(String content) {
        numericalFeatureValue = extractNumericalFeature(content) / 250.0;
    }

    public int extractNumericalFeature(String content) {
        String[] wordsList = content.split("\\s+");
//        System.out.println("liczba slow: " + wordsList.length);
        return wordsList.length;
    }
}
