package pl.ksr.pon.ext;

import java.util.*;
import java.util.stream.Collectors;

public class MostOftenWordFeature {

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
