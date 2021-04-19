package pl.ksr.pon.ext.fea;

import lombok.Getter;
import pl.ksr.pon.ext.NumericalFeature;

import java.util.regex.Pattern;

@Getter
public class CitesCountFeature extends Feature implements NumericalFeature {

    public CitesCountFeature(boolean isSelected) {
        super(isSelected);
    }

    public void extract(String content) {
        featureValue = extractNumericalFeature(content) / 5.0;
    }

    @Override
    public int extractNumericalFeature(String content) {
        Pattern pattern = Pattern.compile(",\"");
        Pattern pattern1 = Pattern.compile("\\.\"\\s+");
        return (int) (pattern.matcher(content).results().count() + pattern1.matcher(content).results().count());
    }
}
