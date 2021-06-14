package pl.ksr.pon.fuz;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ksr.pon.dao.Player;

import java.util.ArrayList;
import java.util.List;

@Data
public class FuzzySet {
    MembershipFunction membershipFunction;;

    public FuzzySet(MembershipFunction membershipFunction) {
        this.membershipFunction = membershipFunction;
    }

    public Range<Double> getAlphaCut(double x) {
        Range<Double> range = Range.closed(0d, 2d);
        System.out.println(range.contains(0.5));
        return range;
    }

    public List<Player> getSupport(List<Player> datasetElements) {
        List<Player> supportList = new ArrayList<>();
        for (Player player : datasetElements) {
            if (membershipFunction.countMembership(player.getAge()) > 0) {
                supportList.add(player);
            }
        }
    }


    public double getDegreeOfFuzziness(List<Player> datasetElements) {
        return (double)getSupport(datasetElements).size() / datasetElements.size();
    }

    public double getCardinality(List<Player> datasetElements) {
        double sum = 0;
        for (Player player : datasetElements) {
            sum += membershipFunction.countMembership(player.getAge());
        }
        return sum;
    }

    public double getRelativeCardinality(List<Player> datasetElements) {
        return getCardinality(datasetElements) / datasetElements.size();
    }

}
