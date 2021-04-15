package pl.ksr.pon.ext;

import pl.ksr.pon.ext.fea.*;

import java.util.ArrayList;
import java.util.List;

public class FeaturesVector {
    private AllCapitalLettersFeature allCapitalLettersFeature;
    private AllWordsCountFeature allWordsCountFeature;
    private CitesCountFeature citesCountFeature;
    private DatesCountFeature datesCountFeature;
    private DatesFormatFeature datesFormatFeature;
    private FirstCapitalLetterFeature firstCapitalLetterFeature;
    private KeyWordsFeature keyWordsFeature;
    private MostFrequentCurrencyFeature mostFrequentCurrencyFeature;
    private MostOftenStringOfNumbersFeature mostOftenStringOfNumbersFeature;
    private MostOftenWordFeature mostOftenWordFeature;
    private UnitFeature unitFeature;


    public FeaturesVector(List<Boolean> selectedFeatures) {
        List<NumericalFeature> numericalFeatures = new ArrayList<>();
        if (selectedFeatures.get(0)) {
            numericalFeatures.add(new AllCapitalLettersFeature());
        }
        if (selectedFeatures.get(1)) {
            numericalFeatures.add(new AllWordsCountFeature());
        }
        if (selectedFeatures.get(2)) {
            numericalFeatures.add(new CitesCountFeature());
        }
        if (selectedFeatures.get(3)) {
            numericalFeatures.add(new DatesCountFeature());
        }
        if (selectedFeatures.get(4)) {
            numericalFeatures.add(new FirstCapitalLetterFeature());
        }
        if (selectedFeatures.get(5)) {
            numericalFeatures.add(new AllCapitalLettersFeature());
        }
        if (selectedFeatures.get(6)) {
            numericalFeatures.add(new AllWordsCountFeature());
        }
        if (selectedFeatures.get(7)) {
            numericalFeatures.add(new CitesCountFeature());
        }
        if (selectedFeatures.get(8)) {
            numericalFeatures.add(new DatesCountFeature());
        }
        if (selectedFeatures.get(9)) {
            numericalFeatures.add(new FirstCapitalLetterFeature());
        }
        if (selectedFeatures.get(9)) {
            numericalFeatures.add(new FirstCapitalLetterFeature());
        }

    }

    public FeaturesVector getNumericalFeatures() {
        return null;
    }

    public FeaturesVector getTextFeatures() {
        return null;
    }
}
