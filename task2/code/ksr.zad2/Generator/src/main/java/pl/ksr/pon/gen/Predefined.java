package pl.ksr.pon.gen;

import com.google.common.collect.Range;
import pl.ksr.pon.dao.Player;
import pl.ksr.pon.fuz.*;

import java.util.Arrays;
import java.util.List;

public class Predefined {
    public static List<LinguisticVariable> getPredefinedLinguisticVariables(List<Player> players) {
        //1.wiek zawodnika
        LinguisticLabel juniorLabel = new LinguisticLabel("w wieku juniorskim",
                new FuzzySet(new LeftTrapezoidalMembershipFunction(18.0, 20.0, 24.0), players, "age"));
        LinguisticLabel youngLabel = new LinguisticLabel("w młodym wieku",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(20.0, 24.0, 26.0, 28.0), players,"age"));
        LinguisticLabel middleAgedLabel = new LinguisticLabel("w średnim wieku",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(26.0, 28.0, 30.0, 32.0), players,"age"));
        LinguisticLabel experiencedLabel = new LinguisticLabel("doświadczonych wiekiem",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(30.0, 32.0, 34.0, 36.0), players, "age"));
        LinguisticLabel oldLabel = new LinguisticLabel("w starym wieku",
                new FuzzySet(new RightTrapezoidalMembershipFunction(34.0, 36.0, 44.0), players,"age"));
        List<LinguisticLabel> ageLabels = Arrays.asList(juniorLabel, youngLabel, middleAgedLabel, experiencedLabel, oldLabel);
        //Range teoretycznie powinny być w tym przypadku Integer ale jeśli będziemy szli po zawodnikach to nie powinno to przeszkadzać
        LinguisticVariable ageLinguisticVariable = new LinguisticVariable("wiek zawodnika",
                Range.closed(18.0, 36.0), ageLabels);

        //2.wzrost zawodnika
        MembershipFunction shortMembership = new LeftTrapezoidalMembershipFunction(160.03, 185.0, 195.0);
        LinguisticLabel veryShortLabel = new LinguisticLabel("bardzo niskiego wzrostu",
                new FuzzySet(new ExponentialMembershipFunction(2, shortMembership), players,"height"));
        LinguisticLabel shortLabel = new LinguisticLabel("niskiego wzrostu",
                new FuzzySet(shortMembership, players,"height"));
        LinguisticLabel middleHeightLabel = new LinguisticLabel("średniego wzrostu",
                new FuzzySet(new BothSidesTriangularMembershipFunction(185.0, 195.0, 205.0), players,"height"));
        MembershipFunction tallMembership = new RightTrapezoidalMembershipFunction(200.0, 210.0, 231.14);
        LinguisticLabel tallLabel = new LinguisticLabel("wysokiego wzrostu",
                new FuzzySet(tallMembership, players,"height"));
        LinguisticLabel veryTallLabel = new LinguisticLabel("bardzo wysokiego wzrostu",
                new FuzzySet(new ExponentialMembershipFunction(2, tallMembership), players,"height"));
        List<LinguisticLabel> heightLabels = Arrays.asList(veryShortLabel, shortLabel, middleHeightLabel, tallLabel, veryTallLabel);
        LinguisticVariable heightLinguisticVariable = new LinguisticVariable("wzrost zawodnika",
                Range.closed(160.03, 231.14), heightLabels);

        //3.waga zawodnika
        MembershipFunction lightMembership = new LeftTrapezoidalMembershipFunction(60.33, 85.0, 95.0);
        LinguisticLabel veryLightLabel = new LinguisticLabel("bardzo lekkiej wagi",
                new FuzzySet(new ExponentialMembershipFunction(2,lightMembership), players,"weight"));
        LinguisticLabel lightLabel = new LinguisticLabel("lekkiej wagi",
                new FuzzySet(lightMembership, players,"weight"));
        LinguisticLabel middleWeightLabel = new LinguisticLabel("przeciętnej wagi",
                new FuzzySet(new BothSidesTriangularMembershipFunction(90.0, 100.0, 110.0), players,"weight"));
        MembershipFunction heavyMembership = new RightTrapezoidalMembershipFunction(105.0, 115.0, 163.29);
        LinguisticLabel heavyLabel = new LinguisticLabel("cięzkiej wagi",
                new FuzzySet(heavyMembership, players,"weight"));
        LinguisticLabel veryHeavyLabel = new LinguisticLabel("bardzo cięzkiej wagi",
                new FuzzySet(new ExponentialMembershipFunction(2, heavyMembership), players,"weight"));
        List<LinguisticLabel> weightLabels = Arrays.asList(veryLightLabel, lightLabel, middleWeightLabel, heavyLabel, veryHeavyLabel);
        LinguisticVariable weightLinguisticVariable = new LinguisticVariable("waga zawodnika",
                Range.closed(60.33, 163.29), weightLabels);

        //4. kolejność wyboru w drafcie
        LinguisticLabel instantlyLabel = new LinguisticLabel("błyskawicznie wybrani w drafcie",
                new FuzzySet(new LeftTrapezoidalMembershipFunction(0.0, 6.0, 14.0), players,"draftNumber"));
        LinguisticLabel quickLabel = new LinguisticLabel("szybko wybrani w drafcie",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(10.0, 14.0, 22.0, 30.0), players,"draftNumber"));
        LinguisticLabel middlePickLabel = new LinguisticLabel("normalnie wybrani w drafcie",
                new FuzzySet(new BothSidesTriangularMembershipFunction(20.0, 30.0, 40.0), players,"draftNumber"));
        LinguisticLabel lateLabel = new LinguisticLabel("późno wybrani w drafcie",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(36.0, 44.0, 48.0, 56.0), players,"draftNumber"));
        // tu dodałem jako ostateczną wartość 200, bo widziałem że w bazie danych jakieś pojedyncze wartości
        // przekaraczały 60 (w sprawku mamy 60)
        LinguisticLabel inTheEndLabel = new LinguisticLabel("wybrani na końcu w drafcie",
                new FuzzySet(new RightTrapezoidalMembershipFunction(51.0, 56.0, 200.0), players,"draftNumber"));
        List<LinguisticLabel> draftPickLabels = Arrays.asList(instantlyLabel, quickLabel, middlePickLabel, lateLabel, inTheEndLabel);
        //Range teoretycznie powinny być w tym przypadku Integer ale jeśli będziemy szli po zawodnikach to nie powinno to przeszkadzać
        LinguisticVariable draftPickLinguisticVariable = new LinguisticVariable("kolejność wyboru w drafcie",
                Range.closed(0.0, 200.0), draftPickLabels);

        //5. rozegrane gry w sezonie
        LinguisticLabel negligibleLabel = new LinguisticLabel("znikoma liczba rozegranych gier w sezonie",
                new FuzzySet(new LeftTrapezoidalMembershipFunction(1.0,10.0,26.0), players,"gamesPlayed"));
        LinguisticLabel smallGamesLabel = new LinguisticLabel("mało rozegranych gier w sezonie",
                new FuzzySet(new BothSidesTriangularMembershipFunction(20.0, 30.0, 40.0), players,"gamesPlayed"));
        LinguisticLabel middleGamesLabel = new LinguisticLabel("średnia liczba rozegranych gier w sezonie",
                new FuzzySet(new BothSidesTriangularMembershipFunction(30.0, 46.0, 62.0), players,"gamesPlayed"));
        LinguisticLabel bigGamesLabel = new LinguisticLabel("dużo rozegranych gier w sezonie",
                new FuzzySet(new BothSidesTriangularMembershipFunction(49.0, 65.0, 81.0), players,"gamesPlayed"));
        LinguisticLabel maximumLabel = new LinguisticLabel("maksymalna liczna rozegranych gier w sezonie",
                new FuzzySet(new RightTrapezoidalMembershipFunction(70.0, 80.0, 85.0), players,"gamesPlayed"));
        List<LinguisticLabel> gamesPlayedLabels = Arrays.asList(negligibleLabel, smallGamesLabel, middleGamesLabel,
                bigGamesLabel, maximumLabel);
        //Range teoretycznie powinny być w tym przypadku Integer ale jeśli będziemy szli po zawodnikach to nie powinno to przeszkadzać
        LinguisticVariable gamesPlayedLinguisticVariable = new LinguisticVariable("rozegrane gry w sezonie",
                Range.closed(1.0, 85.0), gamesPlayedLabels);

        //6. średnia liczba zdobytych punktów na mecz
        MembershipFunction fewPointsMembership = new LeftTrapezoidalMembershipFunction(0.0, 1.0, 11.0);
        LinguisticLabel veryFewPointsLabel = new LinguisticLabel("bardzo mało zdobytych punktów na mecz",
                new FuzzySet(new ExponentialMembershipFunction(2, fewPointsMembership), players,"averagePoints"));
        LinguisticLabel fewPointsLabel = new LinguisticLabel("mało zdobytych punktów na mecz",
                new FuzzySet(fewPointsMembership, players,"averagePoints"));
        LinguisticLabel enoughPointsLabel = new LinguisticLabel("dostateczna liczba zdobytych punktów na mecz",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(1.0, 6.0, 10.0, 16.0), players,"averagePoints"));
        MembershipFunction lotOfPointsMembership = new RightTrapezoidalMembershipFunction(11.0, 19.0, 36.1);
        LinguisticLabel lotOfPointsLabel = new LinguisticLabel("dużo zdobytych punktów na mecz",
                new FuzzySet(lotOfPointsMembership, players,"averagePoints"));
        LinguisticLabel veryLotOfPointsLabel = new LinguisticLabel("bardzo dużo zdobytych punktów na mecz",
                new FuzzySet(new ExponentialMembershipFunction(2, lotOfPointsMembership), players,"averagePoints"));
        List<LinguisticLabel> pointsScoredLabels = Arrays.asList(veryFewPointsLabel, fewPointsLabel, enoughPointsLabel,
                lotOfPointsLabel, veryLotOfPointsLabel);
        LinguisticVariable pointsScoredLinguisticVariable = new LinguisticVariable("średnia liczba zdobytych punktów na mecz",
                Range.closed(0.0, 36.1),pointsScoredLabels);

        //7. średnia liczba zbiórek na mecz
        MembershipFunction fewReboundsMembership = new LeftTrapezoidalMembershipFunction(0.0, 1.0, 5.0);
        LinguisticLabel veryFewReboundsLabel = new LinguisticLabel("bardzo mało zbiórek na mecz",
                new FuzzySet(new ExponentialMembershipFunction(2, fewReboundsMembership), players,"averageRebounds"));
        LinguisticLabel fewReboundsLabel = new LinguisticLabel("mało zbiórek na mecz",
                new FuzzySet(fewReboundsMembership, players,"averageRebounds"));
        LinguisticLabel enoughReboundsLabel = new LinguisticLabel("dostateczna liczba zbiórek na mecz",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(2.0, 4.0, 5.0, 6.0), players,"averageRebounds"));
        MembershipFunction lotOfReboundsMembership = new RightTrapezoidalMembershipFunction(4, 6.5, 16.3);
        LinguisticLabel lotOfReboundsLabel = new LinguisticLabel("dużo zbiórek na mecz",
                new FuzzySet(lotOfReboundsMembership, players,"averageRebounds"));
        LinguisticLabel veryLotOfReboundsLabel = new LinguisticLabel("bardzo dużo zbiórek na mecz",
                new FuzzySet(new ExponentialMembershipFunction(2, lotOfReboundsMembership), players,"averageRebounds"));
        List<LinguisticLabel> reboundsLabels = Arrays.asList(veryFewReboundsLabel, fewReboundsLabel, enoughReboundsLabel,
                lotOfReboundsLabel, veryLotOfReboundsLabel);
        LinguisticVariable reboundsNumberLinguisticVariable = new LinguisticVariable("średnia liczba zbiórek na mecz",
                Range.closed(0.0, 16.3), reboundsLabels);

        //8. średnia liczba asyst na mecz
        MembershipFunction fewAssistsMembership = new LeftTrapezoidalMembershipFunction(0.0, 0.5, 1.5);
        LinguisticLabel veryFewAssistsLabel = new LinguisticLabel("bardzo mało asyst na mecz",
                new FuzzySet(new ExponentialMembershipFunction(2, fewAssistsMembership), players,"averageAssists"));
        LinguisticLabel fewAssistsLabel = new LinguisticLabel("mało asyst na mecz",
                new FuzzySet(fewAssistsMembership,players,"averageAssists"));
        LinguisticLabel enoughAssistsLabel = new LinguisticLabel("dostateczna liczba asyst na mecz",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(0.5, 1.5, 2.0, 3.0), players,"averageAssists"));
        MembershipFunction lotOfAssistsMembership = new RightTrapezoidalMembershipFunction(2, 3.6, 11.7);
        LinguisticLabel lotOfAssistsLabel = new LinguisticLabel("dużo asyst na mecz",
                new FuzzySet(lotOfAssistsMembership, players,"averageAssists"));
        LinguisticLabel veryLotOfAssistsLabel = new LinguisticLabel("bardzo dużo asyst na mecz",
                new FuzzySet(new ExponentialMembershipFunction(2, lotOfAssistsMembership), players,"averageAssists"));
        List<LinguisticLabel> assistsNumberLabels = Arrays.asList(veryFewAssistsLabel, fewAssistsLabel, enoughAssistsLabel,
                lotOfAssistsLabel, veryLotOfAssistsLabel);
        LinguisticVariable assistsNumberLinguisticVariable = new LinguisticVariable("średnia liczba asyst na mecz",
                Range.closed(0.0, 11.7), assistsNumberLabels);

        //9. wpływ na drużynę
        LinguisticLabel fatalImpactLabel = new LinguisticLabel("fatalny wpływ na drużynę",
                new FuzzySet(new LeftTrapezoidalMembershipFunction(-100.0, -15.0, -5.0), players,"teamImpact"));
        LinguisticLabel negativeImpactLabel = new LinguisticLabel("negatywny wpływ na drużynę",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(-15.0, -7.0, -2.5, 0.0), players,"teamImpact"));
        LinguisticLabel neutralImpactLabel = new LinguisticLabel("neutralny wpływ na drużynę",
                new FuzzySet(new BothSidesTriangularMembershipFunction(-2.5, 0.0, 2.5), players,"teamImpact"));
        LinguisticLabel positiveImpactLabel = new LinguisticLabel("pozytywny wpływ na drużynę",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(0, 3.2, 7.0, 13.4), players,"teamImpact"));
        LinguisticLabel idealImpactLabel = new LinguisticLabel("idealny wpływ na drużynę",
                new FuzzySet(new RightTrapezoidalMembershipFunction(5.0, 13.0, 100.0), players,"teamImpact"));
        List<LinguisticLabel> impactLabels = Arrays.asList(fatalImpactLabel, negativeImpactLabel, neutralImpactLabel,
                positiveImpactLabel, idealImpactLabel);
        LinguisticVariable impactLinguisticVariable = new LinguisticVariable("wpływ na drużynę",
                Range.closed(-100.0, 100.0), impactLabels);

        //10. skuteczność rzutów
        LinguisticLabel fatalAccuracyLabel = new LinguisticLabel("fatalną skuteczność rzutów",
                new FuzzySet(new LeftTrapezoidalMembershipFunction(0.0, 0.35, 0.45), players,"throwAccuracy"));
        LinguisticLabel ineffectiveAccuracyLabel = new LinguisticLabel("nieskuteczni z rzutów",
                new FuzzySet(new GaussianMembershipFunction(0.4, 0.003), players,"throwAccuracy"));
        LinguisticLabel averageAccuracyLabel = new LinguisticLabel("przeciętną skuteczność rzutów",
                new FuzzySet(new GaussianMembershipFunction(0.5, 0.001), players,"throwAccuracy"));
        LinguisticLabel effectiveAccuracyLabel = new LinguisticLabel("skuteczni z rzutów",
                new FuzzySet(new GaussianMembershipFunction(0.57, 0.001), players,"throwAccuracy"));
        LinguisticLabel idealAccuracyLabel = new LinguisticLabel("idealną skuteczność rzutów",
                new FuzzySet(new RightTrapezoidalMembershipFunction(0.58, 0.63, 1.0), players,"throwAccuracy"));
        List<LinguisticLabel> accuracyLabels = Arrays.asList(fatalAccuracyLabel, ineffectiveAccuracyLabel, averageAccuracyLabel,
                effectiveAccuracyLabel, idealAccuracyLabel);
        LinguisticVariable accuracyLinguisticVariable = new LinguisticVariable("skuteczność rzutów",
                Range.closed(0.0, 1.0), accuracyLabels);

        //11. procent asyst
        LinguisticLabel fatalAssistsPercentLabel = new LinguisticLabel("fatalny procent asyst",
                new FuzzySet(new LeftTrapezoidalMembershipFunction(0, 0.01, 0.05), players,"percentAssists"));
        LinguisticLabel smallAssistsPercentLabel = new LinguisticLabel("mały procent asyst",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(0.01, 0.05, 0.06, 0.10), players,"percentAssists"));
        LinguisticLabel averageAssistsPercentLabel = new LinguisticLabel("przeciętny procent asysyt",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(0.06, 0.14, 0.15, 0.19), players,"percentAssists"));
        LinguisticLabel bigAssistsPercentLabel = new LinguisticLabel("duży procent asysyt",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(0.15, 0.20, 0.23, 0.31), players,"percentAssists"));
        LinguisticLabel idealAssistsPercentLabel = new LinguisticLabel("idealny procent asyst",
                new FuzzySet(new RightTrapezoidalMembershipFunction(0.23, 0.31, 1.0), players,"percentAssists"));
        List<LinguisticLabel> assistsPercentLabels = Arrays.asList(fatalAssistsPercentLabel, smallAssistsPercentLabel,
                averageAssistsPercentLabel, bigAssistsPercentLabel, idealAssistsPercentLabel);
        LinguisticVariable assistsPercentLinguisticVariable = new LinguisticVariable("procent asyst",
                Range.closed(0.0, 1.0), assistsPercentLabels);


        List<LinguisticVariable> variables = Arrays.asList(ageLinguisticVariable, heightLinguisticVariable,
                weightLinguisticVariable, draftPickLinguisticVariable, gamesPlayedLinguisticVariable,
                pointsScoredLinguisticVariable, reboundsNumberLinguisticVariable, assistsNumberLinguisticVariable,
                impactLinguisticVariable, accuracyLinguisticVariable, assistsPercentLinguisticVariable);

        return variables;
    }

    public static List<AbsoluteQuantifier> getAbsoluteQuantifiers() {
        LinguisticLabel moreThan0Label = new LinguisticLabel("Więcej niż 0",
                new FuzzySet(new LeftTriangularMembershipFunction(0.0, 1500.0), null, ""));
        LinguisticLabel about2000Label = new LinguisticLabel("Około 2000",
                new FuzzySet(new BothSidesTriangularMembershipFunction(500.0, 2000.0, 3500.0), null,""));
        LinguisticLabel about4000Label = new LinguisticLabel("Około 4000",
                new FuzzySet(new BothSidesTriangularMembershipFunction(2500.0, 4000.0, 5500.0), null,""));
        LinguisticLabel about6000Label = new LinguisticLabel("Około 6000",
                new FuzzySet(new BothSidesTriangularMembershipFunction(4500.0, 6000.0, 7500.0), null,""));
        LinguisticLabel about8000Label = new LinguisticLabel("Około 8000",
                new FuzzySet(new BothSidesTriangularMembershipFunction(6500.0, 8000.0, 9500.0), null,""));
        LinguisticLabel almost11144Label = new LinguisticLabel("Prawie 11144",
                new FuzzySet(new RightTriangularMembershipFunction(8500.0, 11144.0), null,""));
        AbsoluteQuantifier moreThan0Quantifier = new AbsoluteQuantifier(moreThan0Label,
                Range.closed(0.0, 1500.0));
        AbsoluteQuantifier about2000Quantifier = new AbsoluteQuantifier(about2000Label,
                Range.closed(500.0, 3500.0));
        AbsoluteQuantifier about4000Quantifier = new AbsoluteQuantifier(about4000Label,
                Range.closed(2500.0, 5500.0));
        AbsoluteQuantifier about6000Quantifier = new AbsoluteQuantifier(about6000Label,
                Range.closed(4500.0, 7500.0));
        AbsoluteQuantifier about8000Quantifier = new AbsoluteQuantifier(about8000Label,
                Range.closed(6500.0, 9500.0));
        AbsoluteQuantifier almost11144Quantifier = new AbsoluteQuantifier(almost11144Label,
                Range.closed(8500.0, 11144.0));
        List<AbsoluteQuantifier> absoluteQuantifiers = Arrays.asList(moreThan0Quantifier, about2000Quantifier,
                about4000Quantifier, about6000Quantifier, about8000Quantifier, almost11144Quantifier);
        return absoluteQuantifiers;
    }

    public static List<RelativeQuantifier> getRelativeQuantifiers() {
        LinguisticLabel almostNoneLabel = new LinguisticLabel("Prawie żaden",
                new FuzzySet(new GaussianMembershipFunction(0.0, 0.01), null,""));
        LinguisticLabel aboutQuarterLabel = new LinguisticLabel("Około 1/4",
                new FuzzySet(new GaussianMembershipFunction(0.25, 0.01), null,""));
        LinguisticLabel aboutHalfLabel = new LinguisticLabel("Około 1/2",
                new FuzzySet(new GaussianMembershipFunction(0.5, 0.01), null,""));
        LinguisticLabel aboutThreeQuartersLabel = new LinguisticLabel("Około 3/4",
                new FuzzySet(new GaussianMembershipFunction(0.75, 0.01), null,""));
        LinguisticLabel almostAllLabel = new LinguisticLabel("Prawie wszystkie",
                new FuzzySet(new GaussianMembershipFunction(1.0, 0.01), null,""));
        RelativeQuantifier almostNoneQuantifier = new RelativeQuantifier(almostNoneLabel);
        RelativeQuantifier aboutQuarterQuantifier = new RelativeQuantifier(aboutQuarterLabel);
        RelativeQuantifier aboutHalfQuantifier = new RelativeQuantifier(aboutHalfLabel);
        RelativeQuantifier aboutThreeQuartersQuantifier = new RelativeQuantifier(aboutThreeQuartersLabel);
        RelativeQuantifier almostAllQuantifier = new RelativeQuantifier(almostAllLabel);
        List<RelativeQuantifier> relativeQuantifiers = Arrays.asList(almostNoneQuantifier, aboutQuarterQuantifier,
                aboutHalfQuantifier, aboutThreeQuartersQuantifier, almostAllQuantifier);
        return relativeQuantifiers;
    }

}
