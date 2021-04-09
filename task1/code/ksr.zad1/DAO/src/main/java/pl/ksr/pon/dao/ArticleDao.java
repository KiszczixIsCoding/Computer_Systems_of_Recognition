package pl.ksr.pon.dao;

import pl.ksr.pon.ext.Article;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArticleDao implements Dao<Article> {

    private List<File> selectedFiles;
    public ArticleDao(List<File> selectedFiles) {
        this.selectedFiles = selectedFiles;
    }

    @Override
    public List<Article> getAll() {
        List<Article> articles = new ArrayList<>();
        SGMLParser parser = new SGMLParser();

        for (File file : selectedFiles) {
            articles.addAll(parser.parse(file));
        }

        return articles;
    }
}
