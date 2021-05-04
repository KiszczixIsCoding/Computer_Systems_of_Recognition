package pl.ksr.pon.ext;

import pl.ksr.pon.ext.fea.Feature;

public abstract class NumericalFeature extends Feature {
    protected double numericalFeatureValue;

    public NumericalFeature(boolean isSelected) {
        super(isSelected);
    }

    public double getNumericalFeatureValue() {
        return numericalFeatureValue;
    }
}
