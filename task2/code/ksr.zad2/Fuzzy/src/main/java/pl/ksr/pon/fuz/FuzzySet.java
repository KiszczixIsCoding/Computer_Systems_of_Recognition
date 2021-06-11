package pl.ksr.pon.fuz;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FuzzySet {
    MembershipFunction membershipFunction;
    public Range<Double> getAlphaCut(double x) {
        Range<Double> range = Range.closed(0d, 2d);
        System.out.println(range.contains(0.5));
        return range;
    }
}
