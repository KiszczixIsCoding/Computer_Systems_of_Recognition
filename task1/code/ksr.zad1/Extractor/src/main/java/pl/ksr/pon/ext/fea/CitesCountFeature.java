package pl.ksr.pon.ext.fea;

import lombok.Getter;
import pl.ksr.pon.ext.NumericalFeature;

import java.util.regex.Pattern;

@Getter
public class CitesCountFeature extends NumericalFeature {

    public CitesCountFeature(boolean isSelected) {
        super(isSelected);
    }

    public void extract(String content) {
        numericalFeatureValue = extractNumericalFeature(content) / 5.0;
    }

    public int extractNumericalFeature(String content) {
        Pattern pattern = Pattern.compile(",\"");
        Pattern pattern1 = Pattern.compile("\\.\"\\s+");
        return (int) (pattern.matcher(content).results().count() + pattern1.matcher(content).results().count());
    }
}
