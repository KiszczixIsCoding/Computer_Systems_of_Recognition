package pl.ksr.pon.cla;

import pl.ksr.pon.ext.Article;
import pl.ksr.pon.ext.ClassifiedPlaces;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsGenerator {

    public Map<String, Integer> generateRatesMap(ClassifiedPlaces place, List<Article> testingList) {

        Map<String, Integer> ratesMap = new HashMap<>();
        ratesMap.put("truePositive", 0);
        ratesMap.put("falsePositive", 0);
        ratesMap.put("falseNegative", 0);
        ratesMap.put("trueNegative", 0);


        for (Article article : testingList) {
            if (article.getPlace() == place && article.getPredictedPlace() == place) {
                ratesMap.put("truePositive", ratesMap.get("truePositive") + 1);
            }
            if (article.getPlace() == place && article.getPredictedPlace() != place) {
                ratesMap.put("falsePositive", ratesMap.get("falsePositive") + 1);
            }
            if (article.getPlace() != place && article.getPredictedPlace() == place) {
                ratesMap.put("falseNegative", ratesMap.get("falseNegative") + 1);
            }
            if (article.getPlace() != place && article.getPredictedPlace() != place) {
                ratesMap.put("trueNegative", ratesMap.get("trueNegative") + 1);
            }
        }

        return ratesMap;
    }

    public double countAccuracy(ClassifiedPlaces place, List<Article> testingList) {
        Map<String, Integer> ratesMap = generateRatesMap(place, testingList);

        double truePositive = (double)ratesMap.get("truePositive");
        double trueNegative = (double)ratesMap.get("trueNegative");

        return (truePositive + trueNegative) / (double)testingList.size();
    }

    public double countPrecision(ClassifiedPlaces place, List<Article> testingList) {
        Map<String, Integer> ratesMap = generateRatesMap(place, testingList);
        double truePositive = (double)ratesMap.get("truePositive");
        double falsePositive = (double)ratesMap.get("falsePositive");

        return truePositive / (truePositive + falsePositive);
    }

    public double countRecall(ClassifiedPlaces place, List<Article> testingList) {
        Map<String, Integer> ratesMap = generateRatesMap(place, testingList);
        double truePositive = (double)ratesMap.get("truePositive");
        double falseNegative = (double)ratesMap.get("falseNegative");

        return truePositive / (truePositive + falseNegative);
    }

    public double countF1(ClassifiedPlaces place, List<Article> testingList) {
        return 2 * countPrecision(place, testingList) * countRecall(place, testingList)
                / (countPrecision(place, testingList) + countRecall(place, testingList));
    }
}
