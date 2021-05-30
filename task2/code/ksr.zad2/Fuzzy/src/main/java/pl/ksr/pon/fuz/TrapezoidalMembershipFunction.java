package pl.ksr.pon.fuz;

public class TrapezoidalMembershipFunction extends MembershipFunction {
    private double x1, x2, x3, x4;

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
}
