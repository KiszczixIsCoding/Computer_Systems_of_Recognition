package pl.ksr.pon.ext.fea;

import pl.ksr.pon.ext.FeatureUtils;

import java.util.*;
import java.util.stream.Collectors;

public class MostOftenWordFeature extends Feature {

    public MostOftenWordFeature(boolean isSelected) {
        super(isSelected);
    }

    public List<String> extract(String content) {
        String[] wordsArray = content.split("\\s+");
        List<String> wordsList = Arrays.asList(wordsArray);

        String mostCommonWord = FeatureUtils.mostCommon(wordsList);
        wordsList = FeatureUtils.removeAll(wordsList, mostCommonWord);
        String secondMostCommonWord = FeatureUtils.mostCommon(wordsList);
        wordsList = FeatureUtils.removeAll(wordsList, secondMostCommonWord);
        String thirdMostCommonWord = FeatureUtils.mostCommon(wordsList);

        return Arrays.asList(mostCommonWord, secondMostCommonWord, thirdMostCommonWord);
    }

}
