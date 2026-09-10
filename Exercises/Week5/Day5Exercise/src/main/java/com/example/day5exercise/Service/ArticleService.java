package com.example.day5exercise.Service;

import com.example.day5exercise.Model.Article;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ArticleService {
    ArrayList<Article> articles = new ArrayList<>();

    public ArrayList<Article> getArticles(){
        return articles;
    }

    public boolean addArticle(Article article){
        articles.add(article);
        return true;
    }

    public boolean updateArticle (String id, Article sendedArticle){
        for(int i =0 ; i<articles.size(); i++ ){
            if(articles.get(i).getId().equalsIgnoreCase(id)){
                articles.set(i,sendedArticle);
                return true;
            }
        }
        return false;
    }

    public boolean deleteArticle (String id){
        for(int i =0 ; i<articles.size(); i++ ){
            if(articles.get(i).getId().equalsIgnoreCase(id)){
                articles.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean publishArticle(String id){
        for(Article article : articles){
            if(article.getId().equalsIgnoreCase(id)){
                article.setPublished(true);
                return true;
            }
        }
        return false;
    }

    public ArrayList<Article> publishedArticles(){
        ArrayList<Article> publishedArticles = new ArrayList<>();

        for(Article article: articles){
            if(article.isPublished()){
                publishedArticles.add(article);
            }
        }

        return publishedArticles;
    }

    public ArrayList<Article> getByCat (String category){
        ArrayList<Article> categoryArticles = new ArrayList<>();

        for(Article article: articles){
            if(article.getCategory().equalsIgnoreCase(category)){
                categoryArticles.add(article);
            }
        }

        return categoryArticles;
    }

}
