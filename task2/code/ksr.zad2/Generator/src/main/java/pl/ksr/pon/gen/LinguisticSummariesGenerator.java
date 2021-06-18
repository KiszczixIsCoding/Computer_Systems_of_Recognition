package pl.ksr.pon.gen;

import lombok.AllArgsConstructor;
import pl.ksr.pon.dao.Player;
import pl.ksr.pon.fuz.FuzzySet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
public class LinguisticSummariesGenerator {
    private static final double ACCURACY = 0.001;
    List<LinguisticLabel> qualifiers;
    List<LinguisticLabel> summarizers;
    LinguisticQuantifier linguisticQuantifier; // in multi subject, form 4 quantifier is null
    List<Player> datasetElements;

    public LinguisticSummary generateSummary(String text) {
        LinguisticSummary summary;
        summary = new LinguisticSummary();
        summary.setText(text);
        summary.setT1(countDegreeOfTruth());
        summary.setT2(countDegreeOfImprecision());
        summary.setT3(countDegreeOfCovering());
        summary.setT4(countDegreeOfAppropriateness());
        summary.setT5(countLengthOfASummary());
        summary.setT6(countDegreeOfQuantifierImprecision());
        summary.setT7(countDegreeOfQuantifierRelativeCardinality());
        summary.setT8(countDegreeOfSummarizerRelativeCardinality());
        summary.setT9(countDegreeOfQualifierImprecision());
        summary.setT10(countDegreeOfQualifierRelativeCardinality());
        summary.setT11(countLengthOfAQualifier());
        summary.setAverage(countDegreeOfImprecision());
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
                double valueToReturn = linguisticQuantifier.
                        getLabel().
                        getFuzzySet().
                        getMembershipFunction().
                        //cardinality
                        countMembership(finalProduct.stream().mapToDouble(Double::doubleValue).sum());
                if (valueToReturn < ACCURACY) {
                    return 0d;
                } else {
                    return valueToReturn;
                }
            } else {
                double valueToReturn = linguisticQuantifier.
                        getLabel().
                        getFuzzySet().
                        getMembershipFunction().
                        //cardinality
                        countMembership(summarizersProduct.stream().mapToDouble(Double::doubleValue).sum());
                if (valueToReturn < ACCURACY) {
                    return 0d;
                } else {
                    return valueToReturn;
                }
            }
        }
        if (qualifiers.size() == 0) {
            double valueToReturn = linguisticQuantifier.
                    getLabel().
                    getFuzzySet().
                    getMembershipFunction().
                    countMembership(summarizersProduct.stream().mapToDouble(Double::doubleValue).sum()
                            / datasetElements.size());
            if (valueToReturn < ACCURACY) {
                return 0d;
            } else {
                return valueToReturn;
            }
        }
        double valueToReturn = linguisticQuantifier
                .getLabel()
                .getFuzzySet()
                .getMembershipFunction().countMembership(finalProduct.stream().mapToDouble(Double::doubleValue).sum()
                        / qualifiersProduct.stream().mapToDouble(Double::doubleValue).sum());
        if (valueToReturn < ACCURACY) {
            return 0d;
        } else {
            return valueToReturn;
        }
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
        FuzzySet firstSummarizer = summarizers.get(0).getFuzzySet();
        List<FuzzySet> otherSummarizers = new ArrayList<>();
        for (LinguisticLabel summarizer : summarizers) {
            otherSummarizers.add(summarizer.getFuzzySet());
        }
        List<Double> summarizersProduct = firstSummarizer.getProductValues(datasetElements, otherSummarizers);
        int sumCounter = 0;
        for (Double sumProduct : summarizersProduct) {
            if (sumProduct > 0) {
                sumCounter++;
            }
        }

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
            int counter = 0;
            for (Double finProduct : finalProduct) {
                if (finProduct > 0) {
                    counter++;
                }
            }
            int qualifiersCounter = 0;
            for (Double qualProduct : qualifiersProduct) {
                if (qualProduct > 0) {
                    qualifiersCounter++;
                }
            }
            //nosniki
            return counter * 1.0 / qualifiersCounter;
            //qualifiers == 0
        } else {
            return sumCounter * 1.0 / datasetElements.size();
        }

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
        return 2.0 * Math.pow(0.5, qualifiers.size());
    }


}
