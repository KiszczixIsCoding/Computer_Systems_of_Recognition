package pl.ksr.pon.fuz;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExponentialMembershipFunction extends MembershipFunction {
    private double exponent;
    private MembershipFunction membershipFunction;

    @Override
    public double countMembership(double x) {
        return Math.pow(membershipFunction.countMembership(x), exponent);
    }
}
