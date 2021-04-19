package pl.ksr.pon.ext.fea;

import pl.ksr.pon.ext.FeatureUtils;
import pl.ksr.pon.ext.TextFeature;
import pl.ksr.pon.ext.TrigramMethod;

import java.util.*;
import java.util.stream.Collectors;

public class MostOftenWordFeature extends Feature implements TextFeature {

    public MostOftenWordFeature(boolean isSelected) {
        super(isSelected);
    }


    public void extract(String content, String comparingContent) {
        String mainContent = extractTextFeature(content);
        String compContent = extractTextFeature(comparingContent);
        featureValue = TrigramMethod.calculateSimilarity(mainContent, compContent);
    }

    @Override
    public String extractTextFeature(String content) {
        if (content == null) {
            return null;
        }
        String[] wordsArray = content.split("\\s+");
        List<String> wordsList = Arrays.asList(wordsArray);

        String mostCommonWord = FeatureUtils.mostCommon(wordsList);
        wordsList = FeatureUtils.removeAll(wordsList, mostCommonWord);
        String secondMostCommonWord = FeatureUtils.mostCommon(wordsList);
        wordsList = FeatureUtils.removeAll(wordsList, secondMostCommonWord);
        String thirdMostCommonWord = FeatureUtils.mostCommon(wordsList);

        return mostCommonWord;
    }

}
