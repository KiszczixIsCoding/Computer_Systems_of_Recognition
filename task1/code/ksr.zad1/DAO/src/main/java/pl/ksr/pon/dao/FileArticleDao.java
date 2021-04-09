package pl.ksr.pon.dao;

import org.apache.commons.lang3.StringUtils;
import pl.ksr.pon.ext.Article;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileArticleDao implements Dao<List<Article>> {
    private String filename;
    private FileReader fileReader;
    private boolean classifiedArticle;
    private boolean titleMarkup;
    private boolean contentMarkup;
    private Markups currentMarkup;

    public FileArticleDao(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Article> read() {
        List<Article> articlesFromFile = new ArrayList<>();
        ArticleValidator validator = new ArticleValidator();
        Article currentArticle = null;

        try (BufferedReader bufferedReader = new BufferedReader(fileReader = new FileReader(filename))) {
            String readLine;
            do {
                readLine = bufferedReader.readLine();
                if (validator.checkOpenMarkup(readLine, Markups.REUTERS)) {
                    classifiedArticle = true;
                    currentArticle = new Article();
                }
                
                if (classifiedArticle) {
                    for (Markups markup : Markups.values()) {
                        if (markup != Markups.REUTERS) {
                            if (validator.checkOpenMarkup(readLine, markup) && validator.checkCloseMarkup(readLine, markup)) {
                                StringUtils.remove(readLine, "<" + markup.name() + ">");
                                StringUtils.remove(readLine, "</" + markup.name() + ">");
                                changeArticleField(currentArticle, readLine, markup);
                                currentMarkup = null;
                            }

                            if (validator.checkOpenMarkup(readLine, markup) && !validator.checkCloseMarkup(readLine, markup)) {
                                StringUtils.remove(readLine, "<" + markup.name() + ">");
                                currentMarkup = markup;
                            }

                            if (!validator.checkOpenMarkup(readLine, markup) && validator.checkCloseMarkup(readLine, markup)) {
                                StringUtils.remove(readLine, "</" + markup.name() + ">");
                                changeArticleField(currentArticle, readLine, markup);
                                currentMarkup = null;
                            }
                        }
                        if (currentMarkup != null) {
                            changeArticleField(currentArticle, readLine, currentMarkup);
                        }
                        
                        if (validator.checkCloseMarkup(readLine, Markups.REUTERS)) {
                            articlesFromFile.add(currentArticle);
                        }
                    }
                }
            } while (!readLine.isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articlesFromFile;
    }

    public void changeArticleField(Article article, String line, Markups markup) {
        switch (markup) {
            case DATE: {
                article.setDate(article.getDate() + line);
            }
            case TITLE: {
                article.setTitle(article.getTitle() + line);
            }
            case BODY: {
                article.setContent(article.getContent() + line);
            }
        }
    }
}
