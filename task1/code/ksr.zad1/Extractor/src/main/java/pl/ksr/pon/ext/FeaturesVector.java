package pl.ksr.pon.ext;

import pl.ksr.pon.ext.fea.*;

import java.util.ArrayList;
import java.util.List;

public class FeaturesVector {
    private final List<Feature> features;

    public FeaturesVector(List<Boolean> selectedFeatures) {
        features = new ArrayList<>();
        features.add(new AllCapitalLettersFeature(selectedFeatures.get(0))); //ok
        features.add(new AllWordsCountFeature(selectedFeatures.get(1))); //ok
        features.add(new CitesCountFeature(selectedFeatures.get(2))); //ok
        features.add(new DatesCountFeature(selectedFeatures.get(3)));
        features.add(new DatesFormatFeature(selectedFeatures.get(4)));
        features.add(new FirstCapitalLetterFeature(selectedFeatures.get(5))); //ok
        features.add(new KeyWordsFeature(selectedFeatures.get(6)));
        features.add(new MostFrequentCurrencyFeature(selectedFeatures.get(7)));
        features.add(new MostOftenStringOfNumbersFeature(selectedFeatures.get(8)));
        features.add(new MostOftenWordFeature(selectedFeatures.get(9)));
        features.add(new UnitFeature(selectedFeatures.get(10)));
    }

    public List<Feature> getFeatures() {
        return features;
    }
}
