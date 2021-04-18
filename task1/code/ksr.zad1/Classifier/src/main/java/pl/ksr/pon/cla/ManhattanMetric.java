package pl.ksr.pon.cla;

import pl.ksr.pon.ext.FeaturesVector;
import pl.ksr.pon.ext.NumericalFeature;

public class ManhattanMetric extends Metric {
    @Override
    public double countDistance(FeaturesVector trainingVector, FeaturesVector testingVector) {
        double differenceValue = 0d;
        for (int index = 0; index < 11; index++) {
            if (trainingVector.getFeatures().get(index).isSelected()
                    && testingVector.getFeatures().get(index).isSelected()) {
                if (trainingVector.getFeatures().get(index) instanceof NumericalFeature) {

                    differenceValue += Math.abs(trainingVector.getFeatures().get(index).getFeatureValue()
                            - testingVector.getFeatures().get(index).getFeatureValue());
                } else {
                    differenceValue += Math.abs(1 - trainingVector.getFeatures().get(index).getFeatureValue());
                }
            }
        }
        return differenceValue;
    }
}
