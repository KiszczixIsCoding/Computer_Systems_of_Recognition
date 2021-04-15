package pl.ksr.pon.cla;

import pl.ksr.pon.ext.FeaturesVector;

public class ManhattanMetric extends Metric {
    @Override
    public double countDistance(FeaturesVector trainingVector, FeaturesVector testingVector) {
        return 0;
    }
}
