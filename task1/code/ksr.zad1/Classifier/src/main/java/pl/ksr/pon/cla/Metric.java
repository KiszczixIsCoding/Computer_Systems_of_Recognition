package pl.ksr.pon.cla;

import lombok.ToString;
import pl.ksr.pon.ext.FeaturesVector;

@ToString
public abstract class Metric {
    public abstract double countDistance(FeaturesVector f1, FeaturesVector f2);
}
