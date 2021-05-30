package pl.ksr.pon.fuz;

public abstract class MembershipFunction {
    public abstract double countMembership(double x);

    public double countCoefficientA(double x1, double x2, boolean is_up) {
        double y1, y2;
        if (is_up) {
            y1 = 0;
            y2 = 1;
        } else {
            y1 = 1;
            y2 = 0;
        }

        return ((y2 - y1) / x2) / (1 + x1 / x2);
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

        return (y2 - (y1 * x2 / x1)) / (1 - x2);
    }

    public double countLinearFunction(double x, double x1, double x2, boolean is_up) {
        return countCoefficientA(x1, x2, is_up) * x + countCoefficientB(x1, x2, is_up);
    }
}
