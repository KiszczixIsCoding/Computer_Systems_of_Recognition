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

    public List<Article> parse(File inputFile) {
        List<Article> articlesInFile = new ArrayList<>();

        try {
            Document document = Jsoup.parse(inputFile, "UTF-8");
            document.select("REUTERS");
            //dostanie się do body: (na razie dla jednego elementu w brzydki sposob
            document.select("TEXT").get(0).textNodes().
                    get(document.select("TEXT").get(0).textNodes().size()-1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return articlesInFile;
    }
}
