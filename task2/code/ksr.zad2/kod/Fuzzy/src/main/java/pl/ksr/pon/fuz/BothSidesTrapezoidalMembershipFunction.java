package pl.ksr.pon.fuz;

import io.vavr.Tuple2;

public class BothSidesTrapezoidalMembershipFunction extends TrapezoidalMembershipFunction {

    private double x4;


    public BothSidesTrapezoidalMembershipFunction(double x1, double x2, double x3, double x4) {
        super(x1, x2, x3);
        this.x4 = x4;
    }

    @Override
    public double countMembership(double x) {
        if ((x1 <= x) && (x <= x2)) {
            return countLinearFunction(x, x1, x2, true);
        } else if ((x2 < x) && (x <= x3)){
            return 1;
        } else if ((x3 < x) && (x <= x4)){
            return countLinearFunction(x, x3, x4, false);
        } else {
            return 0;
        }
    }

    @Override
    public Tuple2<Double, Double> countConstraints(double y) {
        double t1 = countLinearFunctionArgument(y, x1, x2, true);
        double t2 = countLinearFunctionArgument(y, x3, x4, false);
        return new Tuple2<>(t1, t2);
    }

    public Double getArea() {
        return (((x4 - x1) + (x3 - x2))) / 2.0;
    }
}
