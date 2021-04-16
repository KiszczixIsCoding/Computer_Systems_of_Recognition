package pl.ksr.pon.ext.fea;

import lombok.Getter;
import pl.ksr.pon.ext.NumericalFeature;

import java.util.regex.Pattern;

@Getter
public class CitesCountFeature extends Feature implements NumericalFeature {
    int citesCount;

    public CitesCountFeature(boolean isSelected) {
        super(isSelected);
    }

    @Override
    public int extract(String content) {
        Pattern pattern = Pattern.compile(",\"");
        Pattern pattern1 = Pattern.compile(".\"");
        return (int) (pattern.matcher(content).results().count() + pattern1.matcher(content).results().count());
    }
}
