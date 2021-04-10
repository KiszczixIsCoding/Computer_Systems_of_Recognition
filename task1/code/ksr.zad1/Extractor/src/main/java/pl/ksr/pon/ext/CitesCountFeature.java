package pl.ksr.pon.ext;

import lombok.Getter;
import java.util.regex.Pattern;

@Getter
public class CitesCountFeature implements NumericalFeature {
    int citesCount;

    @Override
    public int extract(String content) {
        Pattern pattern = Pattern.compile(",\"");
        Pattern pattern1 = Pattern.compile(".\"");
        return (int) (pattern.matcher(content).results().count() + pattern1.matcher(content).results().count());
    }
}
