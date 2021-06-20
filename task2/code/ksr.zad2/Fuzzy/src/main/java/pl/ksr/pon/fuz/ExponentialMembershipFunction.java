package pl.ksr.pon.fuz;

import io.vavr.Tuple2;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ExponentialMembershipFunction extends MembershipFunction {
    private double exponent;
    private MembershipFunction membershipFunction;

    @Override
    public double countMembership(double x) {
        return Math.pow(membershipFunction.countMembership(x), exponent);
    }

    @Override
    public Tuple2<Double, Double> countConstraints(double y) {
        return null;
    }

    // na ten moment null jako że nam nie będzie potrzebne
    @Override
    public Double getArea() {
        return null;
    }

}
