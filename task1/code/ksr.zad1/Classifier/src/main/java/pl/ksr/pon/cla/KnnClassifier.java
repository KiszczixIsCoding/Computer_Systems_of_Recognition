package pl.ksr.pon.cla;

import pl.ksr.pon.ext.Article;
import pl.ksr.pon.ext.ClassifiedPlaces;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KnnClassifier {
    private int kNeighbours;
    private Metric metric;

    public KnnClassifier(int kNeighbours, Metric metric) {
        this.kNeighbours = kNeighbours;
        this.metric = metric;
    }

    public void classify(List<Article> trainingList, Article testingArticle) {
        Map<Article, Double> trainingMapWithDistances = new HashMap<>();

        for (Article trainingArticle : trainingList) {
            double distance = metric.countDistance(trainingArticle.getFeaturesVector(),
                                                    testingArticle.getFeaturesVector());
            trainingMapWithDistances.put(trainingArticle, distance);
        }

        Map<Article, Double> sortedTrainingMap = sortMapWithDoubles(trainingMapWithDistances);

        Map<ClassifiedPlaces, Integer> kNeighboursPlacesMap = initializeClassifiedPlacesMap();

        testingArticle.setPredictedPlace(predictPlace(sortedTrainingMap, kNeighboursPlacesMap));
    }

    public Map<Article, Double> sortMapWithDoubles(Map<Article, Double> mapToSort) {
        return mapToSort.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    public ClassifiedPlaces predictPlace(Map<Article, Double> sortedTrainingMap,
                                         Map<ClassifiedPlaces, Integer> kNeighboursPlacesMap) {
        // Limit map to k-nearest articles
        Map<Article, Double> kArticles = sortedTrainingMap.entrySet().stream().limit(kNeighbours)
                                                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        // Count each place occurrence
        for (Article article : kArticles.keySet()) {
            kNeighboursPlacesMap.put(article.getPlace(), kNeighboursPlacesMap.get(article.getPlace()) + 1);
        }

        // Get map entry (place) with maximum occurrence
        Map.Entry<ClassifiedPlaces, Integer> maxEntry = Collections.max(kNeighboursPlacesMap.entrySet(),
                Map.Entry.comparingByValue());
        
        return maxEntry.getKey();
    }

    public Map<ClassifiedPlaces, Integer> initializeClassifiedPlacesMap() {
        Map<ClassifiedPlaces, Integer> classifiedPlacesMap = new HashMap<>();
        for (ClassifiedPlaces place : ClassifiedPlaces.values()) {
            classifiedPlacesMap.put(place, 0);
        }
        return classifiedPlacesMap;
    }
}
