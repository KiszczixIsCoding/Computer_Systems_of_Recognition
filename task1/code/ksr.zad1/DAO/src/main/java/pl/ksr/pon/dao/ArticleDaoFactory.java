package pl.ksr.pon.dao;

import pl.ksr.pon.ext.Article;

import java.util.List;

public class ArticleDaoFactory {
    public Dao<List<Article>> getFileDao(String filename) {
        Dao<List<Article>> dao = new FileArticleDao(filename);
        return dao;
    }
}
