package com.example.exerciseservice.Service;

import com.example.exerciseservice.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();

    //Get News
    public ArrayList<NewsArticle> getNewsArticles() {
        return newsArticles;
    }

    //Add news
    public void addNewsArticle(NewsArticle newsArticle) {
        newsArticles.add(newsArticle);
    }

    //Update news
    public boolean UpdateNewsArticle(int id,NewsArticle newsArticle) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId() == id) {
                newsArticles.set(i, newsArticle);
                return true;
            }
        }
        return false;
    }

    //Delete news
    public boolean DeleteNewsArticle(int id) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId() == id) {
                newsArticles.remove(i);
                return true;
            }
        }
        return false;
    }

    //Publish news
    public boolean publishNewsArticle(int id) {
        for (NewsArticle article : newsArticles) {
            if (article.getId() == id) {
                article.setPublished(true);
                article.setPublishedDate(LocalDate.now());
                return true;
            }
        }
        return false;
    }

    //Publish all news
    public ArrayList<NewsArticle> getAllPublishedNewsArticles() {
        ArrayList<NewsArticle> publish = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles) {
            if (newsArticle.isPublished()) {
                publish.add(newsArticle);
            }
        }
        return publish;
    }

    //Publish by Category
    public ArrayList<NewsArticle> getNewsArticlesByCategory(String category) {
        ArrayList<NewsArticle> categorys = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles) {
            if (newsArticle.getCategory().equalsIgnoreCase(category)) {
                categorys.add(newsArticle);
            }
        }
        return categorys;
    }


}
