package pl.ksr.pon.cla;

import pl.ksr.pon.ext.FeaturesVector;
import pl.ksr.pon.ext.NumericalFeature;
import pl.ksr.pon.ext.TextFeature;
import pl.ksr.pon.ext.TrigramMethod;

public class ManhattanMetric extends Metric {
    @Override
    public double countDistance(FeaturesVector trainingVector, FeaturesVector testingVector) {
        double differenceValue = 0d;
        for (int index = 0; index < 11; index++) {
            if (trainingVector.getFeatures().get(index).isSelected()
                    && testingVector.getFeatures().get(index).isSelected()) {
                if (trainingVector.getFeatures().get(index) instanceof NumericalFeature) {

                    double trainingFeatureValue = ((NumericalFeature) trainingVector.getFeatures()
                            .get(index)).getNumericalFeatureValue();

                    double testingFeatureValue = ((NumericalFeature)testingVector.getFeatures()
                            .get(index)).getNumericalFeatureValue();

                    differenceValue += Math.abs(trainingFeatureValue - testingFeatureValue);

                } else {
                    String trainingFeatureWord = ((TextFeature)trainingVector.getFeatures()
                            .get(index)).getTextFeatureValue();
                    String testingFeatureWord = ((TextFeature)testingVector.getFeatures()
                            .get(index)).getTextFeatureValue();

                    double similarity = TrigramMethod.calculateSimilarity(trainingFeatureWord, testingFeatureWord);
                    differenceValue += Math.abs(1 - similarity);
                }
            }
        }
        return differenceValue;
    }
}
