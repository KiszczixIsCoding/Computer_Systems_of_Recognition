package pl.ksr.pon.fuz;

import io.vavr.Tuple2;

public class LeftTrapezoidalMembershipFunction extends TrapezoidalMembershipFunction {
    public LeftTrapezoidalMembershipFunction(double x1, double x2, double x3) {
        super(x1, x2, x3);
    }

    @Override
    public double countMembership(double x) {
        if ((x1 <= x) && (x <= x2)){
            return 1;
        } else if ((x2 < x) && (x <= x3)){
            return countLinearFunction(x, x2, x3, false);
        } else {
            return 0;
        }
    }

    @Override
    public Tuple2<Double, Double> countConstraints(double y) {
        double t1 = countLinearFunctionArgument(y, x2, x3, false);
        double t2 = 0; // jako ze tu będzie jedna wartosc to do drugiej przypisuje 0
        return new Tuple2<>(t1, t2);
    }
}
