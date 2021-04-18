package pl.ksr.pon.ext.fea;

import org.apache.commons.lang3.StringUtils;
import pl.ksr.pon.ext.TextFeature;
import pl.ksr.pon.ext.TrigramMethod;
import pl.ksr.pon.ext.dic.KeyWordsDictionary;

import java.util.*;
import java.util.stream.Collectors;

public class KeyWordsFeature extends Feature implements TextFeature {

    public KeyWordsFeature(boolean isSelected) {
        super(isSelected);
    }

    public void extract(String content, String comparingContent) {
        String mainContent = extractTextFeature(content);
        String compContent = extractTextFeature(comparingContent);
        featureValue = TrigramMethod.calculateSimilarity(mainContent, compContent);

    }

    @Override
    public String extractTextFeature(String content) {
        Map<String, Integer> keyWordsMap = new HashMap<>();
        List<String> keyWordsDictionary = new KeyWordsDictionary().getDictionary();

        for (String keyWord : keyWordsDictionary) {

            char[] charArray = keyWord.toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);
            String upperKeyWord = String.valueOf(charArray);

            int count = StringUtils.countMatches(content, keyWord) + StringUtils.countMatches(content, upperKeyWord);
            keyWordsMap.put(keyWord, count);

        }

        // Sorting HashMap
        keyWordsMap = keyWordsMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return keyWordsMap.keySet().iterator().next();
    }
}
