package pl.ksr.pon.gen;

import pl.ksr.pon.dao.Player;

import java.util.List;

public class MultisubjectLinguisticSummariesGenerator {
    LinguisticLabel qualifier;
    LinguisticLabel summarizer;
    RelativeQuantifier relativeQuantifier; // in multi subject, form 4 quantifier is null
    List<List<Player>> groupedPlayersList;

    public LinguisticSummary generateSummary() {
        LinguisticSummary summary = new LinguisticSummary();
        return summary;
    }

    public double countFirstDegreeOfTruth() {
        double nfoCount1 = 1d / groupedPlayersList.get(0).size() * summarizer.getFuzzySet().getCardinality();
        double nfoCount2 = 1d / groupedPlayersList.get(1).size() * summarizer.getFuzzySet().getCardinality();

        double arg = nfoCount1 / (nfoCount1 + nfoCount2);

        relativeQuantifier.getLabel().getFuzzySet().getMembershipFunction().countMembership(arg);
    }

    public double countSecondDegreeOfTruth() {

    }

    public double countThirdDegreeOfTruth() {

    }

    public double countForthDegreeOfTruth() {
        return 1 -
    }
}
