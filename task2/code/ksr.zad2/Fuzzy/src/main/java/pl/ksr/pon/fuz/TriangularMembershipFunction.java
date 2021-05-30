package pl.ksr.pon.fuz;

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

}
