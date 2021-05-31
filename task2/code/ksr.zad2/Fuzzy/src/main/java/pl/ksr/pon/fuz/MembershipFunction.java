package pl.ksr.pon.fuz;


import io.vavr.Tuple2;

public abstract class MembershipFunction {
    public abstract double countMembership(double x);
    public abstract Tuple2<Double, Double> countConstraints(double y);

    public double countCoefficientA(double x1, double x2, boolean is_up) {
        double y1, y2;
        if (is_up) {
            y1 = 0;
            y2 = 1;
        } else {
            y1 = 1;
            y2 = 0;
        }

        return (y2 - y1) / (x2 - x1);
    }

    public double countCoefficientB(double x1, double x2, boolean is_up) {
        double y1, y2;
        if (is_up) {
            y1 = 0;
            y2 = 1;
        } else {
            y1 = 1;
            y2 = 0;
        }

        return (x2 * y1 - x1 * y2) / (x2 - x1);
    }

    public double countLinearFunction(double x, double x1, double x2, boolean is_up) {
        return countCoefficientA(x1, x2, is_up) * x + countCoefficientB(x1, x2, is_up);
    }

    public double countLinearFunctionArgument(double y, double x1, double x2, boolean is_up) {
        return (y - countCoefficientB(x1, x2, is_up)) / countCoefficientA(x1, x2, is_up);
    }
}
