package pl.ksr.pon.gen;

import com.google.common.collect.Range;
import pl.ksr.pon.fuz.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Predefined {
    public static List<LinguisticVariable> getPredefinedLinguisticVariables() {
        //1.wiek zawodnika
        Label juniorLabel = new Label("w wieku juniorskim",
                new FuzzySet(new LeftTrapezoidalMembershipFunction(18.0, 20.0, 24.0)));
        Label youngLabel = new Label("w młodym wieku",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(20.0, 24.0, 26.0, 28.0)));
        Label middleAgedLabel = new Label("w średnim wieku",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(26.0, 28.0, 30.0, 32.0)));
        Label experiencedLabel = new Label("doświadczonych wiekiem",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(30.0, 32.0, 34.0, 36.0)));
        Label oldLabel = new Label("w starym wieku",
                new FuzzySet(new RightTrapezoidalMembershipFunction(34.0, 36.0, 44.0)));
        List<Label> ageLabels = Arrays.asList(juniorLabel, youngLabel, middleAgedLabel, experiencedLabel, oldLabel);
        //Range teoretycznie powinny być w tym przypadku Integer ale jeśli będziemy szli po zawodnikach to nie powinno to przeszkadzać
        LinguisticVariable ageLinguisticVariable = new LinguisticVariable("wiek zawodnika",
                Range.closed(18.0, 36.0), ageLabels);

        //2.wzrost zawodnika
        MembershipFunction shortMembership = new LeftTrapezoidalMembershipFunction(160.03, 185.0, 195.0);
        Label veryShortLabel = new Label("bardzo niskiego wzrostu",
                new FuzzySet(new ExponentialMembershipFunction(2, shortMembership)));
        Label shortLabel = new Label("niskiego wzrostu",
                new FuzzySet(shortMembership));
        Label middleHeightLabel = new Label("średniego wzrostu",
                new FuzzySet(new BothSidesTriangularMembershipFunction(185.0, 195.0, 205.0)));
        MembershipFunction tallMembership = new RightTrapezoidalMembershipFunction(200.0, 210.0, 231.14);
        Label tallLabel = new Label("wysokiego wzrostu",
                new FuzzySet(tallMembership));
        Label veryTallLabel = new Label("bardzo wysokiego wzrostu",
                new FuzzySet(new ExponentialMembershipFunction(2, tallMembership)));
        List<Label> heightLabels = Arrays.asList(veryShortLabel, shortLabel, middleHeightLabel, tallLabel, veryTallLabel);
        LinguisticVariable heightLinguisticVariable = new LinguisticVariable("wzrost zawodnika",
                Range.closed(160.03, 231.14), heightLabels);

        //3.waga zawodnika
        MembershipFunction lightMembership = new LeftTrapezoidalMembershipFunction(60.33, 85.0, 95.0);
        Label veryLightLabel = new Label("bardzo lekkiej wagi",
                new FuzzySet(new ExponentialMembershipFunction(2,lightMembership)));
        Label lightLabel = new Label("lekkiej wagi",
                new FuzzySet(lightMembership));
        Label middleWeightLabel = new Label("przeciętnej wagi",
                new FuzzySet(new BothSidesTriangularMembershipFunction(90.0, 100.0, 110.0)));
        MembershipFunction heavyMembership = new RightTrapezoidalMembershipFunction(105.0, 115.0, 163.29);
        Label heavyLabel = new Label("cięzkiej wagi",
                new FuzzySet(heavyMembership));
        Label veryHeavyLabel = new Label("bardzo cięzkiej wagi",
                new FuzzySet(new ExponentialMembershipFunction(2, heavyMembership)));
        List<Label> weightLabels = Arrays.asList(veryLightLabel, lightLabel, middleWeightLabel, heavyLabel, veryHeavyLabel);
        LinguisticVariable weightLinguisticVariable = new LinguisticVariable("waga zawodnika",
                Range.closed(60.33, 163.29), weightLabels);

        //4. kolejność wyboru w drafcie
        Label instantlyLabel = new Label("błyskawicznie wybrani w drafcie",
                new FuzzySet(new RightTrapezoidalMembershipFunction(0.0, 6.0, 14.0)));
        Label quickLabel = new Label("szybko wybrani w drafcie",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(10.0, 14.0, 22.0, 30.0)));
        Label middlePickLabel = new Label("normalnie wybrani w drafcie",
                new FuzzySet(new BothSidesTriangularMembershipFunction(20.0, 30.0, 40.0)));
        Label lateLabel = new Label("późno wybrani w drafcie",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(36.0, 44.0, 48.0, 56.0)));
        // tu dodałem jako ostateczną wartość 200, bo widziałem że w bazie danych jakieś pojedyncze wartości
        // przekaraczały 60 (w sprawku mamy 60)
        Label inTheEndLabel = new Label("wybrani na końcu w drafcie",
                new FuzzySet(new LeftTrapezoidalMembershipFunction(51.0, 56.0, 200.0)));
        List<Label> draftPickLabels = Arrays.asList(instantlyLabel, quickLabel, middlePickLabel, lateLabel, inTheEndLabel);
        //Range teoretycznie powinny być w tym przypadku Integer ale jeśli będziemy szli po zawodnikach to nie powinno to przeszkadzać
        LinguisticVariable draftPickLinguisticVariable = new LinguisticVariable("kolejność wyboru w drafcie",
                Range.closed(0.0, 200.0), draftPickLabels);

        //5. rozegrane gry w sezonie
        Label negligibleLabel = new Label("znikoma liczba rozegranych gier w sezonie",
                new FuzzySet(new LeftTrapezoidalMembershipFunction(1.0,10.0,26.0)));
        Label smallGamesLabel = new Label("mało rozegranych gier w sezonie",
                new FuzzySet(new BothSidesTriangularMembershipFunction(20.0, 30.0, 40.0)));
        Label middleGamesLabel = new Label("średnia liczba rozegranych gier w sezonie",
                new FuzzySet(new BothSidesTriangularMembershipFunction(30.0, 46.0, 62.0)));
        Label bigGamesLabel = new Label("dużo rozegranych gier w sezonie",
                new FuzzySet(new BothSidesTriangularMembershipFunction(49.0, 65.0, 81.0)));
        Label maximumLabel = new Label("maksymalna liczna rozegranych gier w sezonie",
                new FuzzySet(new RightTrapezoidalMembershipFunction(70.0, 80.0, 85.0)));
        List<Label> gamesPlayedLabels = Arrays.asList(negligibleLabel, smallGamesLabel, middleGamesLabel,
                bigGamesLabel, maximumLabel);
        //Range teoretycznie powinny być w tym przypadku Integer ale jeśli będziemy szli po zawodnikach to nie powinno to przeszkadzać
        LinguisticVariable gamesPlayedLinguisticVariable = new LinguisticVariable("rozegrane gry w sezonie",
                Range.closed(1.0, 85.0), gamesPlayedLabels);

        //6. średnia liczba zdobytych punktów na mecz
        MembershipFunction fewPointsMembership = new LeftTrapezoidalMembershipFunction(0.0, 1.0, 11.0);
        Label veryFewPointsLabel = new Label("bardzo mało zdobytych punktów na mecz",
                new FuzzySet(new ExponentialMembershipFunction(2, fewPointsMembership)));
        Label fewPointsLabel = new Label("mało zdobytych punktów na mecz",
                new FuzzySet(fewPointsMembership));
        Label enoughPointsLabel = new Label("dostateczna liczba zdobytych punktów na mecz",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(1.0, 6.0, 10.0, 16.0)));
        MembershipFunction lotOfPointsMembership = new RightTrapezoidalMembershipFunction(11.0, 19.0, 36.1);
        Label lotOfPointsLabel = new Label("dużo zdobytych punktów na mecz",
                new FuzzySet(lotOfPointsMembership));
        Label veryLotOfPointsLabel = new Label("bardzo dużo zdobytych punktów na mecz",
                new FuzzySet(new ExponentialMembershipFunction(2, lotOfPointsMembership)));
        List<Label> pointsScoredLabels = Arrays.asList(veryFewPointsLabel, fewPointsLabel, enoughPointsLabel,
                lotOfPointsLabel, veryLotOfPointsLabel);
        LinguisticVariable pointsScoredLinguisticVariable = new LinguisticVariable("średnia liczba zdobytych punktów na mecz",
                Range.closed(0.0, 36.1),pointsScoredLabels);

        //7. średnia liczba zbiórek na mecz
        MembershipFunction fewReboundsMembership = new LeftTrapezoidalMembershipFunction(0.0, 1.0, 5.0);
        Label veryFewReboundsLabel = new Label("bardzo mało zbiórek na mecz",
                new FuzzySet(new ExponentialMembershipFunction(2, fewReboundsMembership)));
        Label fewReboundsLabel = new Label("mało zbiórek na mecz",
                new FuzzySet(fewReboundsMembership));
        Label enoughReboundsLabel = new Label("dostateczna liczba zbiórek na mecz",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(2.0, 4.0, 5.0, 6.0)));
        MembershipFunction lotOfReboundsMembership = new RightTrapezoidalMembershipFunction(4, 6.5, 16.3);
        Label lotOfReboundsLabel = new Label("dużo zbiórek na mecz",
                new FuzzySet(lotOfReboundsMembership));
        Label veryLotOfReboundsLabel = new Label("bardzo dużo zbiórek na mecz",
                new FuzzySet(new ExponentialMembershipFunction(2, lotOfReboundsMembership)));
        List<Label> reboundsLabels = Arrays.asList(veryFewReboundsLabel, fewReboundsLabel, enoughReboundsLabel,
                lotOfReboundsLabel, veryLotOfReboundsLabel);
        LinguisticVariable reboundsNumberLinguisticVariable = new LinguisticVariable("średnia liczba zbiórek na mecz",
                Range.closed(0.0, 16.3), reboundsLabels);

        //8. średnia liczba asyst na mecz
        MembershipFunction fewAssistsMembership = new LeftTrapezoidalMembershipFunction(0.0, 0.5, 1.5);
        Label veryFewAssistsLabel = new Label("bardzo mało asyst na mecz",
                new FuzzySet(new ExponentialMembershipFunction(2, fewAssistsMembership)));
        Label fewAssistsLabel = new Label("mało asyst na mecz",
                new FuzzySet(fewAssistsMembership));
        Label enoughAssistsLabel = new Label("dostateczna liczba asyst na mecz",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(0.5, 1.5, 2.0, 3.0)));
        MembershipFunction lotOfAssistsMembership = new RightTrapezoidalMembershipFunction(2, 3.6, 11.7);
        Label lotOfAssistsLabel = new Label("dużo asyst na mecz",
                new FuzzySet(lotOfAssistsMembership));
        Label veryLotOfAssistsLabel = new Label("bardzo dużo asyst na mecz",
                new FuzzySet(new ExponentialMembershipFunction(2, lotOfAssistsMembership)));
        List<Label> assistsNumberLabels = Arrays.asList(veryFewAssistsLabel, fewAssistsLabel, enoughAssistsLabel,
                lotOfAssistsLabel, veryLotOfAssistsLabel);
        LinguisticVariable assistsNumberLinguisticVariable = new LinguisticVariable("średnia liczba asyst na mecz",
                Range.closed(0.0, 11.7), assistsNumberLabels);

        //9. wpływ na drużynę
        Label fatalImpactLabel = new Label("fatalny wpływ na drużynę",
                new FuzzySet(new LeftTrapezoidalMembershipFunction(-100.0, -15.0, -5.0)));
        Label negativeImpactLabel = new Label("negatywny wpływ na drużynę",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(-15.0, -7.0, -2.5, 0.0)));
        Label neutralImpactLabel = new Label("neutralny wpływ na drużynę",
                new FuzzySet(new BothSidesTriangularMembershipFunction(-2.5, 0.0, 2.5)));
        Label positiveImpactLabel = new Label("pozytywny wpływ na drużynę",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(0, 3.2, 7.0, 13.4)));
        Label idealImpactLabel = new Label("idealny wpływ na drużynę",
                new FuzzySet(new RightTrapezoidalMembershipFunction(5.0, 13.0, 100.0)));
        List<Label> impactLabels = Arrays.asList(fatalImpactLabel, negativeImpactLabel, neutralImpactLabel,
                positiveImpactLabel, idealImpactLabel);
        LinguisticVariable impactLinguisticVariable = new LinguisticVariable("wpływ na drużynę",
                Range.closed(-100.0, 100.0), impactLabels);

        //10. skuteczność rzutów
        Label fatalAccuracyLabel = new Label("fatalną skuteczność rzutów",
                new FuzzySet(new LeftTrapezoidalMembershipFunction(0.0, 0.35, 0.45)));
        Label ineffectiveAccuracyLabel = new Label("nieskuteczni z rzutów",
                new FuzzySet(new GaussianMembershipFunction(0.4, 0.003)));
        Label averageAccuracyLabel = new Label("przeciętną skuteczność rzutów",
                new FuzzySet(new GaussianMembershipFunction(0.5, 0.001)));
        Label effectiveAccuracyLabel = new Label("skuteczni z rzutów",
                new FuzzySet(new GaussianMembershipFunction(0.57, 0.001)));
        Label idealAccuracyLabel = new Label("idealną skuteczność rzutów",
                new FuzzySet(new RightTrapezoidalMembershipFunction(0.58, 0.63, 1.0)));
        List<Label> accuracyLabels = Arrays.asList(fatalAccuracyLabel, ineffectiveAccuracyLabel, averageAccuracyLabel,
                effectiveAccuracyLabel, idealAccuracyLabel);
        LinguisticVariable accuracyLinguisticVariable = new LinguisticVariable("skuteczność rzutów",
                Range.closed(0.0, 1.0), accuracyLabels);

        //11. procent asyst
        Label fatalAssistsPercentLabel = new Label("fatalny procent asyst",
                new FuzzySet(new LeftTrapezoidalMembershipFunction(0, 0.01, 0.05)));
        Label smallAssistsPercentLabel = new Label("mały procent asyst",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(0.01, 0.05, 0.06, 0.10)));
        Label averageAssistsPercentLabel = new Label("przeciętny procent asysyt",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(0.06, 0.14, 0.15, 0.19)));
        Label bigAssistsPercentLabel = new Label("duży procent asysyt",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(0.15, 0.20, 0.23, 0.31)));
        Label idealAssistsPercentLabel = new Label("idealny procent asyst",
                new FuzzySet(new RightTrapezoidalMembershipFunction(0.23, 0.31, 1.0)));
        List<Label> assistsPercentLabels = Arrays.asList(fatalAssistsPercentLabel, smallAssistsPercentLabel,
                averageAssistsPercentLabel, bigAssistsPercentLabel, idealAssistsPercentLabel);
        LinguisticVariable assistsPercentLinguisticVariable = new LinguisticVariable("procent asyst",
                Range.closed(0.0, 1.0), assistsPercentLabels);


        List<LinguisticVariable> variables = Arrays.asList(ageLinguisticVariable, heightLinguisticVariable,
                weightLinguisticVariable, draftPickLinguisticVariable, gamesPlayedLinguisticVariable,
                pointsScoredLinguisticVariable, reboundsNumberLinguisticVariable, assistsNumberLinguisticVariable,
                impactLinguisticVariable, accuracyLinguisticVariable, assistsPercentLinguisticVariable);

        return variables;
    }

}
