package pl.ksr.pon.dao;

import pl.ksr.pon.ext.Article;

import java.io.File;
import java.util.List;

public class ArticleDaoFactory {
    public Dao<Article> getArticleDao(List<File> selectedFiles) {
        return new ArticleDao(selectedFiles);
    }
}
