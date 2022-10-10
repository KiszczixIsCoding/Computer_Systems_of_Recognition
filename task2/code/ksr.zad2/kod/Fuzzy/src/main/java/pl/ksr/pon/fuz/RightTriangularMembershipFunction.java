package pl.ksr.pon.fuz;

import io.vavr.Tuple2;

public class RightTriangularMembershipFunction extends TriangularMembershipFunction {
    public RightTriangularMembershipFunction(double x1, double x2) {
        super(x1, x2);
    }

    @Override
    public double countMembership(double x) {
        if ((x1 <= x) && (x <= x2)) {
            return countLinearFunction(x, x1, x2, true);
        } else {
            return 0;
        }
    }

    @Override
    public Tuple2<Double, Double> countConstraints(double y) {
        double t1 = countLinearFunctionArgument(y, x1, x2, true);
        double t2 = 0;
        return new Tuple2<>(t1, t2);
    }

    @Override
    public Double getArea() {
        return (x2 - x1) / 2.0;
    }
}
