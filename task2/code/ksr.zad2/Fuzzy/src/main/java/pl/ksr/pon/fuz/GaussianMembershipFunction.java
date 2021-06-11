package pl.ksr.pon.fuz;

import io.vavr.Tuple2;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GaussianMembershipFunction extends MembershipFunction {
    double mean, variance;

    @Override
    public double countMembership(double x) {
        return Math.pow(Math.E, (-1) * Math.pow(x - mean, 2) / variance);
    }

    @Override
    public Tuple2<Double, Double> countConstraints(double y) {
        return null;
    }

    public double countX(double y) {
        return Math.sqrt(-Math.log(y) * variance) + mean;
    }
}
