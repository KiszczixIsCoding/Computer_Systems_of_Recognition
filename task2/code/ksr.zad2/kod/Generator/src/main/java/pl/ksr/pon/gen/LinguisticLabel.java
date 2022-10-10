package pl.ksr.pon.gen;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ksr.pon.fuz.FuzzySet;

@Data
@AllArgsConstructor
public class LinguisticLabel {

    private String name;
    private FuzzySet fuzzySet;
}
