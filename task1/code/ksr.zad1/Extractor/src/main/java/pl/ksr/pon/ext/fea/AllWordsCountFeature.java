package pl.ksr.pon.ext.fea;

import pl.ksr.pon.ext.NumericalFeature;

public class AllWordsCountFeature extends Feature implements NumericalFeature {

    @Override
    public int extract(String content) {

        String[] wordsList = content.split("\\s+");
        return wordsList.length;
    }
}
