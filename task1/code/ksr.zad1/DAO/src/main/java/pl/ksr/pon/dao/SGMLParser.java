package pl.ksr.pon.dao;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import pl.ksr.pon.ext.Article;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pl.ksr.pon.ext.ClassifiedPlaces;

public class SGMLParser {

    public List<Article> parse(File inputFile) {
        List<Article> articlesInFile = new ArrayList<>();
        Article currentArticle;

        try {
            Document document = Jsoup.parse(inputFile, "UTF-8");
            Elements reutersList = document.select("REUTERS");


            for (Element reutersArticle : reutersList) {
                if (reutersArticle.select("PLACES").select("D").size() == 1
                        && checkPlace(reutersArticle.select("PLACES").select("D").text())) {

                    currentArticle = new Article();
                    currentArticle.setTitle(reutersArticle.select("TITLE").text());
                    currentArticle.setDate(reutersArticle.select("DATE").text());
                    currentArticle.setPlace(ClassifiedPlaces.valueOf(
                            reutersArticle.select("PLACES").select("D").text()));

                    String content = reutersArticle.select("TEXT").get(0).textNodes()
                            .get(reutersArticle.select("TEXT").get(0).textNodes().size()-1).text();

                    String parsedContent = Stoplist.removeMostPopularWordsFromString(content);
                    parsedContent = Stemmer.stemm(parsedContent);

                    currentArticle.setContent(parsedContent);

                    articlesInFile.add(currentArticle);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return articlesInFile;
    }

    public boolean checkPlace(String country) {
        ArrayList<String> countriesNames = new ArrayList<>();
        for (ClassifiedPlaces place : ClassifiedPlaces.values()) {
            countriesNames.add(place.name());
        }
        return countriesNames.contains(country);
    }


}
