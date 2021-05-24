package pl.ksr.pon.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface DAO<T> {
    List<T> getAll() throws IOException;
}
