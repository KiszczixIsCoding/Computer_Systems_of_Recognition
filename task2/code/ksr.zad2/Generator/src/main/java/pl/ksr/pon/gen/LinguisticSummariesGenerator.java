package pl.ksr.pon.gen;

import lombok.AllArgsConstructor;
import pl.ksr.pon.dao.Player;
import pl.ksr.pon.fuz.FuzzySet;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import io.vavr.Tuple2;
import pl.ksr.pon.fuz.TrapezoidalMembershipFunction;
import pl.ksr.pon.fuz.TriangularMembershipFunction;

import java.util.concurrent.RecursiveTask;

import static pl.ksr.pon.gen.Utils.roundDouble;

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
        summary.setT1(roundDouble(countDegreeOfTruth(),2));
        summary.setT2(roundDouble(countDegreeOfImprecision(),2));
        summary.setT3(roundDouble(countDegreeOfCovering(),2));
        summary.setT4(roundDouble(countDegreeOfAppropriateness(),2));
        summary.setT5(roundDouble(countLengthOfASummary(),2));
        summary.setT6(roundDouble(countDegreeOfQuantifierImprecision(),2));
        summary.setT7(roundDouble(countDegreeOfQuantifierRelativeCardinality(),2));
        summary.setT8(roundDouble(countDegreeOfSummarizerRelativeCardinality(),2));
        summary.setT9(roundDouble(countDegreeOfQualifierImprecision(),2));
        summary.setT10(roundDouble(countDegreeOfQualifierRelativeCardinality(),2));
        summary.setT11(roundDouble(countLengthOfAQualifier(),2));
        return summary;
    }

    //T1
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

    //T2
    private Double countDegreeOfImprecision() {
        double product = 1;
        for (LinguisticLabel summarizer : summarizers) {
            product = product * summarizer.getFuzzySet().getDegreeOfFuzziness(datasetElements);
        }
        double product_root = Math.pow(product, (double) 1 / summarizers.size());
        return 1 - product_root;
    }

    //T3
    // todo: mozna przekształcic zeby wykorzystywal getSupport();
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

    //T4
    private Double countDegreeOfAppropriateness() {
        List<Double> parameters_r = new ArrayList<>();

        for (LinguisticLabel summarizer : summarizers) {
            int sum = 0;
            for (Player player : summarizer.getFuzzySet().getSupport(datasetElements))
//                sum += summarizer.getFuzzySet().getMembershipFunction().countMembership(player.getAge());
            // wg mnie zgodnie ze wzorami 8.51, 8.52 ze str. 158 z ang książki nasza suma to będzie po prostu liczba
            // elementow nalezacych do nosnika, bo wzor 8.52 mowi, że g_ij przyjmuje 1 jesli funkcja przynaleznosci
            // jest >0 - czyli wychodziloby ze to nosnik
            sum = summarizer.getFuzzySet().getSupport(datasetElements).size();
            parameters_r.add((double) sum / datasetElements.size());
        }

        double product = 1;
        for (double parameter_r : parameters_r) {
//            product = product * (parameter_r - countDegreeOfCovering());
            // a tu wg mnie najpierw mnożymy, a koncowy wynik mnozenia odejmujemy
            product *= parameter_r;
        }
        product -= countDegreeOfCovering();
        double valueToReturn = Math.abs(product);
        if (valueToReturn < ACCURACY) {
            return 0d;
        } else {
            return valueToReturn;
        }
    }

    //T5
    private Double countLengthOfASummary() {
        return 2.0 * Math.pow(0.5, summarizers.size());
    }

    //T6
    private Double countDegreeOfQuantifierImprecision() {
        if (linguisticQuantifier.getLabel().getFuzzySet().getMembershipFunction() instanceof TrapezoidalMembershipFunction ||
                linguisticQuantifier.getLabel().getFuzzySet().getMembershipFunction() instanceof TriangularMembershipFunction) {
            Tuple2<Double, Double> tuple =
                    linguisticQuantifier.getLabel().getFuzzySet().getMembershipFunction().countConstraints(0);
            // zwracamy długosc, gdzie wykres jest "nad" zerem
            if (tuple._2 != 0) {
                return 1 - (tuple._2 - tuple._1) / datasetElements.size();
            } else {
                //jesli druga z wartości w krotce jest "0" tzn. ze tylko w jednym miejscu wykres przecina sie z zerem,
                // a wiec mamy albo poczatkowy wykres albo koncowy, sprawdzamy wartosc chwile po przecięciu się osi z zerem,
                // jeśli jest != 0 to wykres rosnacy, a wiec wykres z prawej strony, jesli jest 0 to wykres malejacy,
                // a wiec z lewej strony
                if (linguisticQuantifier.getLabel().getFuzzySet().getMembershipFunction().countMembership(tuple._1 + 1) == 0) {
                    return 1 - tuple._1 / datasetElements.size();
                } else {
                    return 1 - (datasetElements.size() - tuple._1) / datasetElements.size();
                }
            }
            // Jesli gaussowska no to chyba zawsze będzie 0, skoro ona dązy do nieskończoności
        } else {
            return 0.0;
        }
    }

    //T7
    private Double countDegreeOfQuantifierRelativeCardinality() {
        if (linguisticQuantifier instanceof AbsoluteQuantifier) {
            return 1 - linguisticQuantifier.getLabel().getFuzzySet().getMembershipFunction().getArea() /
                    datasetElements.size();
        } else {
            return 1 - linguisticQuantifier.getLabel().getFuzzySet().getMembershipFunction().getArea();
        }
    }

    //T8
    private Double countDegreeOfSummarizerRelativeCardinality() {
        double product = 1;
        for (LinguisticLabel summarizer : summarizers) {
            product = product * summarizer.getFuzzySet().getRelativeCardinality();
        }
        double product_root = Math.pow(product, 1d / summarizers.size());
        return 1 - product_root;
    }

    //T9
    private Double countDegreeOfQualifierImprecision() {
        if (qualifiers.size() == 0) {
            return 0.0;
        } else {
            double product = 1.0;
            for (LinguisticLabel qualifier : qualifiers) {
                product *= qualifier.getFuzzySet().getDegreeOfFuzziness(datasetElements);
            }
            double root = Math.pow(product, 1.0 / qualifiers.size());
            return 1 - root;
        }
    }

    //T10
    private Double countDegreeOfQualifierRelativeCardinality() {
        if (qualifiers.size() == 0) {
            return 0.0;
        } else {
            double product = 1;
            for (LinguisticLabel qualifier : qualifiers) {
                product = product * qualifier.getFuzzySet().getRelativeCardinality();
            }
            double product_root = Math.pow(product, 1d / qualifiers.size());
            return 1 - product_root;
        }
    }

    //T11
    private Double countLengthOfAQualifier() {
        if (qualifiers.size() == 0) {
            return 1.0;
        } else {
            return 2.0 * Math.pow(0.5, qualifiers.size());
        }
    }

}
