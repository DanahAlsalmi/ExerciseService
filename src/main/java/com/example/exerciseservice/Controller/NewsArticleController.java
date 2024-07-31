package com.example.exerciseservice.Controller;


import com.example.exerciseservice.ApiResponse.ApiResponse;
import com.example.exerciseservice.Model.NewsArticle;
import com.example.exerciseservice.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsArticleController {

    private final NewsArticleService newsArticleService;

    @GetMapping("/get")
    public ResponseEntity getNewsArticle(){
//        ArrayList<NewsArticle> newsArticles = newsArticleService.getNewsArticles();
        return ResponseEntity.status(200).body(newsArticleService.getNewsArticles());
    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@Valid @RequestBody NewsArticle newsArticle, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        newsArticleService.addNewsArticle(newsArticle);
        return ResponseEntity.status(201).body(new ApiResponse("News Article Added Successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable int id ,@Valid @RequestBody NewsArticle newsArticle, Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated=newsArticleService.UpdateNewsArticle(id,newsArticle);
        if(isUpdated){
            return ResponseEntity.status(201).body(new ApiResponse("News Article Updated Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("News Article Not Found"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteNewsArticle(@PathVariable int id){
        boolean isDeleted=newsArticleService.DeleteNewsArticle(id);
        if(isDeleted){
            return ResponseEntity.status(201).body(new ApiResponse("News Article Deleted Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("News Article Not Found"));
    }


    @PutMapping("/publish/{id}")
    public ResponseEntity publishNewsArticle(@PathVariable int id){
        boolean isPublished=newsArticleService.publishNewsArticle(id);
        if(isPublished){
            return ResponseEntity.status(201).body(new ApiResponse("News Article Published Successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("News Article Not Found"));
    }

    @GetMapping("/all-publish")
    public ResponseEntity getAllPublishedNewsArticles(){
        return ResponseEntity.status(200).body(newsArticleService.getAllPublishedNewsArticles());
    }

    @GetMapping("/category/{category}")
    public ResponseEntity getNewsArticlesByCategory(@PathVariable String category){
        ArrayList<NewsArticle> newsArticles=newsArticleService.getNewsArticlesByCategory(category);
        if (newsArticles.isEmpty()){
            return ResponseEntity.status(404).body(new ApiResponse("News Article Not Found"));
        }
        return ResponseEntity.status(200).body(newsArticles);

    }

}
