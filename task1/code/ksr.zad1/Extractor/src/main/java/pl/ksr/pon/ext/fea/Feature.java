package pl.ksr.pon.ext.fea;

public class Feature {
    private double featureValue;
    protected boolean isSelected;

    public Feature(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public double getFeatureValue() {
        return featureValue;
    }

    public boolean isSelected() {
        return isSelected;
    }
}
