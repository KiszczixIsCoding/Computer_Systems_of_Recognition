package pl.ksr.pon.ext.fea;

import org.apache.commons.lang3.StringUtils;
import pl.ksr.pon.ext.FeatureUtils;
import pl.ksr.pon.ext.TextFeature;
import pl.ksr.pon.ext.dic.KeyWordsDictionary;

import java.util.*;

public class KeyWordsFeature extends TextFeature {

    public KeyWordsFeature(boolean isSelected) {
        super(isSelected);
    }

    public void extract(String content) {
        textFeatureValue = extractTextFeature(content);
    }

    public String extractTextFeature(String content) {
        if (content == null) {
            return null;
        }
        Map<String, Integer> keyWordsMap = new HashMap<>();
        List<String> keyWordsDictionary = new KeyWordsDictionary().getDictionary();

        for (String keyWord : keyWordsDictionary) {

            char[] charArray = keyWord.toCharArray();
            charArray[0] = Character.toUpperCase(charArray[0]);
            String upperKeyWord = String.valueOf(charArray);

            int count = StringUtils.countMatches(content, keyWord) + StringUtils.countMatches(content, upperKeyWord);
            keyWordsMap.put(keyWord, count);

        }

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        // Sorting HashMap
        sortedMap = FeatureUtils.sortByValue(keyWordsMap);

        if (sortedMap.entrySet().iterator().next().getValue() == 0) {
            return null;
        } else {
            return sortedMap.entrySet().iterator().next().getKey();
        }

//        keyWordsMap = keyWordsMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(
//                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

//        return keyWordsMap.keySet().iterator().next();
    }
}
