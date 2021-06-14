package pl.ksr.pon.gen;

import pl.ksr.pon.dao.DAO;
import pl.ksr.pon.dao.Player;
import pl.ksr.pon.dao.PlayerDAOFactory;

import java.io.IOException;
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
    }
}
