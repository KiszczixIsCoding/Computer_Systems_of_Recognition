package pl.ksr.pon.ext;

import lombok.Data;

import java.util.List;

@Data
public class Article {
    private String date;
    private String title;
    private String author;
    private String content;
    private ClassifiedPlaces place;
    private FeaturesVector featuresVector;

    public Article(String date, String title, String author, String content, ClassifiedPlaces place) {
        this.date = date;
        this.title = title;
        this.author = author;
        this.content = content;
        this.place = place;
    }
}

