package pl.ksr.pon.gen;

import com.google.common.collect.Range;

public class AbsoluteQuantifier extends LinguisticQuantifier {
    private Range<Double> range;

    public AbsoluteQuantifier(String name, Label label, Range<Double> range) {
        super(name, label);
        this.range = range;
    }
}
