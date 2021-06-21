package pl.ksr.pon.fuz;

import io.vavr.Tuple2;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GaussianMembershipFunction extends MembershipFunction {
    double mean, variance ,x1, x2; // x1, x2 - przedziały na ktoryxh jest ta funkcja

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

    public Double getArea() {
        double area = 0.0;
        final int rectangles = 10000; // more rectangles = more precise, less rectangles = quicker execution
        final double width = (x2 - x1) / rectangles;
        for(int i = 0; i < rectangles; i++)
            area += width * countMembership(width * i + x1);
        return area;
    }
}
