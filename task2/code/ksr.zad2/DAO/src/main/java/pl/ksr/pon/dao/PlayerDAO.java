package pl.ksr.pon.dao;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class PlayerDAO implements DAO<Player> {

    private String path;

    public PlayerDAO(String path) {
        this.path = path;
    }

    @Override
    public List<Player> getAll() throws IOException {
        try (
                Reader reader = Files.newBufferedReader(Paths.get(path));
        ) {
            CsvToBean<Player> csvToBean = new CsvToBeanBuilder<Player>(reader)
                    .withType(pl.ksr.pon.dao.Player.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            List<Player> players = csvToBean.parse();

            return players;

        }

    }
}
