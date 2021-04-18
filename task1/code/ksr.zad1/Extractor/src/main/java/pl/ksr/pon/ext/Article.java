package pl.ksr.pon.ext;

import lombok.Data;
import pl.ksr.pon.ext.fea.Feature;

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

    public void initFeaturesVector(List<Boolean> selectedFeatures, String comparedArticleContent) {
        this.featuresVector = new FeaturesVector(selectedFeatures);
        for (Feature feature : this.featuresVector.getFeatures()) {
            if (feature instanceof NumericalFeature) {
                ((NumericalFeature) feature).extract(content);
            }
            if (feature instanceof TextFeature) {
                ((TextFeature) feature).extract(content, comparedArticleContent);
            }
        }
    }
}

