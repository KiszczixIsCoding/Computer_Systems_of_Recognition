package pl.ksr.pon.ext.fea;

import pl.ksr.pon.ext.FeatureUtils;
import pl.ksr.pon.ext.TextFeature;
import pl.ksr.pon.ext.TrigramMethod;

import java.util.*;

public class MostOftenWordFeature extends TextFeature {

    public MostOftenWordFeature(boolean isSelected) {
        super(isSelected);
    }


    public void extract(String content) {
        textFeatureValue = extractTextFeature(content);
    }

    public String extractTextFeature(String content) {
        if (content == null) {
            return null;
        }
        String[] wordsArray = content.split("\\s+");
        List<String> wordsList = Arrays.asList(wordsArray);

        String mostCommonWord = FeatureUtils.mostCommon(wordsList);
//        wordsList = FeatureUtils.removeAll(wordsList, mostCommonWord);
//        String secondMostCommonWord = FeatureUtils.mostCommon(wordsList);
//        wordsList = FeatureUtils.removeAll(wordsList, secondMostCommonWord);
//        String thirdMostCommonWord = FeatureUtils.mostCommon(wordsList);

        return mostCommonWord;
    }

}
