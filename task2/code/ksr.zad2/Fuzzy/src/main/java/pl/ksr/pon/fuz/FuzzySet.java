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
        this.type = type;
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
        return (double) getSupport(datasetElements).size() / datasetElements.size();
    }

    public List<Double> getProductValues(List<Player> datasetElements, List<FuzzySet> otherFuzzySets) {

        List<Double> product = new ArrayList<>();
        for (Player player : datasetElements) {
            if (type.equals("age")) {
                product.add(membershipFunction.countMembership(player.getAge()));
            } else if (type.equals("height")) {
                product.add(membershipFunction.countMembership(player.getHeight()));

            } else if (type.equals("weight")) {
                product.add(membershipFunction.countMembership(player.getWeight()));

            } else if (type.equals("draftNumber")) {
                product.add(membershipFunction.countMembership(convertDraftNumberToDouble(player.getDraftNumber())));

            } else if (type.equals("gamesPlayed")) {
                product.add(membershipFunction.countMembership(player.getGamesPlayed()));

            } else if (type.equals("averagePoints")) {
                product.add(membershipFunction.countMembership(player.getAveragePoints()));

            } else if (type.equals("averageRebounds")) {
                product.add(membershipFunction.countMembership(player.getAverageRebounds()));

            } else if (type.equals("averageAssists")) {
                product.add(membershipFunction.countMembership(player.getAverageAssists()));

            } else if (type.equals("teamImpact")) {
                product.add(membershipFunction.countMembership(player.getTeamImpact()));

            } else if (type.equals("throwAccuracy")) {
                product.add(membershipFunction.countMembership(player.getThrowAccuracy()));

            } else if (type.equals("assistsPercent")) {
                product.add(membershipFunction.countMembership(Double.parseDouble(player.getAssistsPercent())));
            }
        }

        if (otherFuzzySets.size() == 1) {
            return product;
        }

        for (FuzzySet fuzzySet : otherFuzzySets) {
            //reject first fuzzySet
            for (int i = 1; i < product.size(); i++) {
                if (fuzzySet.getType().equals("age")) {
                    product.set(i, Math.min(product.get(i),
                            fuzzySet.membershipFunction.countMembership(datasetElements.get(i).getAge())));
                } else if (fuzzySet.getType().equals("height")) {
                    product.set(i, Math.min(product.get(i),
                            fuzzySet.membershipFunction.countMembership(datasetElements.get(i).getHeight())));
                } else if (fuzzySet.getType().equals("weight")) {
                    product.set(i, Math.min(product.get(i),
                            fuzzySet.membershipFunction.countMembership(datasetElements.get(i).getWeight())));
                } else if (fuzzySet.getType().equals("draftNumber")) {
                    product.set(i, Math.min(product.get(i),
                            fuzzySet.membershipFunction.countMembership(
                                    convertDraftNumberToDouble(datasetElements.get(i).getDraftNumber()))));
                } else if (fuzzySet.getType().equals("gamesPlayed")) {
                    product.set(i, Math.min(product.get(i),
                            fuzzySet.membershipFunction.countMembership(datasetElements.get(i).getGamesPlayed())));
                } else if (fuzzySet.getType().equals("averagePoints")) {
                    product.set(i, Math.min(product.get(i),
                            fuzzySet.membershipFunction.countMembership(datasetElements.get(i).getAveragePoints())));
                } else if (fuzzySet.getType().equals("averageRebounds")) {
                    product.set(i, Math.min(product.get(i),
                            fuzzySet.membershipFunction.countMembership(datasetElements.get(i).getAverageRebounds())));
                } else if (fuzzySet.getType().equals("averageAssists")) {
                    product.set(i, Math.min(product.get(i),
                            fuzzySet.membershipFunction.countMembership(datasetElements.get(i).getAverageAssists())));
                } else if (fuzzySet.getType().equals("teamImpact")) {
                    product.set(i, Math.min(product.get(i),
                            fuzzySet.membershipFunction.countMembership(datasetElements.get(i).getTeamImpact())));
                } else if (fuzzySet.getType().equals("throwAccuracy")) {
                    product.set(i, Math.min(product.get(i),
                            fuzzySet.membershipFunction.countMembership(datasetElements.get(i).getThrowAccuracy())));
                } else if (fuzzySet.getType().equals("assistsPercent")) {
                    product.set(i, Math.min(product.get(i),
                            fuzzySet.membershipFunction.countMembership(Double.parseDouble(
                                    datasetElements.get(i).getAssistsPercent()))));
                }
            }
        }
        return product;
    }

    private Double convertDraftNumberToDouble(String draftNumber) {
        if (draftNumber.equalsIgnoreCase("undrafted")) {
            return 60d;
        } else return Double.parseDouble(draftNumber);
    }

    public double getCardinality() {
        double sum = 0;
        for (Double element : elements) {
            sum += membershipFunction.countMembership(element);
        }
        return sum;
    }

    public double getInclusion(List<Player> datasetElements, List<FuzzySet> otherFuzzySets) {
        List<Double> inclusions = new ArrayList<>();
//        for (int i = 0; i < )
        return 0;
    }

    public double getRelativeCardinality() {
        return getCardinality() / elements.size();
    }

}
