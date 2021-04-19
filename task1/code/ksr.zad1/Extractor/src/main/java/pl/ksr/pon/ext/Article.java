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

    public void initFeaturesVector(List<Boolean> selectedFeatures) {
        this.featuresVector = new FeaturesVector(selectedFeatures);
        for (Feature feature : this.featuresVector.getFeatures()) {
            feature.extract(content);
        }
    }
}

