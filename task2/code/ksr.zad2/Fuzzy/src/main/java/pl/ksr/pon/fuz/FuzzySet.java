package pl.ksr.pon.fuz;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ksr.pon.dao.Player;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class FuzzySet {
    MembershipFunction membershipFunction;
    String type;

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

    public List<Double> getProductValues(List<Player> datasetElements, List<FuzzySet> otherFuzzySets) {
        if (type.equals("age")) {

        } else if (type.equals("height")) {

        } else if (type.equals("weight")) {

        } else if (type.equals("draftNumber")) {

        } else if (type.equals("gamesPlayed")) {

        } else if (type.equals("averagePoints")) {

        } else if (type.equals("averageRebounds")) {

        } else if (type.equals("averageAssists")) {

        } else if (type.equals("teamImpact")) {

        } else if (type.equals("throwAccuracy")) {

        } else if (type.equals("assistsPercent")) {

        }

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
