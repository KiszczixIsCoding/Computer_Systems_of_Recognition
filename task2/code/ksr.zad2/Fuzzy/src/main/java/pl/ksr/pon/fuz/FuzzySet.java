package pl.ksr.pon.fuz;

import com.google.common.collect.Range;
import lombok.AllArgsConstructor;
import lombok.Data;
import pl.ksr.pon.dao.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class FuzzySet {
    MembershipFunction membershipFunction;
    String type;
    List<Double> elements;

    public FuzzySet(MembershipFunction membershipFunction, List<Player> playersDataset, String type) {
        this.membershipFunction = membershipFunction;

        if (type.equals("age")) {
            elements = playersDataset.stream().map(Player::getAge).collect(Collectors.toList());
        } else if (type.equals("height")) {
            elements = playersDataset.stream().map(Player::getHeight).collect(Collectors.toList());
        } else if (type.equals("weight")) {
            elements = playersDataset.stream().map(Player::getWeight).collect(Collectors.toList());
        } else if (type.equals("draftNumber")) {
            List<String> elements_str = playersDataset.stream().map(Player::getDraftNumber)
                    .collect(Collectors.toList());

            elements_str = elements_str.stream().map(o -> o.equals("Undrafted") ? "60" : o).collect(Collectors.toList());
//            for (String str : elements_str) {
//                elements.add(Double.parseDouble(str));
//            }

        } else if (type.equals("gamesPlayed")) {
            elements = new ArrayList<>();
        } else if (type.equals("averagePoints")) {
            elements = playersDataset.stream().map(Player::getAveragePoints).collect(Collectors.toList());
        } else if (type.equals("averageRebounds")) {
            elements = playersDataset.stream().map(Player::getAverageRebounds).collect(Collectors.toList());
        } else if (type.equals("averageAssists")) {
            elements = playersDataset.stream().map(Player::getAverageAssists).collect(Collectors.toList());
        } else if (type.equals("teamImpact")) {
            elements = playersDataset.stream().map(Player::getTeamImpact).collect(Collectors.toList());
        } else if (type.equals("throwAccuracy")) {
            elements = playersDataset.stream().map(Player::getThrowAccuracy).collect(Collectors.toList());
        } else if (type.equals("percentAssists")) {
            List<String> elements_str = playersDataset.stream().map(Player::getAssistsPercent).collect(Collectors.toList());
//            for (String str : elements_str) {
//                elements.add(Double.parseDouble(str));
//            }
        } else {
            elements = new ArrayList<>();
        }
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
        return supportList;
    }


    public double getDegreeOfFuzziness(List<Player> datasetElements) {
        return (double)getSupport(datasetElements).size() / datasetElements.size();
    }

    public List<Double> getProductValues(List<Player> datasetElements, List<FuzzySet> otherFuzzySets) {
        return null;
    }

    public double getCardinality() {
        double sum = 0;
        for (Double element : elements) {
            sum += membershipFunction.countMembership(element);
        }
        return sum;
    }

    public double getRelativeCardinality() {
        return getCardinality() / elements.size();
    }

}
