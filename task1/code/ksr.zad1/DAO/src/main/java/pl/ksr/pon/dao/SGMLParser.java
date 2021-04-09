package pl.ksr.pon.dao;

import pl.ksr.pon.ext.Article;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class SGMLParser {

    public List<Article> parse(String fileName) {
        List<Article> articlesInFile = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        fileName = "/reuters21578/" + fileName;
        URL resource = getClass().getResource(fileName);
        File inputFile = null;
        try {
            inputFile = new File(resource.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            Document document = Jsoup.parse(inputFile, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return articlesInFile;
    }
}
