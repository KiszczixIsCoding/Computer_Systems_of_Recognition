package pl.ksr.pon.gen;

import com.google.common.collect.Range;

public class AbsoluteQuantifier extends LinguisticQuantifier {
    private Range<Double> range;

    public AbsoluteQuantifier(Label label, Range<Double> range) {
        super(label);
        this.range = range;
    }
}
