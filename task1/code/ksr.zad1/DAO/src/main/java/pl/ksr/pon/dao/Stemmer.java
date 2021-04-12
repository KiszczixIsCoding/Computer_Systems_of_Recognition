package pl.ksr.pon.dao;

import org.tartarus.snowball.ext.porterStemmer;

public class Stemmer {
    static porterStemmer stemmer = new porterStemmer();
    public static String stemm (String content) {
        stemmer.setCurrent(content);
        stemmer.stem();
        return stemmer.getCurrent();
    }

}
