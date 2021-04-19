package pl.ksr.pon.ext;

import pl.ksr.pon.ext.fea.Feature;

public abstract class TextFeature extends Feature {
    protected String textFeatureValue;

    public TextFeature(boolean isSelected) {
        super(isSelected);
    }

    public String getTextFeatureValue() {
        return textFeatureValue;
    }
}
