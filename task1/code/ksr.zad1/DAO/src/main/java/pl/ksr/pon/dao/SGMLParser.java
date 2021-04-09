package pl.ksr.pon.dao;

import pl.ksr.pon.ext.Article;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SGMLParser {
    public static List<Article> parse(String fileName) {
        List<Article> articlesInFile = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File inputFile = new File(classLoader.
                getResourceAsStream("reuters21578/" + fileName).toString());
        try {
            Document document = Jsoup.parse(inputFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return articlesInFile;
    }
}
