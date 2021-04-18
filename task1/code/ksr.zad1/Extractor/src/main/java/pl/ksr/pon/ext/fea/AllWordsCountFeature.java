package pl.ksr.pon.ext.fea;

import pl.ksr.pon.ext.NumericalFeature;

public class AllWordsCountFeature extends Feature implements NumericalFeature {

    public AllWordsCountFeature(boolean isSelected) {
        super(isSelected);
    }

    public void extract(String content) {
        featureValue = extractNumericalFeature(content) / 100.0;
    }

    @Override
    public int extractNumericalFeature(String content) {
        String[] wordsList = content.split("\\s+");
//        System.out.println("liczba slow: " + wordsList.length);
        return wordsList.length;
    }
}
