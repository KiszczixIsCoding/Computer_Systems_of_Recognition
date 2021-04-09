package pl.ksr.pon.cla;

import lombok.ToString;

@ToString
public abstract class Metric {
    public abstract double countDistance(double x1, double x2);
}
