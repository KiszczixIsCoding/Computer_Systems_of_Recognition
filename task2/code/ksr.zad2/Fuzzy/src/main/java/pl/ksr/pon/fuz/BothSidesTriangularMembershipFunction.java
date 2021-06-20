package pl.ksr.pon.fuz;

import io.vavr.Tuple2;

public class BothSidesTriangularMembershipFunction extends TriangularMembershipFunction {

    private double x3;

    public BothSidesTriangularMembershipFunction(double x1, double x2, double x3) {
        super(x1, x2);
        this.x3 = x3;
    }

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

    @Override
    public Double getArea() {
        return ((x3 - x1)) / 2.0;
    }

}
