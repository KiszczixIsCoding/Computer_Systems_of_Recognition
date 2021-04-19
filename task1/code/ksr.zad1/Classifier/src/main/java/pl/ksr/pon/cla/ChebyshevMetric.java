package pl.ksr.pon.cla;

import pl.ksr.pon.ext.FeaturesVector;
import pl.ksr.pon.ext.NumericalFeature;
import pl.ksr.pon.ext.TextFeature;
import pl.ksr.pon.ext.TrigramMethod;

public class ChebyshevMetric extends Metric {
    @Override
    public double countDistance(FeaturesVector trainingVector, FeaturesVector testingVector) {
        double distanceValue = 0d;
        for (int index = 0; index < 11; index++) {
            if (trainingVector.getFeatures().get(index).isSelected()
                    && testingVector.getFeatures().get(index).isSelected()) {

                double currentDistance;
                if (trainingVector.getFeatures().get(index) instanceof NumericalFeature) {
                    double trainingFeatureValue = ((NumericalFeature) trainingVector.getFeatures()
                            .get(index)).getNumericalFeatureValue();

                    double testingFeatureValue = ((NumericalFeature)testingVector.getFeatures()
                            .get(index)).getNumericalFeatureValue();

                    currentDistance = Math.abs(trainingFeatureValue - testingFeatureValue);
                } else {
                    String trainingFeatureWord = ((TextFeature)trainingVector.getFeatures()
                            .get(index)).getTextFeatureValue();
                    String testingFeatureWord = ((TextFeature)testingVector.getFeatures()
                            .get(index)).getTextFeatureValue();

                    double similarity = TrigramMethod.calculateSimilarity(trainingFeatureWord, testingFeatureWord);

                    currentDistance = Math.abs(1 - similarity);
                }
                if (currentDistance > distanceValue) {
                    distanceValue = currentDistance;
                }
            }
        }
        return distanceValue;
    }
}
