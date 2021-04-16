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
    private ClassifiedPlaces predictedPlace = null;
    private FeaturesVector featuresVector;

}

