package pl.ksr.pon.gen;

import lombok.AllArgsConstructor;
import pl.ksr.pon.dao.Player;
import pl.ksr.pon.fuz.FuzzySet;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class LinguisticSummariesGenerator {
    List<LinguisticLabel> qualifiers;
    List<LinguisticLabel> summarizers;
    LinguisticQuantifier linguisticQuantifier; // in multi subject, form 4 quantifier is null
    List<Player> datasetElements;

    public String generateSummary() {
        String summary = null;
        return summary;
    }

    public Double countDegreeOfTruth() {
        // summarizers product
        FuzzySet firstSummarizer = summarizers.get(0).getFuzzySet();
        List<FuzzySet> otherSummarizers = new ArrayList<>();
        for (LinguisticLabel summarizer : summarizers) {
            otherSummarizers.add(summarizer.getFuzzySet());
        }
        List<Double> summarizersProduct = firstSummarizer.getProductValues(datasetElements, otherSummarizers);

        List<Double> qualifiersProduct = new ArrayList<>();

        List<Double> finalProduct = new ArrayList<>();

        if (qualifiers.size() != 0) {
            FuzzySet firstQualifier = qualifiers.get(0).getFuzzySet();
            List<FuzzySet> otherQualifiers = new ArrayList<>();
            for (LinguisticLabel qualifier : qualifiers) {
                otherQualifiers.add(qualifier.getFuzzySet());
            }
            qualifiersProduct = firstQualifier.getProductValues(datasetElements, otherQualifiers);
            for (int i = 0; i < summarizersProduct.size(); i++) {
                finalProduct.add(Math.min(summarizersProduct.get(i), qualifiersProduct.get(i)));
            }
        }
        if (linguisticQuantifier instanceof AbsoluteQuantifier) {
            if (qualifiers.size() != 0) {
                return linguisticQuantifier.
                        getLabel().
                        getFuzzySet().
                        getMembershipFunction().
                        //cardinality
                        countMembership(finalProduct.stream().mapToDouble(Double::doubleValue).sum());
            }
        }
        if (qualifiers.size() == 0) {
            return linguisticQuantifier.
                    getLabel().
                    getFuzzySet().
                    getMembershipFunction().
                    countMembership(summarizersProduct.stream().mapToDouble(Double::doubleValue).sum()) /
                    datasetElements.size();
        }
        return linguisticQuantifier
                .getLabel()
                .getFuzzySet()
                .getMembershipFunction().countMembership(finalProduct.stream().mapToDouble(Double::doubleValue).sum()
                        / qualifiersProduct.stream().mapToDouble(Double::doubleValue).sum());

    }

    private Double countDegreeOfImprecision() {
        double product = 1;
        for (LinguisticLabel summarizer : summarizers) {
            product = product * summarizer.getFuzzySet().getDegreeOfFuzziness(datasetElements);
        }
        double product_root = Math.pow(product, summarizers.size());
        return 1 - product_root;
    }

    private Double countDegreeOfCovering() {
        return null;
    }

    private Double countDegreeOfAppropriateness() {
        List<Double> parameters_r = new ArrayList<>();

        for (LinguisticLabel summarizer : summarizers) {
            int sum = 0;
            for (Player player : summarizer.getFuzzySet().getSupport(datasetElements))
                sum += summarizer.getFuzzySet().getMembershipFunction().countMembership(player.getAge());
            parameters_r.add((double) sum / datasetElements.size());
        }

        double product = 1;
        for (double parameter_r : parameters_r) {
            product = product * (parameter_r - countDegreeOfCovering());
        }
        return Math.abs(product);
    }

    private Double countLengthOfASummary() {
        return 2.0 * Math.pow(0.5, summarizers.size());
    }

    private Double countDegreeOfQuantifierImprecision() {
//        if (linguisticQuantifier instanceof  AbsoluteQuantifier) {
//            return 1 - linguisticQuantifier.getLabel().getFuzzySet().getSupport(datasetElements).size();
//        } else {
//            return 1 -
//        }
        return null;
    }

    private Double countDegreeOfQuantifierRelativeCardinality() {
        return 1 - linguisticQuantifier.getLabel().getFuzzySet().getRelativeCardinality();
    }

    private Double countDegreeOfSummarizerRelativeCardinality() {
        double product = 1;
        for (LinguisticLabel summarizer : summarizers) {
            product = product * summarizer.getFuzzySet().getRelativeCardinality();
        }
        double product_root = Math.pow(product, 1d / summarizers.size());
        return 1 - product_root;
    }

    private Double countDegreeOfQualifierImprecision() {
        if (qualifiers.size() == 0) {
            return null;
        } else {
            double product = 1.0;
            for (LinguisticLabel qualifier : qualifiers) {
                product *= qualifier.getFuzzySet().getDegreeOfFuzziness(datasetElements);
            }
            double root = Math.round(Math.pow(product, 1.0 / qualifiers.size()));
            return 1 - root;
        }
    }

    private Double countDegreeOfQualifierRelativeCardinality() {
        double product = 1;
        for (LinguisticLabel qualifier : qualifiers) {
            product = product * qualifier.getFuzzySet().getRelativeCardinality();
        }
        double product_root = Math.pow(product, 1d / qualifiers.size());
        return 1 - product_root;
    }

    private Double countLengthOfAQualifier() {
        if (qualifiers.size() == 0) {
            return null;
        } else {
            return 2.0 * Math.pow(0.5, qualifiers.size());
        }
    }

}
