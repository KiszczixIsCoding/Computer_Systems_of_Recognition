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
            sum += summarizer.getFuzzySet().getSupport(datasetElements).size();
            parameters_r.add((double)sum / datasetElements.size());
        }

        double product = 1;
        for (double parameter_r : parameters_r) {
            product = product * (parameter_r - countDegreeOfCovering());
        }
        return Math.abs(product);
    }

    private Double countLengthOfASummary() {
        return null;
    }

    private Double countDegreeOfQuantifierImprecision() {
        return null;
    }

    private Double countDegreeOfQuantifierCardinality() {
        return null;
    }

    private Double countDegreeOfQualifierImprecision() {
        return null;
    }

    private Double countDegreeOfQualifierCardinality() {
        return null;
    }

    private Double countLengthOfAQualifier() {
        return null;
    }

}
