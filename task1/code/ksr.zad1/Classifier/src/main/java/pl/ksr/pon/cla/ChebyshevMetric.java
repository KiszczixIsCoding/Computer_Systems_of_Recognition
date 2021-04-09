package pl.ksr.pon.cla;

import lombok.ToString;

@ToString
public class ChebyshevMetric extends Metric {
    @Override
    public double countDistance(double x1, double x2) {
        return 0;
    }
}
