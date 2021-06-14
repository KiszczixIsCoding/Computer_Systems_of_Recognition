package pl.ksr.pon.gen;

import pl.ksr.pon.dao.Player;

import java.util.ArrayList;
import java.util.List;

public class LinguisticSummariesGenerator {
    List<LinguisticLabel> qualifiers;
    List<LinguisticLabel> summarizers;
    LinguisticQuantifier linguisticQuantifier; // in multi subject, form 4 quantifier is null
    List<Player> datasetElements;

    public String generateSummary() {
        String summary = null;
        return summary;
    }

    private Double countDegreeOfTruth() {
        return null;
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
            parameters_r.add((double)sum / datasetElements.size());
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
        return 1 - linguisticQuantifier.getLabel().getFuzzySet().getRelativeCardinality(datasetElements);
    }

    private Double countDegreeOfSummarizerRelativeCardinality() {
        double product = 1;
        for (LinguisticLabel summarizer : summarizers) {
            product = product * summarizer.getFuzzySet().getRelativeCardinality(datasetElements);
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
            product = product * qualifier.getFuzzySet().getRelativeCardinality(datasetElements);
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
