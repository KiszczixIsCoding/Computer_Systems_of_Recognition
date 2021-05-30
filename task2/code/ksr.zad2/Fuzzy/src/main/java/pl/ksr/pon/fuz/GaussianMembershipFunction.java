package pl.ksr.pon.fuz;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GaussianMembershipFunction extends MembershipFunction {
    private double mean, variance;

    @Override
    public double countMembership(double x) {
        return Math.pow(Math.E, (-1) / variance * Math.pow(x - mean, 2));
    }
}
