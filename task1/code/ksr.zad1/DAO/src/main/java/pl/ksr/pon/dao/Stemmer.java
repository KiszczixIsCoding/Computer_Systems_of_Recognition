package pl.ksr.pon.dao;

import org.apache.commons.lang3.StringUtils;
import org.tartarus.snowball.ext.porterStemmer;

import java.util.ArrayList;
import java.util.List;

public class Stemmer {
    static porterStemmer stemmer = new porterStemmer();
    public static String stemm (String content) {
        String[] wordsList = content.split("\\s+");
        List<String> stemmedWords = new ArrayList<>();
        for (String word : wordsList) {
            stemmer.setCurrent(word);
            stemmer.stem();
            stemmedWords.add(stemmer.getCurrent());
        }
        return StringUtils.join(stemmedWords, " ");
    }

}
