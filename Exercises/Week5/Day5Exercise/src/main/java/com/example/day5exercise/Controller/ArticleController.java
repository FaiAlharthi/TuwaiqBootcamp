package com.example.day5exercise.Controller;

import com.example.day5exercise.Api.ApiResponse;
import com.example.day5exercise.Model.Article;
import com.example.day5exercise.Service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/article/v1")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService = new ArticleService();

    @GetMapping("/getAll")
    public ResponseEntity<?> getArticles(){
        ArrayList<Article> articles = new ArrayList<>();
        return ResponseEntity.status(200).body(articleService.getArticles());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addArticle (@RequestBody @Valid Article article, Errors errors){
        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        boolean added = articleService.addArticle(article);
        if(added){
            return ResponseEntity.status(200).body(new ApiResponse("article added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("article is not added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable String id, @RequestBody @Valid Article article, Errors errors){
        boolean updated = articleService.updateArticle(id,article);

        if(errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        if(updated){
            return ResponseEntity.status(200).body(new ApiResponse("article updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("article id is not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteArticle (@PathVariable String id){
        boolean deleted = articleService.deleteArticle(id);

        if(deleted){
            return ResponseEntity.status(200).body(new ApiResponse("article deleted successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("article id is not found"));
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity<?> publishArticle(@PathVariable String id){
        boolean published = articleService.publishArticle(id);

        if(published){
            return ResponseEntity.status(200).body(new ApiResponse("article published successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("article id is not found"));

    }

    @GetMapping("/published")
    public ResponseEntity<?> publishedArticles (){
        ArrayList<Article> publishedArticles = articleService.publishedArticles();

        if(publishedArticles.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No published articles "));
        }
        return ResponseEntity.status(200).body(publishedArticles);
    }

    @GetMapping("/getByCat/{category}")
    public ResponseEntity<?> getByCat(@PathVariable String category){
        ArrayList<Article> categoryArticles = articleService.getByCat(category);

        if(categoryArticles.isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("No articles withing this category "));
        }
        return ResponseEntity.status(200).body(categoryArticles);

    }





























}
