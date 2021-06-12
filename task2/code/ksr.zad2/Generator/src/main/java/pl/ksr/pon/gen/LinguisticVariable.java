package pl.ksr.pon.gen;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class LinguisticVariable {
    private String name;
    Range<Double> range;
    List<LinguisticLabel> labels;
}
