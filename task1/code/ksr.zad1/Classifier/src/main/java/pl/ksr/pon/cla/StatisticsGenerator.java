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

    // bez argumentu place wygeneruje dla calego zbioru (true, false) -
    // czyli ile artykulow zostalo sklasyfikowanych prawidłowo
    public Map<String, Integer> generateRatesMap(List<Article> testingList) {

        Map<String, Integer> ratesMap = new HashMap<>();
        ratesMap.put("true", 0);
        ratesMap.put("false", 0);
        for (Article article : testingList) {
            if (article.getPlace() == article.getPredictedPlace()) {
                ratesMap.put("true", ratesMap.get("true") + 1);
            } else {
                ratesMap.put("false", ratesMap.get("false") + 1);
            }
        }
        return ratesMap;
    }

    public double countAccuracy(List<Article> testingList) {
        Map<String, Integer> ratesMap = generateRatesMap(testingList);

        double trueClassified = (double)ratesMap.get("true");

        return (trueClassified) / (double)testingList.size();
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
