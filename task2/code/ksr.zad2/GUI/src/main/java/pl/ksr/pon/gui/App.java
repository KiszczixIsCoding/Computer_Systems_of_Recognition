package pl.ksr.pon.gui;

import pl.ksr.pon.dao.DAO;
import pl.ksr.pon.dao.Player;
import pl.ksr.pon.dao.PlayerDAOFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        System.out.println( "Hello World!" );
        DAO<Player> dao = new PlayerDAOFactory().getPlayerDAO(".\\all_seasons.csv");
        List<Player> players = dao.getAll();
        for (int i = 0; i < 10; i++) {
            System.out.println("Index:" + players.get(i).getIndex());
            System.out.println("Country:" + players.get(i).getCountry());
            System.out.println("Avg assists:" + players.get(i).getAverageAssists());
            System.out.println("Age:" + players.get(i).getAge());
            System.out.println("--------------------");
        }
    }
}
