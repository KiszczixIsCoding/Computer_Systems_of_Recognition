package pl.ksr.pon.dao;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import pl.ksr.pon.ext.Article;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pl.ksr.pon.ext.ClassifiedPlaces;

public class SGMLParser {

    public List<Article> parse(File inputFile) {
        List<Article> articlesInFile = new ArrayList<>();

        try {
            Document document = Jsoup.parse(inputFile, "UTF-8");
            Elements elements = document.select("REUTERS");
            for (Element element : elements) {
                String placeTmp = element.select("PLACES").select("D").text();
                ClassifiedPlaces place;
                if (placeTmp.equalsIgnoreCase(ClassifiedPlaces.west_germany.toString())) {
                    place = ClassifiedPlaces.west_germany;
                } else if (placeTmp.equalsIgnoreCase(ClassifiedPlaces.usa.toString())) {
                    place = ClassifiedPlaces.usa;
                } else if (placeTmp.equalsIgnoreCase(ClassifiedPlaces.france.toString())) {
                    place = ClassifiedPlaces.france;
                } else if (placeTmp.equalsIgnoreCase(ClassifiedPlaces.uk.toString())) {
                    place = ClassifiedPlaces.uk;
                } else if (placeTmp.equalsIgnoreCase(ClassifiedPlaces.canada.toString())) {
                    place = ClassifiedPlaces.canada;
                } else if (placeTmp.equalsIgnoreCase(ClassifiedPlaces.japan.toString())) {
                    place = ClassifiedPlaces.japan;
                } else {
                    continue;
                }
                String date = element.select("DATE").text();
                String title = element.select("TITLE").text();
                String author = element.select("AUTHOR").text();
                List<TextNode> textNodes = element.select("TEXT").get(0).textNodes();
                String content = textNodes.get(textNodes.size() - 1).text();

                articlesInFile.add(new Article(date, title, author, content, place));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return articlesInFile;
    }
}
