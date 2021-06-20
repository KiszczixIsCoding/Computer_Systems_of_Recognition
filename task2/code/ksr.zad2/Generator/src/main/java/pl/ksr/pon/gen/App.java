package pl.ksr.pon.gen;

import pl.ksr.pon.dao.DAO;
import pl.ksr.pon.dao.Player;
import pl.ksr.pon.dao.PlayerDAOFactory;

import java.io.IOException;
import java.util.List;

import pl.ksr.pon.dao.DAO;
import pl.ksr.pon.dao.Player;
import pl.ksr.pon.dao.PlayerDAOFactory;
import pl.ksr.pon.fuz.BothSidesTrapezoidalMembershipFunction;
import pl.ksr.pon.fuz.BothSidesTriangularMembershipFunction;
import pl.ksr.pon.fuz.FuzzySet;
import pl.ksr.pon.fuz.GaussianMembershipFunction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        DAO<Player> dao = new PlayerDAOFactory().getPlayerDAO(".\\all_seasons.csv");
        List<Player> players = dao.getAll();

        Predefined.getPredefinedLinguisticVariables(players);
        System.out.println( "Hello World!" );



        LinguisticLabel middleAgedLabel = new LinguisticLabel("w średnim wieku",
                new FuzzySet(new BothSidesTrapezoidalMembershipFunction(26.0, 28.0, 30.0, 32.0),players, "age"));
        LinguisticLabel middleHeightLabel = new LinguisticLabel("średniego wzrostu",
                new FuzzySet(new BothSidesTriangularMembershipFunction(185.0, 195.0, 205.0),players, "height"));
        LinguisticLabel almostAllLabel = new LinguisticLabel("Prawie wszystkie",
                new FuzzySet(new GaussianMembershipFunction(1.0, 0.01,0,1),players, ""));
        RelativeQuantifier almostAllQuantifier = new RelativeQuantifier(almostAllLabel);

        ArrayList<LinguisticLabel> qualifiers = new ArrayList<>( Arrays.asList(middleAgedLabel));
        ArrayList<LinguisticLabel> summarizers = new ArrayList<>( Arrays.asList(middleHeightLabel));
        ArrayList<LinguisticLabel> quantifiers = new ArrayList<>( Arrays.asList(almostAllLabel));


        LinguisticSummariesGenerator linguisticSummariesGenerator = new LinguisticSummariesGenerator(qualifiers,
                summarizers, almostAllQuantifier, players);
        var i= linguisticSummariesGenerator.countDegreeOfTruth();
        var j = 3;

    }
}
