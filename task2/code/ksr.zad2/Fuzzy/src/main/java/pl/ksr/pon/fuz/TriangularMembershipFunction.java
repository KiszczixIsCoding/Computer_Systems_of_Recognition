package pl.ksr.pon.fuz;

import io.vavr.Tuple2;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TriangularMembershipFunction extends MembershipFunction {
    private double x1, x2, x3;

    @Override
    public double countMembership(double x) {
        if ((x1 <= x) && (x <= x2)) {
            return countLinearFunction(x, x1, x2, true);
        } else if ((x2 < x) && (x <= x3)){
            return countLinearFunction(x, x2, x3, false);
        } else {
            return 0;
        }
    }

    @Override
    public Tuple2<Double, Double> countConstraints(double y) {
        double t1 = countLinearFunctionArgument(y, x1, x2, true);
        double t2 = countLinearFunctionArgument(y, x2, x3, false);
        return new Tuple2<>(t1, t2);
    }

}
