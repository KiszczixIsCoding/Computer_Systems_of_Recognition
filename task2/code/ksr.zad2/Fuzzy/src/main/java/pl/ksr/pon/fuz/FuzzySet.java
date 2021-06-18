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
            elements = new ArrayList<>();
            List<String> elements_str = playersDataset.stream().map(Player::getDraftNumber)
                    .collect(Collectors.toList());
            for (String str : elements_str) {
                elements.add(convertDraftNumberToDouble(str));
            }
        } else if (type.equals("gamesPlayed")) {
            elements = playersDataset.stream().map(Player::getGamesPlayed).collect(Collectors.toList());
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
            elements = new ArrayList<>();
            List<String> elements_str = playersDataset.stream().map(Player::getAssistsPercent).collect(Collectors.toList());
            for (String str : elements_str) {
                elements.add(Double.parseDouble(str));
            }
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

            } else if (type.equals("percentAssists")) {
                product.add(membershipFunction.countMembership(Double.parseDouble(player.getAssistsPercent())));
            }
        }

        if (otherFuzzySets.size() == 1) {
            return product;
        }

        //reject first fuzzySet
        for (int i = 1; i < otherFuzzySets.size(); i++) {
            for (int j = 0; j < product.size(); j++) {
                if (otherFuzzySets.get(i).getType().equals("age")) {
                    product.set(j, Math.min(product.get(j),
                            otherFuzzySets.get(i).membershipFunction.countMembership(datasetElements.get(j).getAge())));
                } else if (otherFuzzySets.get(i).getType().equals("height")) {
                    product.set(j, Math.min(product.get(j),
                            otherFuzzySets.get(i).membershipFunction.countMembership(datasetElements.get(j).getHeight())));
                } else if (otherFuzzySets.get(i).getType().equals("weight")) {
                    product.set(j, Math.min(product.get(j),
                            otherFuzzySets.get(i).membershipFunction.countMembership(datasetElements.get(j).getWeight())));
                } else if (otherFuzzySets.get(i).getType().equals("draftNumber")) {
                    product.set(j, Math.min(product.get(j),
                            otherFuzzySets.get(i).membershipFunction.countMembership(
                                    convertDraftNumberToDouble(datasetElements.get(j).getDraftNumber()))));
                } else if (otherFuzzySets.get(i).getType().equals("gamesPlayed")) {
                    product.set(j, Math.min(product.get(j),
                            otherFuzzySets.get(i).membershipFunction.countMembership(datasetElements.get(j).getGamesPlayed())));
                } else if (otherFuzzySets.get(i).getType().equals("averagePoints")) {
                    product.set(j, Math.min(product.get(j),
                            otherFuzzySets.get(i).membershipFunction.countMembership(datasetElements.get(j).getAveragePoints())));
                } else if (otherFuzzySets.get(i).getType().equals("averageRebounds")) {
                    product.set(j, Math.min(product.get(j),
                            otherFuzzySets.get(i).membershipFunction.countMembership(datasetElements.get(j).getAverageRebounds())));
                } else if (otherFuzzySets.get(i).getType().equals("averageAssists")) {
                    product.set(j, Math.min(product.get(j),
                            otherFuzzySets.get(i).membershipFunction.countMembership(datasetElements.get(j).getAverageAssists())));
                } else if (otherFuzzySets.get(i).getType().equals("teamImpact")) {
                    product.set(j, Math.min(product.get(j),
                            otherFuzzySets.get(i).membershipFunction.countMembership(datasetElements.get(j).getTeamImpact())));
                } else if (otherFuzzySets.get(i).getType().equals("throwAccuracy")) {
                    product.set(j, Math.min(product.get(j),
                            otherFuzzySets.get(i).membershipFunction.countMembership(datasetElements.get(j).getThrowAccuracy())));
                } else if (otherFuzzySets.get(i).getType().equals("percentAssists")) {
                    product.set(j, Math.min(product.get(j),
                            otherFuzzySets.get(i).membershipFunction.countMembership(Double.parseDouble(
                                    datasetElements.get(j).getAssistsPercent()))));
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

    public double getRelativeCardinality() {
        return getCardinality() / elements.size();
    }

}
