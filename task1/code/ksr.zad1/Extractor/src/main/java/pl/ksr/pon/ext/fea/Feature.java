package pl.ksr.pon.ext.fea;

public abstract class Feature {
    protected double featureValue;
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

    public abstract void extract(String content);
}
