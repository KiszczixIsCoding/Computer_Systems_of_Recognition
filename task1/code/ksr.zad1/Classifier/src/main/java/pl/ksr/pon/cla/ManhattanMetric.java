package pl.ksr.pon.cla;

import pl.ksr.pon.ext.FeaturesVector;

public class ManhattanMetric extends Metric {
    @Override
    public double countDistance(FeaturesVector trainingVector, FeaturesVector testingVector) {
        double differenceValue = 0d;
        for (int index = 0; index < 11; index++) {
            if (trainingVector.getFeatures().get(index).isSelected()
                    && testingVector.getFeatures().get(index).isSelected()) {

                differenceValue += Math.abs(trainingVector.getFeatures().get(index).getFeatureValue()
                                             - testingVector.getFeatures().get(index).getFeatureValue());
            }
        }
        return differenceValue;
    }
}
