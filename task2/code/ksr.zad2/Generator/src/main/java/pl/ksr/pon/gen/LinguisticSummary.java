package pl.ksr.pon.gen;

import lombok.Data;

import java.util.List;

@Data
public class LinguisticSummary {
    private String text;
    private Double t1;
    private Double t2;
    private Double t3;
    private Double t4;
    private Double t5;
    private Double t6;
    private Double t7;
    private Double t8;
    private Double t9;
    private Double t10;
    private Double t11;
    private Double average;

    private Double countWeightedAverage(List<Double> weights) {
        double sum = 0;
        sum += weights.get(0) * t1 + weights.get(1) * t2 + weights.get(2) * t3 + weights.get(3) * t4 +
                weights.get(4) * t5 + weights.get(5) * t6 + weights.get(6) * t7 + weights.get(7) * t8 +
                weights.get(8) * t9 + weights.get(9) * t10 + weights.get(10) * t11;

        return sum;
    }
}
