package pl.ksr.pon.gen;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ksr.pon.fuz.FuzzySet;

@Data
@AllArgsConstructor
public class Label {

    private String name;
    private FuzzySet fuzzySet;
}
