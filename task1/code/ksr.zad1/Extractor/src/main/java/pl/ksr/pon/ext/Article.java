package pl.ksr.pon.ext;

import lombok.Data;

import java.util.List;

@Data
public class Article {
    private String date;
    private String title;
    private List<String> authors;
    private String content;
    private ClassifiedPlaces place;
    private FeaturesVector featuresVector;
}
