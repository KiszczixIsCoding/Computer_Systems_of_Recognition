package pl.ksr.pon.gen;

import lombok.AllArgsConstructor;
import pl.ksr.pon.dao.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class MultisubjectLinguisticSummariesGenerator {
    List<LinguisticLabel> qualifiers;
    List<LinguisticLabel> summarizers;
    RelativeQuantifier relativeQuantifier;
    List<List<Player>> groupedPlayersList;

    public MultisubjectLinguisticSummary generateSummary(String text, int form) {
        MultisubjectLinguisticSummary summary = new MultisubjectLinguisticSummary();

        summary.setText(text);
        if (form == 0) {
            summary.setDegreeOfTruth(countFirstDegreeOfTruth());
        } else if (form == 1) {
            summary.setDegreeOfTruth(countSecondDegreeOfTruth());
        } else if (form == 2) {
            summary.setDegreeOfTruth(countThirdDegreeOfTruth());
        } else {
            summary.setDegreeOfTruth(countForthDegreeOfTruth());
        }
        return summary;
    }

    public double countFirstDegreeOfTruth() {
        List<Double> sigmaCount1, sigmaCount2;
        double sigmaSum1 = 0;
        double sigmaSum2 = 0;

        sigmaCount1 = summarizers.get(0).getFuzzySet().getProductValues(groupedPlayersList.get(0),
                summarizers.stream().map(LinguisticLabel::getFuzzySet).collect(Collectors.toList()));
        sigmaCount2 = summarizers.get(0).getFuzzySet().getProductValues(groupedPlayersList.get(1),
                summarizers.stream().map(LinguisticLabel::getFuzzySet).collect(Collectors.toList()));



        for (double fuzzyMembership : sigmaCount1) {
            sigmaSum1 += fuzzyMembership;
        }

        for (double fuzzyMembership : sigmaCount2) {
            sigmaSum2 += fuzzyMembership;
        }

        double nfoCount1 = 1d / groupedPlayersList.get(0).size() * sigmaSum1;
        double nfoCount2 = 1d / groupedPlayersList.get(1).size() * sigmaSum2;

        double arg = nfoCount1 / (nfoCount1 + nfoCount2);

        return relativeQuantifier.getLabel().getFuzzySet().getMembershipFunction().countMembership(arg);
    }

    public double countSecondDegreeOfTruth() {
        List<Double> sigmaCount1, sigmaCount2, qualifierCount2;
        double sigmaSum1 = 0;
        double sigmaSum2 = 0;

        sigmaCount1 = summarizers.get(0).getFuzzySet().getProductValues(groupedPlayersList.get(0),
                summarizers.stream().map(LinguisticLabel::getFuzzySet).collect(Collectors.toList()));
        sigmaCount2 = summarizers.get(0).getFuzzySet().getProductValues(groupedPlayersList.get(1),
                summarizers.stream().map(LinguisticLabel::getFuzzySet).collect(Collectors.toList()));

        System.out.println(qualifiers.size());
        qualifierCount2 = qualifiers.get(0).getFuzzySet().getProductValues(groupedPlayersList.get(1),
                qualifiers.stream().map(LinguisticLabel::getFuzzySet).collect(Collectors.toList()));

        for (double fuzzyMembership : sigmaCount1) {
            sigmaSum1 += fuzzyMembership;
        }

        for (int i = 0; i < sigmaCount2.size(); i++) {
            if (sigmaCount2.get(i) > qualifierCount2.get(i)) {
                sigmaSum2 += sigmaCount2.get(i);
            } else {
                sigmaSum2 += qualifierCount2.get(i);
            }
        }

        double nfoCount1 = 1d / groupedPlayersList.get(0).size() * sigmaSum1;
        double nfoCount2 = 1d / groupedPlayersList.get(1).size() * sigmaSum2;

        double arg = nfoCount1 / (nfoCount1 + nfoCount2);

        return relativeQuantifier.getLabel().getFuzzySet().getMembershipFunction().countMembership(arg);
    }

    public double countThirdDegreeOfTruth() {
        List<Double> sigmaCount1, sigmaCount2, qualifierCount1;
        double sigmaSum1 = 0;
        double sigmaSum2 = 0;

        sigmaCount1 = summarizers.get(0).getFuzzySet().getProductValues(groupedPlayersList.get(0),
                summarizers.stream().map(LinguisticLabel::getFuzzySet).collect(Collectors.toList()));
        sigmaCount2 = summarizers.get(1).getFuzzySet().getProductValues(groupedPlayersList.get(1),
                summarizers.stream().map(LinguisticLabel::getFuzzySet).collect(Collectors.toList()));

        qualifierCount1 = qualifiers.get(0).getFuzzySet().getProductValues(groupedPlayersList.get(0),
                qualifiers.stream().map(LinguisticLabel::getFuzzySet).collect(Collectors.toList()));

        for (int i = 0; i < sigmaCount1.size(); i++) {
            if (sigmaCount1.get(i) > qualifierCount1.get(i)) {
                sigmaSum1 += sigmaCount1.get(i);
            } else {
                sigmaSum1 += qualifierCount1.get(i);
            }
        }

        for (double fuzzyMembership : sigmaCount2) {
            sigmaSum2 += fuzzyMembership;
        }

        double nfoCount1 = 1d / groupedPlayersList.get(0).size() * sigmaSum1;
        double nfoCount2 = 1d / groupedPlayersList.get(1).size() * sigmaSum2;

        double arg = nfoCount1 / (nfoCount1 + nfoCount2);

        return relativeQuantifier.getLabel().getFuzzySet().getMembershipFunction().countMembership(arg);
    }

    public double countForthDegreeOfTruth() {
        return 0;
    }
}
