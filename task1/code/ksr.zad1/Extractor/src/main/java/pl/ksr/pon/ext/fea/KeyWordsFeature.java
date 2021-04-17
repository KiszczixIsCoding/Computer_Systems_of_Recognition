package pl.ksr.pon.ext.fea;

import org.apache.commons.lang3.StringUtils;
import pl.ksr.pon.ext.TextFeature;
import pl.ksr.pon.ext.TrigramMethod;
import pl.ksr.pon.ext.dic.KeyWordsDictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class KeyWordsFeature extends Feature implements TextFeature {

    public KeyWordsFeature(boolean isSelected) {
        super(isSelected);
    }

    public void extract(String content, String comparingContent) {
        List<String> mainContentList = extractTextFeature(content);
        List<String> comparingContentList = extractTextFeature(comparingContent);
        // featureValue = TrigramMethod.calculateSimilarity(mainContentList, comparingContentList);

    }

    @Override
    public List<String> extractTextFeature(String content) {
        Map<String, Integer> keyWordsMap = new HashMap<>();
        List<String> keyWordsDictionary = new KeyWordsDictionary().getDictionary();

        for (String keyWord : keyWordsDictionary) {
            String upperKeyWord = keyWord.replaceFirst(
                    String.valueOf(keyWord.charAt(0)), keyWord.toUpperCase(Locale.ROOT));

            int count = StringUtils.countMatches(content, keyWord) + StringUtils.countMatches(content, upperKeyWord);
            keyWordsMap.put(keyWord, count);

        }

        // Sorting HashMap
//        keyWordsMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
        return null;
    }
}
